package Design.Idea;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class IdeaApplicationTests {

	//MyBatis 및 MySql 연결 확인 테스트 코드

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSqlSession() throws Exception {
		System.out.println(sqlSession.toString());
	}


	@Test
	public void testDataSourceConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
		connection.close();
	}

}