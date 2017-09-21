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
import com.huschle.roster.entity.Teacher;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentConfig.class)
@Rollback(true)
@Transactional
public class TeacherDaoTest {

    @Autowired
    ITeacherDao teacherDao;
    
    @Autowired
    private HibernateTemplate  hibernateTemplate;
    
    //@Test
    public void testAddTeacher() {
        Teacher teacher = getTestTeacher();
        teacherDao.addTeacher(teacher);
        assertTrue(hibernateTemplate.contains(teacher));
    }
    
    private Teacher getTestTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(11111);
        teacher.setFirstName("Naoko");
        teacher.setLastName("Huschle");
        return teacher;
    }

}
