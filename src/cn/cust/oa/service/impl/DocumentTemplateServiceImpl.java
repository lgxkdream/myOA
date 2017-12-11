package cn.cust.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.DocumentTemplate;
import cn.cust.oa.service.DocumentTemplateService;

import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class DocumentTemplateServiceImpl extends
		DaoSupportImpl<DocumentTemplate> implements DocumentTemplateService {
    
	/**
	 * 下载功能
	 */
	public InputStream download(Long id) throws Exception {
		DocumentTemplate documentTemplate = findById(id);
		String fileName = URLEncoder.encode(documentTemplate.getName(), "utf-8");
		ActionContext.getContext().put("fileName", fileName);
		//获取项目根目录
		String basePath = ServletActionContext.getServletContext().getRealPath("/");
		return new FileInputStream(new File(basePath + documentTemplate.getUrl()));
	}

}
