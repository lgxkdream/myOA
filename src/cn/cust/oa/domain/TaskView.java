package cn.cust.oa.domain;

import org.jbpm.api.task.Task;

/**
 * 任务视图
 * 
 * @author LiGang
 * @date 2017-1-22 下午05:17:33
 * @version v1.0
 */
public class TaskView {

	private Form form;
	private Task task;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
