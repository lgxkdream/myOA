package cn.cust.oa.service;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * 根据用户名与密码(明文)查询用户（用于登录）
	 * 
	 * @param LoginName
	 * @param Password
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
