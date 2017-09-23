package com.huschle.roster.service;

import java.util.List;

import com.huschle.roster.dao.IEnrollmentDao;
import com.huschle.roster.entity.Enrollment;

public interface IEnrollmentService {

    void addEnrollment(Enrollment enrollment);
    void deleteEnrollment(Enrollment enrollment);
    List<Object[]> getEnrollmentInfo();
    void setEnrollmentDao(IEnrollmentDao enrollmentDao);

}
