package cn.cust.oa.test;

import org.junit.Test;

import cn.cust.oa.domain.Forum;
import cn.cust.oa.domain.Topic;
import cn.cust.oa.util.QueryHelper;


public class TestQueryHelper {

	private Forum forum = new Forum();
	private int viewType = 1;
	private int orderBy =0;
	private boolean reverse = false;
	
	@Test
	public void testQueryHelper() throws Exception {
		QueryHelper qHelper = new QueryHelper(Topic.class, "t")
		.addCondition("t.forum=?", forum)
		.addCondition(viewType == 1,"t.type=?", Topic.TYPE_BEST)
		.addOrderProperty(orderBy == 1, "t.lastUpdateTime", reverse)
		.addOrderProperty(orderBy == 2, "t.postTime", reverse)
		.addOrderProperty(orderBy == 3, "t.replyCount", reverse)
		.addOrderProperty(orderBy == 0, "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)
		.addOrderProperty(orderBy == 0, "t.lastUpdateTime", false);
		System.out.println(qHelper.getListQueryHql());
		System.out.println(qHelper.getCountQueryHql());
		System.out.println(qHelper.getParameters());
	}
	//FROM Topic t WHERE t.forum AND t.type=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC
	//SELECT COUNT(*) FROM Topic t WHERE t.forum AND t.type=?
    //[cn.cust.oa.domain.Forum@114ef62, 1]
}
