package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ʽ���� ÿ������ʽ����name���� alias���� positionλ����Ϣ�б�
 * 
 * @author chenfuhai
 *
 */
public class Youkari implements Serializable{
	private String name;
	private String clue;
	private ArrayList<PositionInfo> posInfos;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClue() {
		return clue;
	}
	public void setClue(String alias) {
		this.clue = alias;
	}
	public ArrayList<PositionInfo> getPosInfos() {
		return posInfos;
	}
	public void setPosInfos(ArrayList<PositionInfo> posInfos) {
		this.posInfos = posInfos;
	}
	public Youkari(String name, String alias, ArrayList<PositionInfo> posInfos) {
		super();
		this.name = name;
		this.clue = alias;
		this.posInfos = posInfos;
	}
	public Youkari() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String getAllPosInfo(){
		StringBuffer buffer = new StringBuffer();
		for( PositionInfo pInfo : this.posInfos){
			buffer.append(pInfo.toString());
			buffer.append("<br/>");
		}
		return buffer.toString();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "������Ϊ: "+name+"&nbsp;&nbsp;&nbsp;"+(clue!=null && !clue.isEmpty()?"��������Ϊ: "+clue:"������������")+"<br/>"
				+"���ֵ�λ������:"+"<br/>"+getAllPosInfo();
	}
}
