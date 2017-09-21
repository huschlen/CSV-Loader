package com.huschle.roster.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huschle.roster.entity.Student;

@Transactional
@Repository
public class StudentDao implements IStudentDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Override
    public Student getStudentById(int studentId) {
        return hibernateTemplate.get(Student.class, studentId);
    }
    
    @Override
    public void addStudent(Student student) {
        hibernateTemplate.saveOrUpdate(student);
    }
    
    @Override
    public void deleteStudent(Student student) {
        hibernateTemplate.delete(student);
    }

}
