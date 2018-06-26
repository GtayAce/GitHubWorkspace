package ssm.DaoImp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.Dao.UserDao;
import ssm.po.User;

public class UserDaoImpTest {
	
	private ApplicationContext applicationContext;
	
	//在setUp()方法中得到spring的容器
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void testFindUserById() throws Exception {
		//getBean()方法的参数是applicationContext.xml中的原始Dao接口的id
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.findUserById(102);
		System.out.println(user);
	}

}
