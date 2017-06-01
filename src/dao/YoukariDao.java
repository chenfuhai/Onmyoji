package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ChaperCount;
import bean.PositionInfo;
import bean.Youkari;
import utils.DBOpreate;

/**
 * 
 * @author chenfuhai
 *
 */
public class YoukariDao {
	/**
	 * 获取一个存有详细地址信息的妖怪
	 * 
	 * @param youkariName
	 * @return
	 */
	public static Youkari getYoukari(String youkariName) throws SQLException {
		Youkari youkari = null;

		String sql1 = "select name,clue from youkari where name ='" + youkariName + "'";
		ResultSet resultSet1 = DBOpreate.executeQuery(sql1);

		while (resultSet1.next()) {
			youkari = new Youkari();
			youkari.setName(resultSet1.getString(1));
			youkari.setClue(resultSet1.getString(2));
		}

		String sql = "select chaper " + " as 章节,section as 具体位置,rounds as " + "第几回合,number as 数量 from youkari_positon, "
				+ "youkari where youkari_id=youkari.id and youkari.name='" + youkariName + "'";
		ResultSet resultSet = DBOpreate.executeQuery(sql);

		ArrayList<PositionInfo> positionInfos = new ArrayList<>();
		while (resultSet.next()) {
			PositionInfo positionInfo = new PositionInfo(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getInt(4));
			positionInfos.add(positionInfo);
		}
		youkari.setPosInfos(positionInfos);

		return youkari;
	}

	/**
	 * 获取详细地址里面的统计信息
	 * @param youkari
	 * @return
	 */
	public static ArrayList<ChaperCount> getChaperCount(Youkari youkari){
		ArrayList<ChaperCount> counts = new ArrayList<>();
		ChaperCount chaperCount = null;
		int count= 0;
		String chaper = "";
		if (youkari.getPosInfos()!=null) {
			ArrayList<PositionInfo> positionInfos = youkari.getPosInfos();
			//处理非御魂副本的
			for(int i = 0;i<positionInfos.size();i++){
				if (positionInfos.get(i).getChapter().equals("御魂副本")) {
					continue;
				}

				if (i==positionInfos.size()-1) {
					//要记得把最后一个添加进来
					if (chaper !=null && !chaper.isEmpty() && count!=0) {
						counts.add(new ChaperCount(chaper, count));
					}
				}
				if (i == 0) {
					chaper = positionInfos.get(i).getChapter();						
					count +=positionInfos.get(i).getCount();
					
					if (i==positionInfos.size()-1) {
						//要记得把最后一个添加进来
						if (chaper !=null && !chaper.isEmpty() && count!=0) {
							counts.add(new ChaperCount(chaper, count));
						}
					}
					continue;
				}
				//说明这个时候 下一个的章节内容跟前一个是一样的 可以统计在一起
				//判断一下 这个时候取到的章节是否跟上一个一样 如果一样 统计一下 如果不一样
				//把上一个章节的信息正式存储起来，然后新建空白的存储器 存储这个章节的信息
				
				if (positionInfos.get(i).getChapter().equals(positionInfos.get(i-1).getChapter())) {
					count+=positionInfos.get(i).getCount();
				}else{
					if (chaper !=null && !chaper.isEmpty() && count!=0) {
						counts.add(new ChaperCount(chaper, count));
					}
					chaper = positionInfos.get(i).getChapter();						
					count = positionInfos.get(i).getCount();
				}
				
			}
			String chaperYH = "";
			int countYH = 0;
			//处理御魂副本的
			for(int i = 0;i<positionInfos.size();i++){
				if (!positionInfos.get(i).getChapter().equals("御魂副本")) {
					if (i==positionInfos.size()-1) {
						//要记得把最后一个添加进来
						if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
							counts.add(new ChaperCount(chaperYH, countYH));							
						}
					}
					continue;
				}
			
				if (i==positionInfos.size()-1) {
					//要记得把最后一个添加进来
					if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
						counts.add(new ChaperCount(chaperYH, countYH));							
					}
				}
				if (i == 0) {
					chaperYH = positionInfos.get(i).getChapter()+positionInfos.get(i).getSection();						
					countYH +=positionInfos.get(i).getCount();
					if (i==positionInfos.size()-1) {
						//要记得把最后一个添加进来
						if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
							counts.add(new ChaperCount(chaperYH, countYH));							
						}
					}
					continue;
				}
				//说明这个时候 下一个的章节内容跟前一个是一样的 可以统计在一起
				//判断一下 这个时候取到的章节是否跟上一个一样 如果一样 统计一下 如果不一样
				//把上一个章节的信息正式存储起来，然后新建空白的存储器 存储这个章节的信息
				
				String theNew = positionInfos.get(i).getChapter()+positionInfos.get(i).getSection();
				String theOld = positionInfos.get(i-1).getChapter()+positionInfos.get(i-1).getSection();
				if (theNew.equals(theOld)) {
					countYH+=positionInfos.get(i).getCount();
				}else{
					if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
						counts.add(new ChaperCount(chaperYH, countYH));							
					}
					chaperYH = theNew;					
					countYH = positionInfos.get(i).getCount();
				}
				
			}
		}
		
		return counts;
	}

	
}
