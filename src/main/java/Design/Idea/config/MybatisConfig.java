//package Design.Idea.config;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@Lazy
//@RequiredArgsConstructor
//@MapperScan(basePackages = "Design.Idea.mapper")
//public class MybatisConfig {
//
//    private final ApplicationContext ac; //Spring Container
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        //데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 가장 중요한 객체
//        //DataSource를 참조하여 MyBatis와 Mysql 서버를 연동시켜준다
//
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//
//        sessionFactory.setDataSource(dataSource);
////        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//
//        sessionFactory.setMapperLocations(
//                ac.getResources( // 1
//                        // 실제 쿼리가 들어갈 xml 패키지 경로
//                        "classpath:/mapper/*.xml"
//                ));
//
//        // Value Object를 선언해 놓은 package 경로
//        // Mapper의 result, parameterType의 패키지 경로를 클래스만 작성 할 수 있도록 도와줌.
//        sessionFactory.setTypeAliasesPackage( "Design.Idea.model" );
//        return sessionFactory.getObject();
//    }
//
//    // Mybatis Template
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
//        //Template Pattern 중 하나. SQL 실행만 담당.
//        //나머지 일은 SqlSessionFactoryBean, Mapper.xml 정보를 DI로 주입받아 사용
//
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
//
//        sqlSessionTemplate.getConfiguration().setMapUnderscoreToCamelCase(false);
//        //이 설정을 false로 하였기때문에 TEST code 통과하였음
//        //전통적인 데이터베이스 컬럼명 형태인 A_COLUMN을 CamelCase형태의 자바 프로퍼티명 형태인 aColumn으로 자동 mapping
//        //ex) login_id -> loginid
//
////      sqlSessionTemplate.getConfiguration().setUseGeneratedKeys(true);
//        return sqlSessionTemplate;
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}