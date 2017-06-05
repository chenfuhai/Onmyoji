package action;

import utils.Query;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import utils.NetUtils;

public class Test extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = NetUtils.readString(ServletActionContext.getRequest().getInputStream());
		Gson gson = new GsonBuilder().serializeNulls().create();
		Query query = gson.fromJson(msg, Query.class);
		
		
		ServletActionContext.getResponse().getWriter().println(msg);
		System.out.println(234234);
		System.out.println(query.toString());
		return null;
	}
	
	
}
