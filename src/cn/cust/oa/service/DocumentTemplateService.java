package cn.cust.oa.service;

import java.io.InputStream;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.DocumentTemplate;

public interface DocumentTemplateService extends DaoSupport<DocumentTemplate> {

	/**
	 * 下载功能
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	InputStream download(Long id) throws Exception;
}
