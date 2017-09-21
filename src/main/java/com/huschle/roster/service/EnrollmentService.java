package com.huschle.roster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huschle.roster.dao.IEnrollmentDao;
import com.huschle.roster.entity.Enrollment;

@Service
public class EnrollmentService implements IEnrollmentService {
  
  @Autowired
  IEnrollmentDao enrollmentDao;

  @Override
  public void addEnrollment(Enrollment enrollment) {
      enrollmentDao.addEnrollment(enrollment);
  }

  @Override
  public void deleteEnrollment(Enrollment enrollment) {
      enrollmentDao.deleteEnrollment(enrollment);
  }

  /*@Override
  public List<Object[]> getEnrollmentInfo() {
      return enrollmentDao.getEnrollmentInfo();
  }*/
  
  @Override
  public void setEnrollmentDao(IEnrollmentDao dao) {
      this.enrollmentDao = dao;
  }

}
