package com.huschle.roster.dao;

import com.huschle.roster.entity.Student;

public interface IStudentDao {

    Student getStudentById(int studentId);
    void addStudent(Student student);
    void deleteStudent(Student student);

}
