package com.huschle.roster.service;

import com.huschle.roster.dao.IStudentDao;
import com.huschle.roster.entity.Student;

public interface IStudentService {

    Student getStudentById(int studentId);
    void addStudent(Student student);
    void deleteStudent(Student student);
    void setStudentDao(IStudentDao studentDao);

}
