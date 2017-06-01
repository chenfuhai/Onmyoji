package action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.ChaperCount;
import bean.PositionInfo;
import bean.Youkari;
import dao.YoukariDao;
import utils.DBOpreate;

/**
 * 式神action 添加式神 删除式神 查找式神
 * 
 * @author chenfuhai
 *
 */
public class ShikigamiAction {
	private HttpServletRequest request = ServletActionContext.getRequest();

	// 分析三个妖怪 得出结论返回
	public String analysisAll() {
		ServletActionContext.getResponse().setContentType("text/html; charset=UTF-8");
		StringBuffer result = new StringBuffer();
		String youkariName1 = request.getParameter("youkari1");
		String youkariName2 = request.getParameter("youkari2");
		String youkariName3 = request.getParameter("youkari3");
//		String snum1=request.getParameter("youkariCount1");
//		String snum2=request.getParameter("youkariCount2");
//		String snum3=request.getParameter("youkariCount3");
//		int num1 = Integer.parseInt(snum1);
//		int num2 = Integer.parseInt(snum2);
//		int num3 = Integer.parseInt(snum3);
		
		try {
			Youkari youkari1 = YoukariDao.getYoukari(youkariName1);
			Youkari youkari2 = YoukariDao.getYoukari(youkariName2);
			Youkari youkari3 = YoukariDao.getYoukari(youkariName3);
			ArrayList<ChaperCount> chaperCounts1 = YoukariDao.getChaperCount(youkari1);
			ArrayList<ChaperCount> chaperCounts2 = YoukariDao.getChaperCount(youkari2);
			ArrayList<ChaperCount> chaperCounts3 = YoukariDao.getChaperCount(youkari3);
			
			ChaperCount chaperCountBest1 = chaperCounts1.get(0);
			System.out.println(youkari2.toString()+chaperCounts2.size());
			ChaperCount chaperCountBest2 =  chaperCounts2.get(0);
			ChaperCount chaperCountBest3 =  chaperCounts3.get(0);
			for(int i =0;i<chaperCounts1.size();i++){
				if (chaperCounts1.get(i).getCount()>chaperCountBest1.getCount()) {
					chaperCountBest1=chaperCounts1.get(i);
				}
			}
			for(int i =0;i<chaperCounts2.size();i++){
				if (chaperCounts2.get(i).getCount()>chaperCountBest2.getCount()) {
					chaperCountBest2=chaperCounts2.get(i);
				}
			}
			for(int i =0;i<chaperCounts3.size();i++){
				if (chaperCounts3.get(i).getCount()>chaperCountBest3.getCount()) {
					chaperCountBest3=chaperCounts3.get(i);
				}
			}
			
			//妖怪一
			result.append("<br/>妖怪:"+youkariName1+" 建议在 ： "+chaperCountBest1.getChaper()+" 一共有： "+chaperCountBest1.getCount()+"只<br/>");
			result.append("具体如下：<br/>");
			for(PositionInfo p :youkari1.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest1.getChaper())) {
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
				}
			}
			//再找一下有没有在本章可以打到另外两个怪的 有的话一并显示出来
			for(PositionInfo p:youkari2.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest1.getChaper())) {
					result.append(youkariName2+"存在本章的位置：");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
					
				}
			}
			for(PositionInfo p:youkari3.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest1.getChaper())) {
					result.append(youkariName3+"存在本章的位置：");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
					
				}
			}
			
			//妖怪2
			result.append("<br/>妖怪:"+youkariName2+" 建议在 ： "+chaperCountBest2.getChaper()+" 一共有： "+chaperCountBest2.getCount()+"只<br/>");
			result.append("具体如下：<br/>");
			for(PositionInfo p :youkari2.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest2.getChaper())) {
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
				}
			}
			//再找一下有没有在本章可以打到另外两个怪的 有的话一并显示出来
			for(PositionInfo p:youkari1.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest2.getChaper())) {
					result.append(youkariName1+"存在本章的位置：");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
					
				}
			}
			for(PositionInfo p:youkari3.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest2.getChaper())) {
					result.append(youkariName3+"存在本章的位置：");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
					
				}
			}
			
			//妖怪3
			result.append("<br/>妖怪:"+youkariName3+" 建议在 ： "+chaperCountBest3.getChaper()+" 一共有： "+chaperCountBest3.getCount()+"只<br/>");
			result.append("具体如下：<br/>");
			for(PositionInfo p :youkari3.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest3.getChaper())) {
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
				}
			}
			//再找一下有没有在本章可以打到另外两个怪的 有的话一并显示出来
			for(PositionInfo p:youkari2.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest3.getChaper())) {
					result.append(youkariName2+"存在本章的位置：");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
					
				}
			}
			for(PositionInfo p:youkari1.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest3.getChaper())) {
					result.append(youkariName1+"存在本章的位置：");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"数量： "+p.getCount()+"<br/>");
					
				}
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			DBOpreate.closeAllConnection();
		}

		
		
		try {
			// JQuery中的用法
			System.out.println(result.toString());
			ServletActionContext.getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 分析提交上来的需求 返回分析过后的结果
	public String analysis() {
		boolean flag = false;

		String youkariName = request.getParameter("youkari");
		if (youkariName.equals("请选择妖怪")) {
			return "success";
		}

		Youkari youkari = null;
		try {
			youkari = YoukariDao.getYoukari(youkariName);
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBOpreate.closeAllConnection();
		}
		ArrayList<ChaperCount> chaperCounts = YoukariDao.getChaperCount(youkari);
		StringBuffer stringBuffer = new StringBuffer();
		for (ChaperCount c : chaperCounts) {
			stringBuffer.append(c.getChaper());
			stringBuffer.append("&nbsp;");
			stringBuffer.append("数量有：" + c.getCount());
			stringBuffer.append("<br/>");
		}

		if (flag) {
			String pos = request.getParameter("position");
			request.getSession().setAttribute("youkariMsg" + pos,
					youkari.toString() + "<br/>" + stringBuffer.toString());
			return "success";
		} else {
			return "failure";
		}
	}

	public String getName() {
		ServletActionContext.getResponse().setContentType("text/html; charset=UTF-8");
		String result = null;
		String clue = request.getParameter("clue");
		if (clue.equals("暂无线索")) {
			return null;
		}

		String sql = "select name from youkari where clue = '" + clue + "'";
		ResultSet rs = DBOpreate.executeQuery(sql);

		try {
			rs.next();
			result = rs.getString(1);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DBOpreate.closeAllConnection();
		}
		try {
			// JQuery中的用法
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String getClue() {
		ServletActionContext.getResponse().setContentType("text/html; charset=UTF-8");
		String result = null;
		String name = request.getParameter("name");
		System.out.println(name);
		String sql = "select clue from youkari where name = '" + name + "'";
		ResultSet rs = DBOpreate.executeQuery(sql);

		try {
			rs.next();
			result = rs.getString(1);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DBOpreate.closeAllConnection();
		}
		try {
			// JQuery中的用法
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 返回现有的妖怪信息集合
	public String updateMessage() {
		boolean flag = false;
		ArrayList<Youkari> youkaris = new ArrayList<>();
		String sql = "select distinct name,clue from youkari";
		ResultSet resultSet = DBOpreate.executeQuery(sql);
		System.out.println(1);
		try {
			while (resultSet.next()) {
				Youkari youkari = new Youkari();
				youkari.setName(resultSet.getString(1));
				youkari.setClue(resultSet.getString(2));
				youkaris.add(youkari);
			}
			flag = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag = false;
			e.printStackTrace();

		} finally {
			DBOpreate.closeAllConnection();
		}

		if (flag) {
			request.getSession().setAttribute("youkaris", youkaris);
			return "success";
		} else {
			return "failure";
		}
	}

	// 添加妖怪
	public String addShikigami() {
		boolean flag = false;

		if (flag) {
			return "success";
		} else {
			return "failure";
		}
	}
}
