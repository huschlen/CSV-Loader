package com.huschle.roster.dao;

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
import com.huschle.roster.entity.Enrollment;
import com.huschle.roster.entity.Student;
import com.huschle.roster.entity.Teacher;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentConfig.class)
@Rollback(true)
@Transactional
public class EnrollmentDaoTest {

    @Autowired
    private IEnrollmentDao enrollmentDao;
    
    @Autowired
    private HibernateTemplate  hibernateTemplate;
    
    //@Test
    public void testAddEnrollment() {
        Student student = getTestStudent();
        Teacher teacher = getTestTeacher();
        Enrollment enrollment = new Enrollment(student, teacher);
        enrollmentDao.addEnrollment(enrollment);
        assertTrue(hibernateTemplate.contains(enrollment));
    }
    
    //@Test
    public void testDeleteEnrollment() {
        Student student = getTestStudent();
        Teacher teacher = getTestTeacher();
        Enrollment enrollment = new Enrollment(student, teacher);
        enrollmentDao.deleteEnrollment(enrollment);
        assertFalse(hibernateTemplate.contains(enrollment));
    }
    
    private Student getTestStudent() {
        Student student = new Student();
        student.setId(1111111);
        student.setFirstName("Naoko");
        student.setLastName("Huschle");
        return student;
    }
    
    private Teacher getTestTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(11111);
        teacher.setFirstName("Nara");
        teacher.setLastName("Teacher");
        return teacher;
    }

}
