package com.huschle.roster.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.huschle.roster.dao.ITeacherDao;
import com.huschle.roster.entity.Student;
import com.huschle.roster.entity.Teacher;

public class TeacherServiceTest {
  
    @Mock
    private ITeacherDao teacherDao;
    
    @Mock
    private ITeacherService teacherService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teacherService = new TeacherService();
        teacherService.setTeacherDao(teacherDao);
    }
  
    @Test
    public void testAddTeacher() {
        Teacher teacher = getTestTeacher();
        teacherService.addTeacher(teacher);
        Mockito.verify(teacherDao, Mockito.times(1)).addTeacher(teacher);
    }
    
    @Test
    public void testGetTeacherById() {
      Teacher teacher = getTestTeacher();
        Mockito.when(teacherDao.getTeacherById(teacher.getId())).thenReturn(teacher);
        assertEquals(teacher, teacherService.getTeacherById(11111));
    }

    private Teacher getTestTeacher() {
      Teacher teacher = new Teacher();
      teacher.setId(11111);
      teacher.setFirstName("Dave");
      teacher.setLastName("Lee");
      return teacher;
  }
}
