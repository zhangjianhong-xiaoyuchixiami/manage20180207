//package org.qydata.config;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.List;
//
///**
// *
// * 获取第二个数据库的连接信息，在application.yml中配置，并指定特定的前缀
// *
// */
//@Configuration
//@ConditionalOnClass({ EnableTransactionManagement.class })
//@AutoConfigureAfter({ DataBaseConfiguration.class })
//public class MybatisConfiguration implements EnvironmentAware{
//
//    private static Log logger = LogFactory.getLog(MybatisConfiguration.class);
//
//    private RelaxedPropertyResolver propertyResolver;
//
//    @Resource(name="writeDataSource")
//    private DataSource writeDataSource;
//
//    @Resource(name="readDataSources")
//    private List<DataSource> readDataSources;
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public SqlSessionFactory sqlSessionFactory() {
//            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//            sessionFactory.setDataSource(roundRobinDataSouceProxy());
//            sessionFactory.setTypeAliasesPackage("org.qydata.entity");
//            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            try {
//                //定义扫描的xml文件
//                sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//                return sessionFactory.getObject();
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//    }
//
//    @Bean
//    public RoundRobinRWRoutingDataSourceProxy roundRobinDataSouceProxy(){
//        RoundRobinRWRoutingDataSourceProxy proxy = new RoundRobinRWRoutingDataSourceProxy();
//        proxy.setWriteDataSource(writeDataSource);
//        proxy.setReadDataSoures(readDataSources);
//        proxy.setReadKey("READ");
//        proxy.setWriteKey("WRITE");
//        return proxy;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(writeDataSource);
//    }
//}