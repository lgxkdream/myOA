package cn.cust.oa.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListener implements HttpSessionListener {
	
	private static int onlineCount = 0;

	public void sessionCreated(HttpSessionEvent hse) {
		onlineCount++;
		System.out.println("OnlineListener之session创建：" + onlineCount);
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		if (onlineCount > 0)
			onlineCount--;
		System.out.println("OnlineListener之session销毁：" + onlineCount);
	}
	
	// 获取活动的session个数(在线人数)
	public static int getOnlineCount() {
		return onlineCount;
	}

}
