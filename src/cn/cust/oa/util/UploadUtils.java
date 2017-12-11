package cn.cust.oa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * 文件上传工具类
 * 
 * @author LiGang
 * @date 2017-1-22 下午03:22:13
 * @version v1.0
 */
public class UploadUtils {

	/**
	 * 
	 * @param resource 上传的文件
	 * @param resourceFileName 上传的文件名称
	 * @param path 要保存的路径，例如/upload/headimg
	 * @return 保存的文件路径
	 * @throws IOException
	 */
	public static String uploadFile(File resource, String resourceFileName, String path)
			throws IOException {
		String url = null;
		if (resource != null) {
			String realPath = ServletActionContext.getServletContext()
					.getRealPath(path);
			String documentType = resourceFileName.substring(
					resourceFileName.lastIndexOf(".") + 1).trim().toLowerCase();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssS");
			String documentName = new String(sdf.format(new Date())) + "."
					+ documentType;
			File destFile = new File(realPath + "/" + documentName);
			FileUtils.copyFile(resource, destFile);
			url = path + "/" + documentName;
		}
		return url;
	}

}
