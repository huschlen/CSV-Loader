package com.huschle.roster.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.huschle.roster.entity.Enrollment;
import com.huschle.roster.entity.Student;
import com.huschle.roster.entity.Teacher;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class PersistentConfig {
  
    @Autowired
    private Environment env;
   
    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory());
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(getDataSource())
           .addAnnotatedClasses(Student.class, Teacher.class, Enrollment.class)
           .buildSessionFactory();
    }
    
    @Bean
    public DataSource getDataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
            dataSource.setUrl(env.getProperty("spring.datasource.url"));
            dataSource.setUsername(env.getProperty("spring.datasource.username"));
            dataSource.setPassword(env.getProperty("spring.datasource.password"));
            return dataSource;
    }
    
    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }

}
