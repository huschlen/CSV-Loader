package com.huschle.roster.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "student")
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode
public class Student implements Serializable {

    private static final long serialVersionUID = -193171985179763576L;
    
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "state_id")
    private String stateId;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name="birthdate")
    private Date birthdate;
    
    @Column(name = "race")
    private String race;
    
    @Column(name = "meal_status")
    private String mealStatus;
    
    @Column(name = "english_proficiency")
    private String englishProficiency;

    @Column(name = "native_language")
    private String nativeLanguage;
    
    @Column(name = "service_code")
    private String serviceCode;
    
    @Column(name = "primary_disability_type")
    private String primaryDisabilityType;
    
    @Column(name = "iep_reading")
    private boolean iepReading;

    @Column(name = "iep_math")
    private boolean iepMath;
    
    @Column(name = "iep_behavior")
    private boolean iepBehavior;
    
    @Column(name = "gifted_talented")
    private boolean giftedTalented;
    
    @Column(name = "section504")
    private boolean section504;
    
    @Column(name = "mobility")
    private boolean mobility;
    
}
