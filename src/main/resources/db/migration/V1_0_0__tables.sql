-- ========================================================
-- Tables
-- ========================================================

CREATE TABLE student (
    id numeric(24) NOT NULL PRIMARY KEY,
    state_id varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    gender char(1),
    birthdate date,
    race char(2),
    meal_status char(2),
    english_proficiency char(2),
    native_language char(5),
    service_code char(2),
    primary_disability_type char(2),
    iep_reading boolean,
    iep_math boolean,
    iep_behavior boolean,
    gifted_talented boolean,
    section504 boolean,
    mobility boolean
);

create table teacher (
    id numeric(24) NOT NULL PRIMARY KEY,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    school varchar(255)
);

create table enrollment (
    id int AUTO_INCREMENT PRIMARY KEY,
    student_id numeric(24) NOT NULL,
    teacher_id numeric(24) NOT NULL,
    grade char(2),
    course varchar(64),
    section varchar(16),
    UNIQUE (student_id, teacher_id),
    FOREIGN KEY (student_id) REFERENCES student (id)
    ON DELETE CASCADE
);