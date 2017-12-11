package cn.cust.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;

import cn.cust.oa.service.ProcessDefinitionService;

@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Resource
	private ProcessEngine processEngine;

	/**
	 * 审批流程列表
	 */
	public Collection<ProcessDefinition> findLastVersion() {
		List<ProcessDefinition> pdList = processEngine.getRepositoryService().
		createProcessDefinitionQuery()
		.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)
		.list();
		Map<String, ProcessDefinition> maps = new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd : pdList){
			maps.put(pd.getKey(), pd);
		}
		return maps.values();
	}

	/**
	 * 部署流程定义文档
	 * @throws FileNotFoundException 
	 */
	public void deploy(File resource) throws FileNotFoundException {
		InputStream in = new FileInputStream(resource);
		ZipInputStream zipInputStream = new ZipInputStream(in);
		processEngine.getRepositoryService()
		.createDeployment().addResourcesFromZipInputStream(zipInputStream)
		.deploy();
	}

	/**
	 * 删除流程定义
	 * 思路：pdkey-->在该key下定义的所有的pd-->循环遍历所有的pd-->得到每一个pddeploymentId-->删除
	 */
	public void delete(String pdkey) {
		List<ProcessDefinition> pdList = processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.processDefinitionKey(pdkey)
		.list();
		for(ProcessDefinition pd : pdList){
			processEngine.getRepositoryService()
			.deleteDeploymentCascade(pd.getDeploymentId());
		}
	}
 
	/**
	 * 查看流程图
	 * 思路：deploymentId-->该deploymentId所对应的pd-->得到imageResourceName
	 */
	public InputStream show(String deploymentId) {
		ProcessDefinition pd = processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.deploymentId(deploymentId).uniqueResult();
		return processEngine.getRepositoryService()
		.getResourceAsStream(deploymentId, pd.getImageResourceName());
	}

}
