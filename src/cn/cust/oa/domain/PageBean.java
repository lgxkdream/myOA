package cn.cust.oa.domain;

import java.util.List;

/**
 * 分页信息
 * 
 * @author LiGang
 * @date 2016-12-24 上午11:28:14
 * @version v1.0
 */
public class PageBean<T> {

	// 指定的或页面参数
	private int currentPage; // 当前页
	private int pageSize; // 每页显示条数
	// 查询数据库
	private int recordCount; // 总记录数
	private List<T> recordList; // 本页的数据列表
	// 计算出来的
	private int pageCount; // 总页数
	private int beginPageIndex; // 页码列表的开始索引(包含)
	private int endPageIndex; // 页码列表的结束索引(包含)

	/**
	 * 之接收前4个必要属性，自动计算出其他3个属性的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int pageSize, int recordCount,
			List<T> recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		// 计算总页码
		this.pageCount = (recordCount + pageSize - 1) / pageSize;
		// 计算页码列表的开始索引和结束索引
		if (pageCount <= 10) {
			// 总页数不大于10
			this.beginPageIndex = 1;
			this.endPageIndex = pageCount;
		} else {
			// 总页数大于10
			this.beginPageIndex = currentPage - 4;
			this.endPageIndex = currentPage + 5;
			if (beginPageIndex < 1) {
				//当前页码不足4
				this.beginPageIndex = 1;
				this.endPageIndex = 10;
			}
			if (endPageIndex > pageCount) {
				//后面页码不足5
				this.beginPageIndex = pageCount - 9;
				this.endPageIndex = pageCount;
			}
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
