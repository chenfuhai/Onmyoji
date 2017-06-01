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
	 * ��ȡһ��������ϸ��ַ��Ϣ������
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

		String sql = "select chaper " + " as �½�,section as ����λ��,rounds as " + "�ڼ��غ�,number as ���� from youkari_positon, "
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
	 * ��ȡ��ϸ��ַ�����ͳ����Ϣ
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
			//��������긱����
			for(int i = 0;i<positionInfos.size();i++){
				if (positionInfos.get(i).getChapter().equals("���긱��")) {
					continue;
				}

				if (i==positionInfos.size()-1) {
					//Ҫ�ǵð����һ����ӽ���
					if (chaper !=null && !chaper.isEmpty() && count!=0) {
						counts.add(new ChaperCount(chaper, count));
					}
				}
				if (i == 0) {
					chaper = positionInfos.get(i).getChapter();						
					count +=positionInfos.get(i).getCount();
					
					if (i==positionInfos.size()-1) {
						//Ҫ�ǵð����һ����ӽ���
						if (chaper !=null && !chaper.isEmpty() && count!=0) {
							counts.add(new ChaperCount(chaper, count));
						}
					}
					continue;
				}
				//˵�����ʱ�� ��һ�����½����ݸ�ǰһ����һ���� ����ͳ����һ��
				//�ж�һ�� ���ʱ��ȡ�����½��Ƿ����һ��һ�� ���һ�� ͳ��һ�� �����һ��
				//����һ���½ڵ���Ϣ��ʽ�洢������Ȼ���½��հ׵Ĵ洢�� �洢����½ڵ���Ϣ
				
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
			//�������긱����
			for(int i = 0;i<positionInfos.size();i++){
				if (!positionInfos.get(i).getChapter().equals("���긱��")) {
					if (i==positionInfos.size()-1) {
						//Ҫ�ǵð����һ����ӽ���
						if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
							counts.add(new ChaperCount(chaperYH, countYH));							
						}
					}
					continue;
				}
			
				if (i==positionInfos.size()-1) {
					//Ҫ�ǵð����һ����ӽ���
					if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
						counts.add(new ChaperCount(chaperYH, countYH));							
					}
				}
				if (i == 0) {
					chaperYH = positionInfos.get(i).getChapter()+positionInfos.get(i).getSection();						
					countYH +=positionInfos.get(i).getCount();
					if (i==positionInfos.size()-1) {
						//Ҫ�ǵð����һ����ӽ���
						if (chaperYH !=null && !chaperYH.isEmpty() && countYH!=0) {
							counts.add(new ChaperCount(chaperYH, countYH));							
						}
					}
					continue;
				}
				//˵�����ʱ�� ��һ�����½����ݸ�ǰһ����һ���� ����ͳ����һ��
				//�ж�һ�� ���ʱ��ȡ�����½��Ƿ����һ��һ�� ���һ�� ͳ��һ�� �����һ��
				//����һ���½ڵ���Ϣ��ʽ�洢������Ȼ���½��հ׵Ĵ洢�� �洢����½ڵ���Ϣ
				
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
