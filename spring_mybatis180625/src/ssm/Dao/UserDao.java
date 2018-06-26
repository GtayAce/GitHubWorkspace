package ssm.Dao;

import ssm.po.User;

public interface UserDao {
	abstract public User findUserById(int id) throws Exception;
}
