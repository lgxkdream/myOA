package cn.cust.oa.util;

import java.util.ArrayList;
import java.util.List;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.PageBean;

import com.opensymphony.xwork2.ActionContext;

/**
 * 辅助拼接HQL语句工具类
 * 
 * @author LiGang
 * @date 2016-12-25 上午11:12:10
 * @version v1.0
 */
public class QueryHelper {

	private String fromClause; // FROM字句
	private String whereClause = ""; // WHERE字句
	private String orderByClause = ""; // ORDER BY字句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 构造生成FROM字句
	 * 
	 * @param clazz
	 * @param alias
	 */
	@SuppressWarnings("unchecked")
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接WHERE字句
	 * 
	 * @param condition
	 * @param param
	 */
	public QueryHelper addCondition(String condition, Object... param) {
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		if (param != null) {
			for (Object p : param) {
				parameters.add(p);
			}
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接WHERE字句
	 * 
	 * @param append
	 * @param condition
	 * @param param
	 */
	public QueryHelper addCondition(boolean append, String condition,
			Object... param) {
		if (append) {
			addCondition(condition, param);
		}
		return this;
	}

	/**
	 * 拼接ORDER BY字句
	 * 
	 * @param propertyName
	 *            属性名
	 * @param reverse
	 *            升序true/降序false
	 */
	public QueryHelper addOrderProperty(String propertyName, boolean reverse) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName
					+ (reverse ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (reverse ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接ORDER BY字句
	 * 
	 * @param append
	 * @param propertyName
	 * @param reverse
	 */
	public QueryHelper addOrderProperty(boolean append, String propertyName,
			boolean reverse) {
		if (append) {
			addOrderProperty(propertyName, reverse);
		}
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getListQueryHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * 
	 * @return
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取HQL中的参数值列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * 准备分页信息并放到值栈顶
	 * 
	 * @param service
	 * @param currentPage
	 * @param pageSize
	 */
	public void preparePageBean(DaoSupport<?> service, int currentPage,
			int pageSize) {
		PageBean<?> pageBean = service
				.findPageBean(currentPage, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}

}
