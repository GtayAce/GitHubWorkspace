package ssm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.po.User;

public class UserMapperTest {

private ApplicationContext applicationContext;
	
	//��setUp()�����еõ�spring������
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void testFindUserById() throws Exception {
		//getBean()�����Ĳ�����applicationContext.xml�е�ԭʼDao�ӿڵ�id
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = userMapper.findUserById(102);
		System.out.println(user);
	}
	
	@Test
	public void testFingUserByUsername() throws Exception {
		//getBean()�����Ĳ�����applicationContext.xml�е�ԭʼDao�ӿڵ�id
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		String username = "%ѩ%";
		List<User> userList = userMapper.fingUserByUsername(username);
		System.out.println(userList);
	}
	
	@Test
	public void testFingUserByUsernameAndSex() throws Exception {
		//getBean()�����Ĳ�����applicationContext.xml�е�ԭʼDao�ӿڵ�id
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = new User();
		user.setUsername("%ѩ%");
		user.setSex("2");
		List<User> userList = userMapper.fingUserByUsernameAndSex(user);
		System.out.println(userList);
	}
	
	@Test
	public void testFindUserByIdList() throws Exception {
		//getBean()�����Ĳ�����applicationContext.xml�е�ԭʼDao�ӿڵ�id
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		List<Integer> list = new ArrayList<Integer>();
		list.add(32);
		list.add(97);
		list.add(87);
		list.add(196);
		list.add(149);
		List<User> userList = userMapper.findUserByIdList(list);
		System.out.println(userList);
	}
	
	@Test
	public void testFindUserOrdersResultMap() throws Exception {
		//getBean()�����Ĳ�����applicationContext.xml�е�ԭʼDao�ӿڵ�id
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(118);
		List<User> userList = userMapper.findUserOrdersResultMap(list);
		System.out.println(userList);
	}
	
	@Test
	public void testFindAllUserShoppingInventory() throws Exception {
		//getBean()�����Ĳ�����applicationContext.xml�е�ԭʼDao�ӿڵ�id
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		List<User> userList = userMapper.findAllUserShoppingInventory();
		System.out.println(userList);
	}
}
