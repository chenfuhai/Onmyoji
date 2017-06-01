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
 * ʽ��action ���ʽ�� ɾ��ʽ�� ����ʽ��
 * 
 * @author chenfuhai
 *
 */
public class ShikigamiAction {
	private HttpServletRequest request = ServletActionContext.getRequest();

	// ������������ �ó����۷���
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
			
			//����һ
			result.append("<br/>����:"+youkariName1+" ������ �� "+chaperCountBest1.getChaper()+" һ���У� "+chaperCountBest1.getCount()+"ֻ<br/>");
			result.append("�������£�<br/>");
			for(PositionInfo p :youkari1.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest1.getChaper())) {
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
				}
			}
			//����һ����û���ڱ��¿��Դ����������ֵ� �еĻ�һ����ʾ����
			for(PositionInfo p:youkari2.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest1.getChaper())) {
					result.append(youkariName2+"���ڱ��µ�λ�ã�");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
					
				}
			}
			for(PositionInfo p:youkari3.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest1.getChaper())) {
					result.append(youkariName3+"���ڱ��µ�λ�ã�");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
					
				}
			}
			
			//����2
			result.append("<br/>����:"+youkariName2+" ������ �� "+chaperCountBest2.getChaper()+" һ���У� "+chaperCountBest2.getCount()+"ֻ<br/>");
			result.append("�������£�<br/>");
			for(PositionInfo p :youkari2.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest2.getChaper())) {
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
				}
			}
			//����һ����û���ڱ��¿��Դ����������ֵ� �еĻ�һ����ʾ����
			for(PositionInfo p:youkari1.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest2.getChaper())) {
					result.append(youkariName1+"���ڱ��µ�λ�ã�");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
					
				}
			}
			for(PositionInfo p:youkari3.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest2.getChaper())) {
					result.append(youkariName3+"���ڱ��µ�λ�ã�");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
					
				}
			}
			
			//����3
			result.append("<br/>����:"+youkariName3+" ������ �� "+chaperCountBest3.getChaper()+" һ���У� "+chaperCountBest3.getCount()+"ֻ<br/>");
			result.append("�������£�<br/>");
			for(PositionInfo p :youkari3.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest3.getChaper())) {
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
				}
			}
			//����һ����û���ڱ��¿��Դ����������ֵ� �еĻ�һ����ʾ����
			for(PositionInfo p:youkari2.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest3.getChaper())) {
					result.append(youkariName2+"���ڱ��µ�λ�ã�");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
					
				}
			}
			for(PositionInfo p:youkari1.getPosInfos()){
				if (p.getChapter().equals(chaperCountBest3.getChaper())) {
					result.append(youkariName1+"���ڱ��µ�λ�ã�");
					result.append(p.getChapter()+"&nbsp;&nbsp;"+p.getSection()
					+"&nbsp;&nbsp;"+p.getRound()+"&nbsp;&nbsp;"+"������ "+p.getCount()+"<br/>");
					
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
			// JQuery�е��÷�
			System.out.println(result.toString());
			ServletActionContext.getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// �����ύ���������� ���ط�������Ľ��
	public String analysis() {
		boolean flag = false;

		String youkariName = request.getParameter("youkari");
		if (youkariName.equals("��ѡ������")) {
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
			stringBuffer.append("�����У�" + c.getCount());
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
		if (clue.equals("��������")) {
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
			// JQuery�е��÷�
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
			// JQuery�е��÷�
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// �������е�������Ϣ����
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

	// �������
	public String addShikigami() {
		boolean flag = false;

		if (flag) {
			return "success";
		} else {
			return "failure";
		}
	}
}
