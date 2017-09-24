package com.huschle.roster.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.test.annotation.Rollback;

import com.huschle.roster.dao.IEnrollmentDao;
import com.huschle.roster.entity.Enrollment;
import com.huschle.roster.entity.Student;
import com.huschle.roster.entity.Teacher;

public class EnrollmentServiceTest {

    @Mock
    private IEnrollmentDao enrollmentDao;
    
    @Mock
    private IEnrollmentService enrollmentService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        enrollmentService = new EnrollmentService();
        enrollmentService.setEnrollmentDao(enrollmentDao);
    }
  
    @Test
    public void testAddEnrollment() {
        Student student = getTestStudent();
        Teacher teacher = getTestTeacher();
        Enrollment enrollment = new Enrollment(student, teacher);
        enrollmentService.addEnrollment(enrollment);
        Mockito.verify(enrollmentDao, Mockito.times(1)).addEnrollment(enrollment);
    }
    
    @Test
    public void testDeleteEnrollment() {
        Student student = getTestStudent();
        Teacher teacher = getTestTeacher();
        Enrollment enrollment = new Enrollment(student, teacher);
        enrollmentService.deleteEnrollment(enrollment);
        Mockito.verify(enrollmentDao, Mockito.times(1)).deleteEnrollment(enrollment);
    }
    
    @Test
    public void testGetEnrollmentInfo() {
        Mockito.when(enrollmentDao.getEnrollmentInfo()).thenReturn(getExpectedEnrollment());
        List<Object[]> resultList = enrollmentService.getEnrollmentInfo();
        Mockito.verify(enrollmentDao, Mockito.times(1)).getEnrollmentInfo();
        assertEquals(getExpectedEnrollment().get(0)[0], resultList.get(0)[0]);
        
    }
    
    private List<Object[]> getExpectedEnrollment() {
        List<Object[]> expectedEnrollment = new ArrayList<Object[]>();
        Object obj[] = {getTestTeacher(), new Integer("1")};
        expectedEnrollment.add(0, obj);
        return expectedEnrollment;
    }
    
    private Student getTestStudent() {
        Student student = new Student();
        student.setId(1111111);
        student.setFirstName("Naoko");
        student.setLastName("Huschle");
        return student;
    }
    
    private Teacher getTestTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(11111);
        teacher.setFirstName("Nara");
        teacher.setLastName("Teacher");
        return teacher;
    }

}
