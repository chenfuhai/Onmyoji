package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PositionInfo;
import bean.Youkari;
import utils.DBOpreate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String youkariName = "涂壁";
		String sql1 = "select name,clue from youkari where name ='"+youkariName+"'";
		ResultSet resultSet1 = DBOpreate.executeQuery(sql1);
		Youkari youkari = null;
		try {
			while(resultSet1.next()){
				youkari= new Youkari();
				youkari.setName(resultSet1.getString(1));
				youkari.setClue(resultSet1.getString(2));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBOpreate.closeAllConnection();
		}
		
		String sql = "select chaper "
				+ " as 章节,section as 具体位置,rounds as "
				+ "第几回合,number as 数量 from youkari_positon, "
				+ "youkari where youkari_id=youkari.id and youkari.name='"+youkariName+"'";
		ResultSet resultSet = DBOpreate.executeQuery(sql);
		
		try {
			
			ArrayList<PositionInfo> positionInfos = new ArrayList<>();
			while(resultSet.next()){
				PositionInfo positionInfo = new PositionInfo(
						resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getInt(4)
						);
				positionInfos.add(positionInfo);
			}
			youkari.setPosInfos(positionInfos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBOpreate.closeAllConnection();
		}
		
		System.out.println(youkari.toString());
	}

}
