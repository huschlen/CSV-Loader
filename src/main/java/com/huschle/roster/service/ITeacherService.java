package com.huschle.roster.service;

import com.huschle.roster.dao.ITeacherDao;
import com.huschle.roster.entity.Teacher;

public interface ITeacherService {

    void addTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId);
    void setTeacherDao(ITeacherDao teacherDao);

}
