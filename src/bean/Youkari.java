package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 式神类 每个具体式神有name名字 alias别名 position位置信息列表
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
		return "妖怪名为: "+name+"&nbsp;&nbsp;&nbsp;"+(clue!=null && !clue.isEmpty()?"悬赏线索为: "+clue:"暂无悬赏线索")+"<br/>"
				+"妖怪的位置如下:"+"<br/>"+getAllPosInfo();
	}
}
