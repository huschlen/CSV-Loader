package com.huschle.roster.dao;

import java.util.List;

import com.huschle.roster.entity.Enrollment;

public interface IEnrollmentDao {

    void addEnrollment(Enrollment enrollment);
    void deleteEnrollment(Enrollment enrollment);
    List<Object[]> getEnrollmentInfo();

}
