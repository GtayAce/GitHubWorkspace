package ssm.DaoImp;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ssm.Dao.UserDao;
import ssm.po.User;

public class UserDaoImp extends SqlSessionDaoSupport implements UserDao {
	
	public User findUserById(int id) throws Exception {
		// �̳�SqlSessionDaoSupport,ͨ��this.getSQLSession()�����SqlSession
		SqlSession sqlSession = this.getSqlSession();
		User user = null;
		user = sqlSession.selectOne("test.findUserByID", id);
		// ͨ��spring����,�����������Զ�sqlSession.close(),���Կ���û��
		return user;
	}
}
