package ssm.mapper;

import java.util.List;

import ssm.po.User;

public interface UserMapper {
	public User findUserById(int id) throws Exception;
	public List<User> fingUserByUsername(String username) throws Exception;
	public List<User> fingUserByUsernameAndSex(User user) throws Exception;
	public List<User> findUserByIdList(List<Integer> list) throws Exception;
	public List<User> findUserByIdListResultMap(List<Integer> list) throws Exception;
	public List<User> findUserOrdersResultMap(List<Integer> list) throws Exception;
	public List<User> findAllUserShoppingInventory() throws Exception;
}

