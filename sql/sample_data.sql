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
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('14682', 'Billy Clinton', '5777 Hollister', 'ECE', 'b85d0b5c7769dc7ab0d09747960a89ff4124a0b048ff59aa4c9a96c69b6fc7cb');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('37642', 'Cindy Laugher', '7000 Hollister', 'CS', '1841eb5e1a7c9057c3478cb935c89e4202a23a2d93e122fa20ce3aa1a2e96df9');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('85821', 'David Copperfill', '1357 State St', 'CS', 'ac95f96e0c9a3306e66a2689d305b3e08f7926318586c0e2b7bc11dc71e0db83');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('38567', 'Elizabeth Sailor', '4321 State St', 'ECE', 'ae7c2110d36cbcf68c032ccc210c888008a3ac6f77324db991c10991a4b8d12d');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('81934', 'Fatal Castro', '3756 La Cumbre Plaza', 'CS', '84b861f1e34d1a3a4cdfe603c3b8cf8448dd59cb8d168b53951ae35118690f2c');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('98246', 'George Brush', '5346 Foothill Av', 'CS', '63a25ea6bcd786d2d894cc2c6a064aa93681802abfc30080e877107e5aefdf37');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('35328', 'Hurryson Ford', '678 State St', 'ECE', 'd4db2bff978f9478416f5b6bb75ec7c75a2e279fcbba4dce2493bc3e79ef098e');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('84713', 'Ivan Lendme', '1235 Johnson Dr', 'ECE', '60ff8047e14d145a234dd14436aa43a946755fd4f5a9b2cb8cd6b7b020db7775');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('36912', 'Joe Pepsi', '3210 State St', 'CS', 'b5b0848f8eb6f98f77dc0a6b80e362819abf89e6fc9c5e0f52c02db9189c3274');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('46590', 'Kelvin Coster', 'Santa Cruz #3579', 'CS', '71f3e82b32707f706fd823a9aaad832a22156c2a71f2a9bb888e13c29cd406b3');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('91734', 'Li Kung', '2 People''s Rd Beijing', 'ECE', 'db2245b389cbdb0dcdd14cb90925e8a08c51945fd0e92b2cfb0970e169db54de');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('73521', 'Magic Jordon', '3852 Court Rd', 'CS', 'ae2b60e92720db76d291342a709b4217a1c0085d84a77e6b2e678781f64b032c');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('53540', 'Nam-hoi Chung', '1997 People''s St HK', 'CS', 'caa77ce7056d4c4b4a410b2996d6f7c4f1d54c64c237cdcc17e2f019cb4f01e7');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('82452', 'Olive Stoner', '6689 El Colegio #151', 'ECE', '2b09b52f8a964d23609365c83c2b372a765a62c325e79813c57f67dbb7b1b9b1');
INSERT INTO STUDENTS (perm, name, address, dept, pin) VALUES ('18221', 'Pit Wilson', '911 State St', 'ECE', '79827a46e6d2f372902947be03f7650d35ab66a3b6c2bdfc18796c676493ea2a');

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