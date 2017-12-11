package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批流转记录
 * 
 * @author LiGang
 * @date 2017-1-20 下午08:43:36
 * @version v1.0
 */
public class ApproveInfo implements Serializable {

	private static final long serialVersionUID = -1219838850108205565L;
	
	public static final String DISAGREE = "不同意";
	public static final String AGREE = "同意";

	private Long id; // 主键id
	private String approver; // 审批人
	private Date approveTime; // 审批时间
	private String approval; // 是否同意
	private String comment; // 审批意见
	private Form form; // 我的申请

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

}
