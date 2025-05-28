DELETE FROM MUST_TAKE;
DELETE FROM CAN_TAKE;
DELETE FROM PREREQS;
DELETE FROM ELECTIVES;
DELETE FROM MANDATORY;
DELETE FROM TAKES;
DELETE FROM TOOK;
DELETE FROM STUDIES;
DELETE FROM OFFERINGS;
DELETE FROM COURSES;
DELETE FROM STUDENTS;
DELETE FROM MAJORS;

INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('12345', 'Alfred Hitchcock', '6667 El Colegio #40', 'CS', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('14682', 'Billy Clinton', '5777 Hollister', 'ECE', '1e05e94f3dc87bcad898f8b5dba5df7c9f1567de45c37df14c3b9abfd00444e8');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('37642', 'Cindy Laugher', '7000 Hollister', 'CS', '6a35a9cb6bc3593a9f6ddf0be780f3130161921ec1b5047453b4a0777b6cd8d2');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('85821', 'David Copperfill', '1357 State St', 'CS', 'fbe22d6c76ebbb5598aa833e967ea6e0130e0502ad5aaae2ff4d19c8ed44b9c3');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('38567', 'Elizabeth Sailor', '4321 State St', 'ECE', '966e0a376157a20ec409b64bb39eb138c5e6085e91168644e088916cd9a3db15');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('81934', 'Fatal Castro', '3756 La Cumbre Plaza', 'CS', 'c264d2538698aceef2f7f127c8293439eb291c9d06da261bd837c58108c1038f');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('98246', 'George Brush', '5346 Foothill Av', 'CS', 'db8fc4150e0b6ff7e80684565ebe2eb4011a0b9292f3b4a6eef29010e6c54559');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('35328', 'Hurryson Ford', '678 State St', 'ECE', 'd4be6b6328194ad8c540824d6289dfd4555cad54ce00fff68b47a649037a0f8d');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('84713', 'Ivan Lendme', '1235 Johnson Dr', 'ECE', '671754d0bd16df89f2e79a9568c90b30f1ea8f881aedb570d516d966bf832f50');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('36912', 'Joe Pepsi', '3210 State St', 'CS', 'a3e5879009f973c93e65779308f048ef60e1b23050676348f3de75d532af9ce2');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('46590', 'Kelvin Coster', 'Santa Cruz #3579', 'CS', '69b19d3e2dc1fee0d74f185f2d92dfa7b32c043997775f265ecce7716f9c9089');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('91734', 'Li Kung', '2 People''s Rd Beijing', 'ECE', '41f15c5a2ecc889bb58bf1b4b0f1e5197ee009351acfe0bcbea639c30595329c');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('73521', 'Magic Jordon', '3852 Court Rd', 'CS', 'f4c94be46d289d52dc7326896923b7cfbc837c2731430c1430f3eafb09ed2681');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('53540', 'Nam-hoi Chung', '1997 People''s St HK', 'CS', '01c6796d14a48e9f352fc2a60509743046918bd6cd2679c6c092cb71ea4854c3');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('82452', 'Olive Stoner', '6689 El Colegio #151', 'ECE', 'c9ab5412eaee51f750490b9c9b55d9b071d6a1622e56a61c5762b009510f0ee3');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('18221', 'Pit Wilson', '911 State St', 'ECE', 'c520d3b7c1b33e652e5db266366a1dc68c0a74571a449a6dc1314916a24b036d');

INSERT INTO MAJORS (major_id, name, dept, num_electives) VALUES (1, 'Computer Science', 'CS', 5);
INSERT INTO MAJORS (major_id, name, dept, num_electives) VALUES (2, 'Computer Engineering', 'ECE', 5);

INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS174', 12345, 'Databases', 'CS');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS170', 54321, 'Operating Systems', 'CS');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS160', 41725, 'Compilers', 'CS');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS026', 76543, 'Intro to Computer Science II', 'CS'); 
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('EC154', 93156, 'Computer Architecture', 'ECE');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('EC140', 19023, 'Communication Electronics', 'ECE');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('EC015', 71631, 'Fundamentals of Logic Design', 'ECE');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS154', 32165, 'Computer Architecture', 'CS');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS130', 56789, 'Data Structures and Algorithms', 'CS'); 
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('EC152', 91823, 'Digital Design Principles', 'ECE');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('CS010', 81623, 'Intro to Java Programming', 'CS');
INSERT INTO COURSES (course_no, enroll_code, title, dept) VALUES ('EC010', 82612, 'Circuits and Systems', 'ECE');

INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS026', 25, 'S', 'Mars', 5, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS174', 25, 'S', 'Venus', 8, 'TR10-12', 'Psycho 1132');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS170', 25, 'S', 'Jupiter', 8, 'MWF10-11', 'English 1124');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC154', 25, 'S', 'Saturn', 7, 'T3-5', 'Maths 3333');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS160', 25, 'S', 'Mercury', 8, 'MWF2-3', 'Engr 1132');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC140', 25, 'S', 'Gold', 10, 'TR1-3', 'Chem 1234');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC015', 25, 'S', 'Silver', 5, 'MW11-1', 'Engr 2116');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS170', 25, 'W', 'Copper', 18, 'MWF10-11', 'English 1124');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS160', 25, 'W', 'Iron', 15, 'MWF2-3', 'Engr 1132');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS154', 25, 'W', 'Tin', 10, 'MF8-9', 'Engr 2116');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS130', 25, 'W', 'Star', 15, 'TR2-4', 'Chem 1111');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS026', 25, 'W', 'Tin', 15, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC154', 25, 'W', 'Saturn', 18, 'T3-5', 'Maths 3333');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC152', 25, 'W', 'Gold', 10, 'MW11-1', 'Engr 3163');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS170', 24, 'F', 'Copper', 15, 'MWF10-11', 'English 1124');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS160', 24, 'F', 'Mercury', 10, 'MWF2-3', 'Engr 1132');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS154', 24, 'F', 'Mars', 10, 'MWF8-9', 'Engr 2116');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS130', 24, 'F', 'Jupiter', 15, 'TR2-4', 'Chem 1111');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS026', 24, 'F', 'Tin', 15, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('CS010', 24, 'F', 'Gold', 10, 'MWR3-4', 'Chem 3333');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC154', 24, 'F', 'Silver', 10, 'T3-5', 'Maths 3333');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC152', 24, 'F', 'Sun', 10, 'MW11-1', 'Engr 3163');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC015', 24, 'F', 'Moon', 15, 'TR2-4', 'Engr 1124');
INSERT INTO OFFERINGS (course_no, year, quarter, professor, capacity, time, location) VALUES ('EC010', 24, 'F', 'Earth', 15, 'MWF8-9', 'Physics 4004');

-- Alfred Hitchcock (12345)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('12345', 'CS174', 25, 'S'); -- CS174 (25 S)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('12345', 'CS160', 25, 'S'); -- CS160 (25 S)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('12345', 'CS154', 25, 'W', 'A');   -- CS154 (25 W)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('12345', 'CS130', 25, 'W', 'B');   -- CS130 (25 W)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('12345', 'EC154', 25, 'W', 'C');  -- EC154 (25 W)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('12345', 'CS026', 24, 'F', 'A');   -- CS026 (24 F)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('12345', 'CS010', 24, 'F', 'A');   -- CS010 (24 F)

-- Billy Clinton (14682)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('14682', 'CS160', 25, 'W', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('14682', 'CS130', 25, 'W', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('14682', 'CS026', 24, 'F', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('14682', 'CS010', 24, 'F', 'A');

-- Cindy Laugher (37642)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('37642', 'EC154', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('37642', 'CS160', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('37642', 'EC152', 25, 'W', 'C');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('37642', 'CS130', 25, 'W', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('37642', 'EC015', 24, 'F', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('37642', 'EC010', 24, 'F', 'A');

-- David Copperfill (85821)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('85821', 'CS174', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('85821', 'CS160', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('85821', 'CS130', 25, 'W', 'C');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('85821', 'CS026', 25, 'W', 'A');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('85821', 'CS010', 24, 'F', 'A');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('85821', 'EC015', 24, 'F', 'B');

-- Elizabeth Sailor (38567)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('38567', 'CS174', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('38567', 'CS170', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('38567', 'CS160', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('38567', 'EC154', 25, 'W', 'C');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('38567', 'CS130', 25, 'W', 'A');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('38567', 'EC152', 24, 'F', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('38567', 'CS154', 24, 'F', 'B');

-- Fatal Castro (81934)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('81934', 'EC154', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('81934', 'CS154', 25, 'W', 'C');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('81934', 'CS130', 25, 'W', 'A');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('81934', 'CS026', 24, 'F', 'A');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('81934', 'EC152', 24, 'F', 'B');

-- George Brush (98246)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('98246', 'CS160', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('98246', 'CS174', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('98246', 'CS170', 25, 'S');
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('98246', 'EC154', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('98246', 'EC152', 25, 'W', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('98246', 'CS154', 24, 'F', 'A');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('98246', 'CS130', 24, 'F', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('98246', 'CS026', 24, 'F', 'A');

-- Hurryson Ford (35328)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('35328', 'CS174', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('35328', 'CS130', 24, 'F', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('35328', 'CS026', 24, 'F', 'A');

-- Ivan Lendme (84713)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('84713', 'CS026', 25, 'W', 'D');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('84713', 'EC015', 24, 'F', 'F');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('84713', 'CS010', 24, 'F', 'C');

-- Kelvin Coster (46590)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('46590', 'CS026', 25, 'W', 'A');

-- Li Kung (91734)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('91734', 'CS026', 25, 'W', 'A');

-- Magic Jordon (73521)
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('73521', 'CS026', 25, 'W', 'B');

-- Nam-hoi Chung (53540)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('53540', 'CS170', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('53540', 'CS154', 25, 'W', 'D');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('53540', 'CS130', 25, 'W', 'C');

-- Olive Stoner (82452)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('82452', 'EC154', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('82452', 'EC152', 25, 'W', 'C');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('82452', 'CS026', 25, 'W', 'C');

-- Pit Wilson (18221)
INSERT INTO TAKES (perm, course_no, year, quarter) VALUES ('18221', 'CS174', 25, 'S');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('18221', 'CS130', 25, 'W', 'B');
INSERT INTO TOOK (perm, course_no, year, quarter, grade) VALUES ('18221', 'CS026', 25, 'W', 'B');

INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('12345', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('14682', 2, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('37642', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('85821', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('38567', 2, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('81934', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('98246', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('35328', 2, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('84713', 2, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('36912', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('46590', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('91734', 2, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('73521', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('53540', 1, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('82452', 2, 5);
INSERT INTO STUDIES (perm, major_id, num_electives) VALUES ('18221', 2, 5);

INSERT INTO MANDATORY (course_no) VALUES ('CS026');
INSERT INTO MANDATORY (course_no) VALUES ('CS130');
INSERT INTO MANDATORY (course_no) VALUES ('CS154');
INSERT INTO MANDATORY (course_no) VALUES ('CS160');
INSERT INTO MANDATORY (course_no) VALUES ('CS170');

INSERT INTO ELECTIVES (course_no) VALUES ('CS010');
INSERT INTO ELECTIVES (course_no) VALUES ('EC010');
INSERT INTO ELECTIVES (course_no) VALUES ('EC015');
INSERT INTO ELECTIVES (course_no) VALUES ('EC140');
INSERT INTO ELECTIVES (course_no) VALUES ('EC152');
INSERT INTO ELECTIVES (course_no) VALUES ('EC154');
INSERT INTO ELECTIVES (course_no) VALUES ('CS174');


INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'CS010');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'EC010'); 
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'EC015');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'EC140');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'EC152');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'EC154');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'CS010'); 
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'EC010');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'EC015');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'EC140');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'EC152');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'EC154');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (1, 'CS174');
INSERT INTO CAN_TAKE (major_id, course_no) VALUES (2, 'CS174');


INSERT INTO MUST_TAKE (major_id, course_no) VALUES (1, 'CS026');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (1, 'CS130');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (1, 'CS154');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (1, 'CS160');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (1, 'CS170');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (2, 'CS026');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (2, 'CS130');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (2, 'CS154');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (2, 'CS160');
INSERT INTO MUST_TAKE (major_id, course_no) VALUES (2, 'CS170');

INSERT INTO PREREQS (course, prereq_course) VALUES ('CS174', 'CS026');
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS174', 'CS130');
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS170', 'CS130');
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS170', 'CS154');
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS160', 'CS026');
INSERT INTO PREREQS (course, prereq_course) VALUES ('EC154', 'CS026');
INSERT INTO PREREQS (course, prereq_course) VALUES ('EC154', 'EC152');

COMMIT;