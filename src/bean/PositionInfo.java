package bean;

import java.io.Serializable;

/**
 * ʽ��ľ���λ����Ϣ �����ĸ��½�  �ĸ��غ� �ĸ�ʽ�� �����Ƕ���
 * @author chenfuhai
 *
 */
public class PositionInfo implements Serializable{
	private String chapter ;//�½� ���һ�� ���� ������
	private String section;//С�� �� ��һ�µĵ�һ��С�� ����ĵ�һ��
	private String rounds;//�غ� ��һ�µ�һ��С�ֵĵ�һ���غ� �����һ��ĵ�һ�غ�
	private int number;//ͳ������
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
		return this.chapter+"&nbsp;&nbsp;&nbsp;"+this.section+"&nbsp;&nbsp;&nbsp;"+this.rounds+"&nbsp;&nbsp;&nbsp;"+"����Ϊ�� "+this.number;
	}
	
}
