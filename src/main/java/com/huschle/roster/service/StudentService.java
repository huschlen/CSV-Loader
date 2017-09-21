package com.huschle.roster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huschle.roster.dao.IStudentDao;
import com.huschle.roster.entity.Student;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentDao studentDao;

    @Override
    public Student getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.deleteStudent(student);
    }
    
    @Override
    public void setStudentDao(IStudentDao dao) {
        this.studentDao = dao;
    }

}
