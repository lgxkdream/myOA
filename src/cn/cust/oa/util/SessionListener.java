package cn.cust.oa.util;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 统计在线人数的session监听器
 * 
 * @author LiGang
 * @date 2017-1-2 下午06:53:52
 * @version v1.0
 */
public class SessionListener extends ActionSupport implements
		HttpSessionAttributeListener {

	private static final long serialVersionUID = 4287713710273418645L;

	private static int activeSessions = 0;
	// private static List<Long> userList = new ArrayList<Long>();

	// public void sessionCreated(HttpSessionEvent hte) {
	// activeSessions++;
	// }
	//	
	// public void sessionDestroyed(HttpSessionEvent hte) {
	// if (activeSessions > 0)
	// activeSessions--;
	// }

	// session创建时执行
	public void attributeAdded(HttpSessionBindingEvent se) {
		// User user = (User) se.getSession().getAttribute("user");
		// if (!userList.contains(user.getId())) {
		// userList.add(user.getId());
		// }
		activeSessions++;
		System.out.println("session创建：" + activeSessions);
		//se.getSession().setAttribute("activeSessions", activeSessions);
		
	}

	// session销毁时执行
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (activeSessions > 0)
			activeSessions--;
		System.out.println("session销毁：" + activeSessions);
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("session覆盖：" + activeSessions);
	}

	// 获取活动的session个数(在线人数)
	public static int getActiveSessions() {
		return activeSessions;
	}

}
