DROP TABLE CAN_TAKE CASCADE CONSTRAINTS;
DROP TABLE MUST_TAKE CASCADE CONSTRAINTS;
DROP TABLE PRECEDES CASCADE CONSTRAINTS;
DROP TABLE PREREQS CASCADE CONSTRAINTS;
DROP TABLE ELECTIVES CASCADE CONSTRAINTS;
DROP TABLE MANDATORY CASCADE CONSTRAINTS;
DROP TABLE TOOK CASCADE CONSTRAINTS;
DROP TABLE TAKES CASCADE CONSTRAINTS;
DROP TABLE STUDIES CASCADE CONSTRAINTS;
DROP TABLE MAJORS CASCADE CONSTRAINTS;
DROP TABLE COURSES CASCADE CONSTRAINTS;
DROP TABLE STUDENTS CASCADE CONSTRAINTS;

CREATE TABLE STUDENTS (
	perm INTEGER,
	name CHAR(20),
	address CHAR(50),
	dept CHAR(20),
	pin INTEGER, 
    PRIMARY KEY (perm));

CREATE TABLE COURSES (
    course_no CHAR(10),
    enroll_code INTEGER,
    title CHAR(20),
    year INTEGER,
    quarter CHAR(10),
    location CHAR(50),
    time CHAR(20),
    capacity INTEGER,
    professor CHAR(20),
    PRIMARY KEY (course_no, enroll_code));

CREATE TABLE MAJORS (
    name CHAR(20),
    dept CHAR(50),
    num_electives INTEGER,
    PRIMARY KEY (name, dept));

CREATE TABLE STUDIES (
    perm INTEGER,
    name CHAR(20),
    dept CHAR(50),
    num_electives INTEGER,
    PRIMARY KEY (perm),
    FOREIGN KEY (perm) REFERENCES STUDENTS,
    FOREIGN KEY (name, dept) REFERENCES MAJORS);

CREATE TABLE TAKES (
    perm INTEGER,
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (perm, course_no, enroll_code),
    FOREIGN KEY (perm) REFERENCES STUDENTS,
    FOREIGN KEY (course_no, enroll_code) REFERENCES COURSES);


CREATE TABLE TOOK (
    perm INTEGER,
    course_no CHAR(10),
    enroll_code INTEGER,
    grade CHAR(2),
    PRIMARY KEY (perm, course_no, enroll_code),
    FOREIGN KEY (perm) REFERENCES STUDENTS,
    FOREIGN KEY (course_no, enroll_code) REFERENCES COURSES);


CREATE TABLE MANDATORY (
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (course_no, enroll_code),
    FOREIGN KEY (course_no, enroll_code) REFERENCES COURSES);

CREATE TABLE ELECTIVES (
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (course_no, enroll_code),
    FOREIGN KEY (course_no, enroll_code) REFERENCES COURSES);

CREATE TABLE PREREQS (
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (course_no, enroll_code),
    FOREIGN KEY (course_no, enroll_code) REFERENCES COURSES);

CREATE TABLE PRECEDES (
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (course_no, enroll_code),
    FOREIGN KEY (course_no, enroll_code) REFERENCES COURSES);

CREATE TABLE MUST_TAKE (
    name CHAR(20),
    dept CHAR(50),
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (course_no, enroll_code),
    FOREIGN KEY (name, dept) REFERENCES MAJORS,
    FOREIGN KEY (course_no, enroll_code) REFERENCES MANDATORY);

CREATE TABLE CAN_TAKE (
    name CHAR(20),
    dept CHAR(50),
    course_no CHAR(10),
    enroll_code INTEGER,
    PRIMARY KEY (course_no, enroll_code),
    FOREIGN KEY (name, dept) REFERENCES MAJORS,
    FOREIGN KEY (course_no, enroll_code) REFERENCES ELECTIVES);





