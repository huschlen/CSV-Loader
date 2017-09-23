package com.huschle.roster.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollment")
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Enrollment implements Serializable {

    private static final long serialVersionUID = -5489637103045994185L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    
    @Column(name = "grade")
    private String grade;
    
    @Column(name = "course")
    private String course;
    
    @Column(name = "section")
    private String section;
    
    public Enrollment(Student s, Teacher t) {
        this.student = s;
        this.teacher = t;
    }

}
