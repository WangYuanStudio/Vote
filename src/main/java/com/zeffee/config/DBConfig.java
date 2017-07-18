package com.zeffee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by zeffee on 2017/5/24.
 */
@Configuration
@EnableTransactionManagement
public class DBConfig {
    private static final String DB_NAME = "vote";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true&amp;characterEncoding=UTF-8&useSSL=false");
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.zeffee");
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.MysqlDialect");

//        properties.put("hibernate.show_sql", true);
//        properties.put("hibernate.format_sql", true);

        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory().getObject());
    }
}
