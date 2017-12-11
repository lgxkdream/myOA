package cn.cust.oa.view.action;

import java.util.Collection;
import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.DocumentTemplate;
import cn.cust.oa.util.UploadUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 表单模板管理
 * 
 * @author LiGang
 * @date 2017-1-21 下午06:34:13
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class DocumentTemplateAction extends BaseAction<DocumentTemplate> {

	private static final long serialVersionUID = 2532804692242313341L;

	/**
	 * 列表功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<DocumentTemplate> documentTempList = documentTemplateService
				.findAll();
		ActionContext.getContext().put("documentTempList", documentTempList);
		return "list";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		Collection<ProcessDefinition> pdList = processDefinitionService
				.findLastVersion();
		ActionContext.getContext().put("pdList", pdList);
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		String url = UploadUtils.uploadFile(resource, resourceFileName, "/upload/documentTemplate");
		model.setUrl(url);
		documentTemplateService.save(model);
		return "toList";
	}

	/**
	 * 下载功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		inputStream = documentTemplateService.download(model.getId());
		return SUCCESS;
	}

}
