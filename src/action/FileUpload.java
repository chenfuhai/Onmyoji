package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/***
 * �ļ��ϴ�����   resource code encoding is utf-8
 * <br>��ҪΪ��android�ͻ���ʵ�ֹ���   ����д����   ���Ҽ���
 * @author ICQwlj<br>
 * Email :wlj250237@126.com<br>
 *
 */
public class FileUpload extends ActionSupport {

	private String savePath;
	/**��������ֺͰ�׿������Ǹ�fileKey�����ֱ���Գ�*/
	private File img;
	/**Ҫ�ϴ����ļ�����*/
	private String imgContentType;
	/**�ļ�������*/
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
	 * ָ�����ϴ�����zip��ͼƬ��ʽ���ļ�
	 */
	private static final String[] types = { "application/x-zip-compressed",
			"ZIP", "image/pjpeg","image/x-png","image/jpeg","image/png" };  //"application/octet-stream; charset=utf-8",

	/***
	 * �ж��ļ��������Ƿ�Ϊָ�����ļ�����
	 * @return
	 */
	public boolean filterType() {
		boolean isFileType = false;
		String fileType = getImgContentType();
		System.out.println(fileType);
		for (String type : types) {
			if (type.equals(fileType)) {
				isFileType = true;
				break;
			}
		}
		return isFileType;
	}



	/**
	 * ȡ���ļ��д�С
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public long getFileSize(File f) throws Exception {
		return f.length();
	}

	public String FormetFileSize(long fileS) {// ת���ļ���С
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * �ϴ��ļ�����
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
		if (!filterType()) {
			System.out.println("�ļ����Ͳ���ȷ");
			ServletActionContext.getRequest().setAttribute("typeError",
					"��Ҫ�ϴ����ļ����Ͳ���ȷ");

			result = "error:" + getImgContentType() + " type not upload file type";
		} else {
			System.out.println("��ǰ�ļ���СΪ��"
					+ FormetFileSize(getFileSize(getImg())));
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				// �����ļ���һ��·��
				fos = new FileOutputStream(getSavePath() + "\\"
						+ getImgFileName());
				fis = new FileInputStream(getImg());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				result = "upload file success !";
			} catch (Exception e) {
				result = "upload file failed ! ";
				e.printStackTrace();
			} finally {
				fos.close();
				fis.close();
			}
		}
		out.print(result);
		return null;
	}
}