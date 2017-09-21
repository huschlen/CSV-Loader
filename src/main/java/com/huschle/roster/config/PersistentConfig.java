package com.huschle.roster.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import org.hibernate.SessionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.huschle.roster.entity.Enrollment;
import com.huschle.roster.entity.Student;
import com.huschle.roster.entity.Teacher;


@Configuration
@EnableTransactionManagement
public class PersistentConfig {
   
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
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/rosters");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
    }
    
    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }

}
