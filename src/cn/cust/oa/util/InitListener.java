package cn.cust.oa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.cust.oa.domain.Privilege;
import cn.cust.oa.service.PrivilegeService;

/**
 * 初始化监听器（用于初始化左侧菜单栏）
 * 
 * @author LiGang
 * @date 2016-12-15 下午09:24:02
 * @version v1.0
 */
public class InitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// 获取容器与相关的Service对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");
		// 准备顶级权限数据：topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("============>已准备左侧菜单栏数据<============");
		
		//准备所有权限Url数据：allPrivilegeUrls
		Collection<String> allPrivilegeUrls = privilegeService.findAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls",
				allPrivilegeUrls);
		System.out.println("============>已准备所有权限Url数据<============");
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
