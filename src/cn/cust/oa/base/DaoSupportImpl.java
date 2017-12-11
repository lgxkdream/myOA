package cn.cust.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.domain.PageBean;
import cn.cust.oa.util.QueryHelper;

/**
 * 基本功能是实现类
 * 
 * @author LiGang
 * @date 2016-12-15 下午06:06:54
 * @version v1.0
 * @param <T>
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	protected SessionFactory sessionFactory;
	private Class<T> clazz = null;

	public DaoSupportImpl() {
		// 使用反射得到T的真实类型
		// 获取当前new的对象的泛型的父类类型
		// System.out.println("this:" + this);
		// cn.cust.oa.dao.impl.UserDaoImpl@bdec44
		// System.out.println("this.getClass():" + this.getClass()); class
		// cn.cust.oa.dao.impl.UserDaoImpl
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		System.out.println("pt:" + pt); // cn.cust.oa.base.BaseDaoImpl<cn.cust.oa.domain.User>
		// Type type = this.getClass().getGenericSuperclass();
		// System.out.println("type:" + type);
		// 获取第一个类型参数的真实类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("clazz:" + clazz); // class cn.cust.oa.domain.User
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 删除实体
	 */
	public void delete(Long id) {
		Object obj = findById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	/**
	 * 查询所有实体
	 */
	public List<T> findAll() {
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	/**
	 * 按id查询实体
	 */
	public T findById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	/**
	 * 按id查询实体
	 */
	public List<T> findByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST; // 特别注意的地方！
		} else {
			return getSession().createQuery(
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")
					.setParameterList("ids", ids).list();
		}
	}

	/**
	 * 保存实体
	 */
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 更新实体
	 */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 查询分页信息
	 */
	@Deprecated
	public PageBean<T> findPageBean(int currentPage, int pageSize, String hql,
			List<Object> parameters) {
		Query listQuery = getSession().createQuery(hql);
		if (!parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		List<T> recordList = listQuery.setFirstResult(
				(currentPage - 1) * pageSize).setMaxResults(pageSize).list();

		Query countQuery = getSession().createQuery("SELECT COUNT(*) " + hql);
		if (!parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long recordCount = (Long) countQuery.uniqueResult();
		return new PageBean<T>(currentPage, pageSize, recordCount.intValue(),
				recordList);
	}

	/**
	 * 查询分页信息(运用QueryHelper）
	 */
	public PageBean<T> findPageBean(int currentPage, int pageSize,
			QueryHelper qHelper) {
		List<Object> parameters = qHelper.getParameters();

		Query listQuery = getSession().createQuery(qHelper.getListQueryHql());
		if (!parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		List<T> recordList = listQuery.setFirstResult(
				(currentPage - 1) * pageSize).setMaxResults(pageSize).list();

		Query countQuery = getSession().createQuery(qHelper.getCountQueryHql());
		if (!parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long recordCount = (Long) countQuery.uniqueResult();

		return new PageBean<T>(currentPage, pageSize, recordCount.intValue(),
				recordList);
	}

}
