package ssm.DaoImp;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ssm.Dao.UserDao;
import ssm.po.User;

public class UserDaoImp extends SqlSessionDaoSupport implements UserDao {
	
	public User findUserById(int id) throws Exception {
		// 继承SqlSessionDaoSupport,通过this.getSQLSession()来获得SqlSession
		SqlSession sqlSession = this.getSqlSession();
		User user = null;
		user = sqlSession.selectOne("test.findUserByID", id);
		// 通过spring管理,方法结束后自动sqlSession.close(),所以可以没有
		return user;
	}
}
