package cn.cust.oa.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefinitionService {

	/**
	 * 审批流程列表
	 * 
	 * @return
	 */
	Collection<ProcessDefinition> findLastVersion();

	/**
	 * 部署流程定义文档
	 */
	void deploy(File resource) throws FileNotFoundException;

	/**
	 * 删除流程定义
	 */
	void delete(String pdkey);

	/**
	 * 查看流程图
	 * 
	 * @return
	 */
	InputStream show(String deploymentId);

}
