package cn.cust.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.cust.oa.domain.Department;

/**
 * 部门工具类
 * 
 * @author LiGang
 * @date 2016-12-11 下午10:12:33
 * @version v1.0
 */
public class DepartmentUtils {

	/**
	 * 遍历部门树，把所有的部门遍历出来放到同一个集合中返回，并且其中所有部门的名称都改了以表示层次
	 * 
	 * @param topList
	 * @return
	 */
	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		getDepartmentTreeList(topList, "┣", list);
		return list;
	}

	private static void getDepartmentTreeList(Collection<Department> topList,
			String prefix, List<Department> list) {
		for (Department top : topList) {
			Department copy = new Department(); //使用副本，防止在session中的原对象被更改
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy);
			getDepartmentTreeList(top.getChildren(), "　　" + prefix, list);
		}
	}

}
