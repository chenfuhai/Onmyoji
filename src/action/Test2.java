package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import utils.NetUtils;

public class Test2 extends ActionSupport{

	private String savePath;
	/**这里的名字和安卓里面的那个fileKey的名字必须对称*/
	private File img;
	/**要上传的文件类型*/
	private String imgContentType;
	/**文件的名称*/
	private String imgFileName;
	
	private String orderId;


	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public File getImg() {
		return img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setSavePath(String value) {
		this.savePath = value;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 上传文件操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception {
		
		String ct  =  ServletActionContext.getRequest().getHeader("Content-Type");
		System.out.println("Content-Type="+ct);
		String result = "unknow error";
		System.out.println("orderId="+getOrderId());
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		System.out.println(123);
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				// 保存文件那一个路径
				System.out.println(getSavePath() + "\\"+ getImgFileName());
				File f1=new File(getSavePath()) ;
				f1.mkdirs();
				
				fos = new FileOutputStream(getSavePath() + "\\"
						+getImgFileName());
				fis = new FileInputStream(getImg());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				result = savePath+"/"+getImgFileName();
			} catch (Exception e) {
				result = "upload file failed ! ";
				e.printStackTrace();
			} finally {
				fos.close();
				fis.close();
			}
			System.out.println(123);
			
		out.print(result);
		out.close();
		return null;
	}
	
	
}
