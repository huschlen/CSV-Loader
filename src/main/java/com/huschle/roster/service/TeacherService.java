package com.huschle.roster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huschle.roster.dao.ITeacherDao;
import com.huschle.roster.entity.Teacher;

@Service
public class TeacherService implements ITeacherService {
  
    @Autowired
    ITeacherDao teacherDao;

    @Override
    public void addTeacher(Teacher teacher) {
        teacherDao.addTeacher(teacher);
    }
    
    @Override
    public Teacher getTeacherById(int teacherId) {
        return teacherDao.getTeacherById(teacherId);
    }
    
    @Override
    public void setTeacherDao(ITeacherDao dao) {
        this.teacherDao = dao;
    }

}