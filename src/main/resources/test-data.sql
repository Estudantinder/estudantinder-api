INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 1', 'ADDRESS 1');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 2', 'ADDRESS 2');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 3', 'ADDRESS 3');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 4', 'ADDRESS 4');

INSERT INTO course (id, name) VALUES (nextval('hibernate_sequence'), 'COURSE 1');
INSERT INTO course (id, name) VALUES (nextval('hibernate_sequence'), 'COURSE 2');
INSERT INTO course (id, name) VALUES (nextval('hibernate_sequence'), 'COURSE 3');
INSERT INTO course (id, name) VALUES (nextval('hibernate_sequence'), 'COURSE 4');
INSERT INTO course (id, name) VALUES (nextval('hibernate_sequence'), 'COURSE 5');

INSERT INTO school_course (school_id, courses_id) VALUES (1, 5);
INSERT INTO school_course (school_id, courses_id) VALUES (2, 6);
INSERT INTO school_course (school_id, courses_id) VALUES (3, 7);
INSERT INTO school_course (school_id, courses_id) VALUES (4, 8);
INSERT INTO school_course (school_id, courses_id) VALUES (4, 9);