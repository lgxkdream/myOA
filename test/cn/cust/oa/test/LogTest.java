package cn.cust.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogTest {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Test
	public void test() throws Exception {
		log.debug("这是debug信息");
	}
}
