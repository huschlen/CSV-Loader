package com.huschle.roster.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode
public class Teacher implements Serializable {

    private static final long serialVersionUID = -6669523438220505962L;

    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "school")
    private String school;

}
