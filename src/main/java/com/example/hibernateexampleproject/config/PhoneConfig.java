package com.example.hibernateexampleproject.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PhoneConfig {
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/hibernatedb?createDatabaseIfNotExist=true");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }



    @Bean
    public SessionFactory getSessionFactory() throws IOException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("com.example.hibernateexampleproject.model");
        factoryBean.setHibernateProperties(additionalProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(SessionFactory factory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(factory);
//        return transactionManager;
//    }
}
