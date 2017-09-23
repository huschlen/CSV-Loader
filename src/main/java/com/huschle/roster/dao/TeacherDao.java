package com.huschle.roster.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huschle.roster.entity.Teacher;

@Transactional
@Repository
public class TeacherDao implements ITeacherDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addTeacher(Teacher teacher) {
        hibernateTemplate.saveOrUpdate(teacher);
    }
    
    @Override
    public Teacher getTeacherById(int teacherId) {
        return hibernateTemplate.get(Teacher.class, teacherId);
    }

}
