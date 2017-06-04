package action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import utils.NetUtils;

public class Test extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = NetUtils.readString(ServletActionContext.getRequest().getInputStream());
		ServletActionContext.getResponse().getWriter().println(msg);
		System.out.println(2134);
		return null;
	}
	
	
}
