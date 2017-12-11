package cn.cust.oa.test;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Test
	public void testBean() throws Exception {
		TestAction testAction = (TestAction) ac.getBean("testAction");
		System.out.println(testAction);
	}
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFatory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFatory);
	}
	
    @Test
	public void testTransaction() throws Exception {
		TestService testService = (TestService) ac.getBean("testService");
		//testService.saveTwoUsers();
		testService.saveUsers();
		System.out.println(testService);
	}
    
    @Test
    public void testProcessEngine() throws Exception {
    	ProcessEngine processEngine = (ProcessEngine) ac.getBean("processEngine");
    	System.out.println(processEngine);
    }
    
  /*  @Test
    public void testProcessDefinitionService(){
    	ProcessDefinitionServiceImpl processDefinition =  (ProcessDefinitionServiceImpl) ac.getBean("processDefinitionServiceImpl");
    	System.out.println(processDefinition.findLastVersion());
    }*/
    
}
