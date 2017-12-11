package cn.cust.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.User;
import cn.cust.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements
		UserService {

	/**
	 * 根据用户名密码查询用户（用于登录）
	 */
	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5Digest = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(
				"FROM User u WHERE u.loginName = ? AND password = ?")
				.setParameter(0, loginName).setParameter(1, md5Digest)
				.uniqueResult();
	}

}
