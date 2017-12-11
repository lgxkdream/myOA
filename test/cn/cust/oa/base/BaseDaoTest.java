package cn.cust.oa.base;

import static org.junit.Assert.fail;

import org.junit.Test;

import cn.cust.oa.dao.RoleDao;
import cn.cust.oa.dao.UserDao;
import cn.cust.oa.dao.impl.RoleDaoImpl;
import cn.cust.oa.dao.impl.UserDaoImpl;

@SuppressWarnings({"unused","deprecation"})
public class BaseDaoTest {

	@Test
	public void testSave() {
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

}
