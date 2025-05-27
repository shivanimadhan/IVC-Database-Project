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

INSERT INTO COURSES (course_no, title) VALUES ('CS174', 'Databases');
INSERT INTO COURSES (course_no, title) VALUES ('CS170', 'Operating Systems');
INSERT INTO COURSES (course_no, title) VALUES ('CS160', 'Compilers');
INSERT INTO COURSES (course_no, title) VALUES ('CS026', 'Intro to Computer Science II'); 
INSERT INTO COURSES (course_no, title) VALUES ('EC154', 'Computer Architecture');
INSERT INTO COURSES (course_no, title) VALUES ('EC140', 'Communication Electronics');
INSERT INTO COURSES (course_no, title) VALUES ('EC015', 'Fundamentals of Logic Design');
INSERT INTO COURSES (course_no, title) VALUES ('CS154', 'Computer Architecture');
INSERT INTO COURSES (course_no, title) VALUES ('CS130', 'Data Structures and Algorithms'); 
INSERT INTO COURSES (course_no, title) VALUES ('EC152', 'Digital Design Principles');
INSERT INTO COURSES (course_no, title) VALUES ('CS010', 'Intro to Java Programming');
INSERT INTO COURSES (course_no, title) VALUES ('EC010', 'Circuits and Systems');

INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS174', 12345, 25, 'S', 'Venus', 5, 'TR10-12', 'Psycho 1132');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS170', 54321, 25, 'S', 'Jupiter', 8, 'MWF10-11', 'English 1124');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS160', 41725, 25, 'S', 'Mercury', 5, 'MWF2-3', 'Engr 1132');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS026', 76543, 25, 'S', 'Mars', 5, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC154', 93156, 25, 'S', 'Saturn', 7, 'T3-5', 'Maths 3333');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC140', 19023, 25, 'S', 'Gold', 10, 'TR1-3', 'Chem 1234');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC015', 71631, 25, 'S', 'Silver', 5, 'MW11-1', 'Engr 2116');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS170', 28292, 25, 'W', 'Copper', 18, 'MWF10-11', 'English 1124');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS160', 23132, 25, 'W', 'Iron', 15, 'MWF2-3', 'Engr 1132');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS154', 32165, 25, 'W', 'Tin', 10, 'MF8-9', 'Engr 2116');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS130', 56789, 25, 'W', 'Star', 15, 'TR2-4', 'Chem 1111');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS026', 32432, 25, 'W', 'Tin', 15, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC154', 23232, 25, 'W', 'Saturn', 18, 'T3-5', 'Maths 3333');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC152', 91823, 25, 'W', 'Gold', 10, 'MW11-1', 'Engr 3163');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS170', 13232, 24, 'F', 'Copper', 15, 'MWF10-11', 'English 1124');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS160', 23819, 24, 'F', 'Mercury', 10, 'MWF2-3', 'Engr 1132');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS154', 13231, 24, 'F', 'Mars', 10, 'MWF8-9', 'Engr 2116');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS130', 22122, 24, 'F', 'Jupiter', 15, 'TR2-4', 'Chem 1111');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS026', 27192, 24, 'F', 'Tin', 15, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS010', 81623, 24, 'F', 'Gold', 10, 'MWR3-4', 'Chem 3333');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC154', 13212, 24, 'F', 'Silver', 10, 'T3-5', 'Maths 3333');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC152', 17293, 24, 'F', 'Sun', 10, 'MW11-1', 'Engr 3163');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC015', 13123, 24, 'F', 'Moon', 15, 'TR2-4', 'Engr 1124');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC010', 82612, 24, 'F', 'Earth', 15, 'MWF8-9', 'Physics 4004');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS130', 37181, 24, 'S', 'Mercury', 15, 'TR2-4', 'Chem 1111');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS026', 13711, 24, 'S', 'Mars', 15, 'MWF2-3', 'Bio 2222');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('CS010', 23713, 24, 'S', 'Gold', 10, 'MWR3-4', 'Chem 3333');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC152', 12312, 24, 'S', 'Iron', 12, 'MW11-1', 'Engr 3163');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC015', 51281, 24, 'S', 'Moon', 15, 'TR2-4', 'Engr 1124');
INSERT INTO OFFERINGS (course_no, enroll_code, year, quarter, professor, capacity, time, location) VALUES ('EC010', 19218, 24, 'S', 'Star', 15, 'MWF8-9', 'Physics 4004');

-- Alfred Hitchcock (12345)
INSERT INTO TAKES (perm, enroll_code) VALUES ('12345', 12345); -- CS174 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('12345', 41725); -- CS160 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 32165, 'A');   -- CS154 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 56789, 'B');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 23232, NULL);  -- EC154 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 27192, 'A');   -- CS026 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 81623, 'A');   -- CS010 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 12312, 'A');   -- EC152 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 51281, 'A');   -- EC015 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('12345', 19218, 'A');   -- EC010 (24 S)

-- Billy Clinton (14682)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 23132, 'B');   -- CS160 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 56789, 'B');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 27192, 'B');   -- CS026 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 81623, 'A');   -- CS010 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 12312, 'A');   -- EC152 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 51281, 'A');   -- EC015 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('14682', 19218, 'A');   -- EC010 (24 S)

-- Cindy Laugher (37642)
INSERT INTO TAKES (perm, enroll_code) VALUES ('37642', 93156);              -- EC154 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('37642', 41725);              -- CS160 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('37642', 91823, 'C');   -- EC152 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('37642', 56789, 'B');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('37642', 13123, 'B');   -- EC015 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('37642', 82612, 'A');   -- EC010 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('37642', 27192, 'A');   -- CS026 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('37642', 81623, 'A');   -- CS010 (24 S)

-- David Copperfill (85821)
INSERT INTO TAKES (perm, enroll_code) VALUES ('85821', 12345);              -- CS174 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('85821', 41725);              -- CS160 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('85821', 56789, 'C');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('85821', 27192, 'A');   -- CS026 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('85821', 81623, 'A');   -- CS010 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('85821', 13123, 'B');   -- EC015 (24 F)

-- Elizabeth Sailor (38567)
INSERT INTO TAKES (perm, enroll_code) VALUES ('38567', 12345);              -- CS174 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('38567', 54321);              -- CS170 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('38567', 41725);              -- CS160 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 23232, 'C');   -- EC154 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 56789, 'A');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 17293, 'B');   -- EC152 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 13231, 'B');   -- CS154 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 27192, 'A');   -- CS026 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 81623, 'A');   -- CS010 (24 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('38567', 19218, 'B');   -- EC010 (24 S)

-- Fatal Castro (81934)
INSERT INTO TAKES (perm, enroll_code) VALUES ('81934', 93156);              -- EC154 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('81934', 32165, 'C');   -- CS154 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('81934', 56789, 'A');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('81934', 27192, 'A');   -- CS026 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('81934', 17293, 'B');   -- EC152 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('81934', 81623, 'A');   -- CS010 (24 S)

-- George Brush (98246)
INSERT INTO TAKES (perm, enroll_code) VALUES ('98246', 41725);              -- CS160 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('98246', 12345);              -- CS174 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('98246', 54321);              -- CS170 (25 S)
INSERT INTO TAKES (perm, enroll_code) VALUES ('98246', 93156);              -- EC154 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('98246', 13231, 'A');   -- CS154 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('98246', 22122, 'B');   -- CS130 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('98246', 27192, 'A');   -- CS026 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('98246', 81623, 'B');   -- CS010 (24 S)

-- Hurryson Ford (35328)
INSERT INTO TAKES (perm, enroll_code) VALUES ('35328', 12345);              -- CS174 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('35328', 22122, 'B');   -- CS130 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('35328', 27192, 'A');   -- CS026 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('35328', 81623, 'A');   -- CS010 (24 S)

-- Ivan Lendme (84713)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('84713', 76543, 'D');   -- CS026 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('84713', 13123, 'F');   -- EC015 (24 F)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('84713', 81623, 'C');   -- CS010 (24 F)

-- Joe Pepsi (36912)
-- no data

-- Kelvin Coster (46590)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('46590', 76543, 'A');   -- CS026 (25 W)

-- Li Kung (91734)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('91734', 76543, 'A');   -- CS026 (25 W)

-- Magic Jordon (73521)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('73521', 76543, 'B');   -- CS026 (25 W)

-- Nam-hoi Chung (53540)
INSERT INTO TAKES (perm, enroll_code) VALUES ('53540', 54321);              -- CS170 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('53540', 32165, 'D');   -- CS154 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('53540', 56789, 'C');   -- CS130 (25 W)

-- Olive Stoner (82452)
INSERT INTO TAKES (perm, enroll_code) VALUES ('82452', 93156);              -- EC154 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('82452', 91823, 'C');   -- EC152 (25 W)

-- Pit Wilson (18221)
INSERT INTO TAKES (perm, enroll_code) VALUES ('18221', 12345);              -- CS174 (25 S)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('18221', 56789, 'B');   -- CS130 (25 W)
INSERT INTO TOOK (perm, enroll_code, grade) VALUES ('18221', 76543, 'B');   -- CS026 (25 W)

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
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS170', 'CS130');
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS170', 'CS154');
INSERT INTO PREREQS (course, prereq_course) VALUES ('CS160', 'CS026');
INSERT INTO PREREQS (course, prereq_course) VALUES ('EC154', 'CS026');
INSERT INTO PREREQS (course, prereq_course) VALUES ('EC154', 'EC152');

COMMIT;