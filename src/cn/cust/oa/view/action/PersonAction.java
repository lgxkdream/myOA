package cn.cust.oa.view.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.User;
import cn.cust.oa.util.UploadUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 个人设置
 * 
 * @author LiGang
 * @date 2016-12-27 下午05:48:12
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class PersonAction extends BaseAction<User> {

	private static final long serialVersionUID = 7433193622213603802L;

	/**
	 * 个人信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUserInfoUI() throws Exception {
		User user = userService.findById(getCurrentUser().getId());
		ActionContext.getContext().getValueStack().push(user);
		return "editUserInfoUI";
	}

	/**
	 * 密码修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editPasswordUI() throws Exception {
		return "editPasswordUI";
	}

	/**
	 * 密码修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editPassword() throws Exception {
		User user = getCurrentUser();
		if (user.getPassword().equals(DigestUtils.md5Hex(getOldPassword()))) {
			user.setPassword(DigestUtils.md5Hex(model.getPassword()));
			userService.update(user);
			addActionMessage("密码修改成功！");
		} else {
			addActionError("对不起，原密码输入错误！");
		}
		return "editPasswordUI";
	}

	/**
	 * 头像修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editHeadimg() throws Exception {
		User user = getCurrentUser();
		String url = UploadUtils.uploadFile(resource, resourceFileName, "/upload/headimg");
		user.setHeadimg(url);
		userService.update(user);
		return "toEditUserInfoUI";
	}

}
