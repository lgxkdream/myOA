package cn.cust.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Forum;

import com.opensymphony.xwork2.ActionContext;

/**
 * 版块管理员管理
 * 
 * @author LiGang
 * @date 2016-12-19 下午09:08:54
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {

	private static final long serialVersionUID = 4750120336161645831L;

	/**
	 * 列表功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/**
	 * 删除功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		forumService.save(model);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		Forum forum = forumService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}

	/**
	 * 修改功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Forum forum = forumService.findById(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumService.update(forum);
		return "toList";
	}

	/**
	 * 向上移动
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}

	/**
	 * 向下移动
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}

}
