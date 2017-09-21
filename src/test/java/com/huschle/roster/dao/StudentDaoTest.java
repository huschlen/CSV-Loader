package com.huschle.roster.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.annotation.*;

import com.huschle.roster.config.PersistentConfig;
import com.huschle.roster.entity.Student;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentConfig.class)
@Rollback(true)
@Transactional
public class StudentDaoTest {
  
    @Autowired
    private IStudentDao studentDao;
    
    @Autowired
    private HibernateTemplate  hibernateTemplate;
    
    //@Test
    public void testGetStudentById() {
        Student student = getTestStudent();
        hibernateTemplate.saveOrUpdate(student);
        Student foundStudent = studentDao.getStudentById(1111111);
        assertEquals(student, foundStudent);
    }
    
    //@Test
    public void testAddStudent() {
        Student student = getTestStudent();
        studentDao.addStudent(student);
        assertTrue(hibernateTemplate.contains(student));
    }
    
    //@Test
    public void testDeleteStudent() {
        Student student = getTestStudent();
        hibernateTemplate.saveOrUpdate(student);
        studentDao.deleteStudent(student);
        assertFalse(hibernateTemplate.contains(student));
    }
    
    private Student getTestStudent() {
        Student student = new Student();
        student.setId(1111111);
        student.setFirstName("Naoko");
        student.setLastName("Huschle");
        return student;
    }

}
