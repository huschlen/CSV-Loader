package com.huschle.roster.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.huschle.roster.dao.IStudentDao;
import com.huschle.roster.entity.Student;

public class StudentServiceTest {

    @Mock
    private IStudentDao studentDao;
    
    @Mock
    private IStudentService studentService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService();
        studentService.setStudentDao(studentDao);
    }
    
    //@Test
    public void testAddStudent() {
        Student student = getTestStudent();
        Mockito.doNothing().when(studentService).addStudent(student);
        Mockito.verify(studentDao, Mockito.times(1)).addStudent(student);
    }
    
    //@Test
    public void testDeleteStudent() {
        Student student = getTestStudent();
        studentService.deleteStudent(student);
        Mockito.verify(studentDao, Mockito.times(1)).deleteStudent(student);
    }
    
    @Test
    public void testGetStudentById() {
        Student student = getTestStudent();
        Mockito.when(studentService.getStudentById(student.getId())).thenReturn(student);
        assertEquals(student, studentService.getStudentById(7777777));
    }
    
    private Student getTestStudent() {
        Student student = new Student();
        student.setId(7777777);
        student.setFirstName("Dave");
        student.setLastName("Lee");
        return student;
    }

}
