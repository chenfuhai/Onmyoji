package bean;

import java.io.Serializable;

/**
 * 式神的具体位置信息 比如哪个章节  哪个回合 哪个式神 数量是多少
 * @author chenfuhai
 *
 */
public class PositionInfo implements Serializable{
	private String chapter ;//章节 如第一章 御魂 妖气等
	private String section;//小节 如 第一章的第一个小怪 御魂的第一层
	private String rounds;//回合 第一章第一个小怪的第一个回合 御魂第一层的第一回合
	private int number;//统计数量
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getRound() {
		return rounds;
	}
	public void setRound(String round) {
		this.rounds = round;
	}
	public int getCount() {
		return number;
	}
	public void setCount(int count) {
		this.number = count;
	}
	public PositionInfo(String chapter, String section, String round, int count) {
		super();
		this.chapter = chapter;
		this.section = section;
		this.rounds = round;
		this.number = count;
	}
	public PositionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.chapter+"&nbsp;&nbsp;&nbsp;"+this.section+"&nbsp;&nbsp;&nbsp;"+this.rounds+"&nbsp;&nbsp;&nbsp;"+"数量为： "+this.number;
	}
	
}
