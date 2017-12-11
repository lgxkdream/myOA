package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 表单模板
 * 
 * @author LiGang
 * @date 2017-1-20 下午08:16:10
 * @version v1.0
 */
public class DocumentTemplate implements Serializable {

	private static final long serialVersionUID = -2435874660538051701L;

	private Long id; // 主键id
	private String name; // 模板名称
	private String processKey; // 所用流程 jbpm中的pdkey（千万不要用key，因为是hibernate映射文件关键字）
	private String url; // 模板存储路径
	private Set<Form> forms = new HashSet<Form>(); // 申请

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Form> getForms() {
		return forms;
	}

	public void setForms(Set<Form> forms) {
		this.forms = forms;
	}

}
