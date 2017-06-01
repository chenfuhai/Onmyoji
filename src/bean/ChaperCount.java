package bean;

import java.io.Serializable;

public class ChaperCount implements Serializable{
	private String chaper;
	private int count;
	public ChaperCount(String chaper, int count) {
		super();
		this.chaper = chaper;
		this.count = count;
	}
	public ChaperCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getChaper() {
		return chaper;
	}
	public void setChaper(String chaper) {
		this.chaper = chaper;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
