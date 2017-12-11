package cn.cust.oa.util;

import cn.cust.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4607262384812725024L;

	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privilegeUrl = namespace + actionName;
		if (user == null) {
			// 如果未登录，就转到登陆页面
			if (privilegeUrl.startsWith("/user_login")) {
				// 如果是去登陆，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登陆，就转到登陆页面
				return "loginUI";
			}
		} else {
			// 如果已登陆，就判断权限
			if (user.hasPrivilegeByUrl(privilegeUrl)) {
				// 如果有权限，就放行
				return invocation.invoke();
			} else {
				// 如果无权限，就转到提示页面
				return "noPrivilegeError";
			}

		}
	}
}
