package com.huschle.roster.service;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.huschle.roster.dao.ITeacherDao;
import com.huschle.roster.entity.Teacher;

public class TeacherServiceTest {
  
    @Mock
    private ITeacherDao teacherDao;
    
    @Mock
    private ITeacherService teacherService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teacherService.setTeacherDao(teacherDao);
    }
  
    //@Test
    public void testAddStudent() {
      Teacher teacher = getTestTeacher();
      Mockito.doNothing().when(teacherService).addTeacher(teacher);
      Mockito.verify(teacherDao, Mockito.times(1)).addTeacher(teacher);
    }

    private Teacher getTestTeacher() {
      Teacher teacher = new Teacher();
      teacher.setId(11111);
      teacher.setFirstName("Dave");
      teacher.setLastName("Lee");
      return teacher;
  }
}
