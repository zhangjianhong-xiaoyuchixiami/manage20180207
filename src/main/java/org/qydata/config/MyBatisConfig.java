//package org.qydata.config;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.qydata.config.dataSource.DatabaseType;
//import org.qydata.config.dataSource.DynamicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * MyBatis基础配置
// *
// */
//@Configuration
//@EnableTransactionManagement
//public class MyBatisConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource masterDataSource() throws Exception {
//        Properties props = new Properties();
//        props.put("driverClassName", env.getProperty("master.datasource.driver-class-name"));
//        props.put("url", env.getProperty("master.datasource.url"));
//        props.put("username", env.getProperty("master.datasource.username"));
//        props.put("password", env.getProperty("master.datasource.password"));
//        props.put("maxActive", env.getProperty("datasource.maxActive"));
//        props.put("minIdle", env.getProperty("datasource.minIdle"));
//        props.put("initialSize", env.getProperty("datasource.initialSize"));
//        props.put("maxWait", env.getProperty("datasource.maxWait"));
//        return DruidDataSourceFactory.createDataSource(props);
//    }
//
//    @Bean
//    public DataSource slaveDataSource() throws Exception {
//        Properties props = new Properties();
//        props.put("driverClassName", env.getProperty("slave.datasource.driver-class-name"));
//        props.put("url", env.getProperty("slave.datasource.url"));
//        props.put("username", env.getProperty("slave.datasource.username"));
//        props.put("password", env.getProperty("slave.datasource.password"));
//        props.put("maxActive", env.getProperty("datasource.maxActive"));
//        props.put("minIdle", env.getProperty("datasource.minIdle"));
//        props.put("initialSize", env.getProperty("datasource.initialSize"));
//        props.put("maxWait", env.getProperty("datasource.maxWait"));
//        return DruidDataSourceFactory.createDataSource(props);
//    }
//
//    /**
//     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
//     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
//     */
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DatabaseType.master, masterDataSource);
//        targetDataSources.put(DatabaseType.slave, slaveDataSource);
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置为masterDataSource
//        return dataSource;
//    }
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource ds) {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(ds);
//        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            //定义扫描的xml文件
//            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 配置事务管理器
//     */
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//}