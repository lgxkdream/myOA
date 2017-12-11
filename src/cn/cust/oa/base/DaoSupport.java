package cn.cust.oa.base;

import java.util.List;

import cn.cust.oa.domain.PageBean;
import cn.cust.oa.util.QueryHelper;

/**
 * 基本功能接口
 * 
 * @author LiGang
 * @date 2016-12-15 下午06:05:45
 * @version v1.0
 * @param <T>
 */
public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询实体
	 * 
	 * @param id
	 * @return
	 */
	T findById(Long id);

	/**
	 * 按id查询实体
	 * 
	 * @param id
	 * @return
	 */
	List<T> findByIds(Long[] id);

	/**
	 * 查询所有实体
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 查询分页信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param hql
	 * @param parameters
	 * @return
	 */
	@Deprecated
	PageBean<T> findPageBean(int currentPage, int pageSize, String hql,
			List<Object> parameters);

	/**
	 * 查询分页信息(运用QueryHelper）
	 * @param currentPage 当前页
	 * @param pageSize 每页显示条数
	 * @param qHelper HQL语句及参数列表
	 * @return
	 */
	PageBean<T> findPageBean(int currentPage, int pageSize,
			QueryHelper qHelper);
}
