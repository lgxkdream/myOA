package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 我的申请
 * 
 * @author LiGang
 * @date 2017-1-20 下午08:34:06
 * @version v1.0
 */
public class Form implements Serializable {

	private static final long serialVersionUID = -8692712540912755907L;
	
	// 各种当前状态
	public static final String ONGOING = "审批中";
	public static final String UNPASS = "未通过";
	public static final String PASS = "已通过";

	private Long id; // 主键id
	private String title; // 标题
	private String applicator; // 申请人
	private Date applyTime; // 申请日期
	private String state; // 当前状态
	private String url; // 填好的表单地址
	private DocumentTemplate documentTemplate; // 表单模板
	private Set<ApproveInfo> approveInfos = new HashSet<ApproveInfo>(); // 审批流转记录

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplicator() {
		return applicator;
	}

	public void setApplicator(String applicator) {
		this.applicator = applicator;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DocumentTemplate getDocumentTemplate() {
		return documentTemplate;
	}

	public void setDocumentTemplate(DocumentTemplate documentTemplate) {
		this.documentTemplate = documentTemplate;
	}

	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}

	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}

}
