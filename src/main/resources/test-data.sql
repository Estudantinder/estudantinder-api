INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 1', 'ADDRESS 1');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 2', 'ADDRESS 2');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 3', 'ADDRESS 3');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 4', 'ADDRESS 4');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 1', 2);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 2', 2);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 3', 3);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 4', 4);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 5', 4);

INSERT INTO subject (id, name) VALUES (nextval('hibernate_sequence'), 'TEST SUBJECT 1');
INSERT INTO subject (id, name) VALUES (nextval('hibernate_sequence'), 'TEST SUBJECT 2');
INSERT INTO subject (id, name) VALUES (nextval('hibernate_sequence'), 'TEST SUBJECT 3');
INSERT INTO subject (id, name) VALUES (nextval('hibernate_sequence'), 'TEST SUBJECT 4');

INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile1', '@testeProfile1', '@testeProfile1', 5511123456781);
INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile2', '@testeProfile2', '@testeProfile2', 5511123456782);
INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile3', '@testeProfile3', '@testeProfile3', 5511123456783);
INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile4', '@testeProfile4', '@testeProfile4', 5511123456784);

INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), '1', 0, 0, null);
INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), '1', 1, 0, null);
INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), '1', 2, 3, null);
INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), '1', 1, 3, 8);

INSERT INTO users (id, bio, birth_date, classroom, email, gender, name, password, photos, school_year, shift, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 1', '2002-12-18', 'A', 'test1@email.com', '1', 'TEST USER 1', 
    '$2a$10$UESqt09s1GHdJ8js1ga1JOvPmOdE2tKS6KLD57mgbFHgla3QyP6Ku', 
    E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001468747470733E2F2F70686F746F732E636F6D2F3174001468747470733E2F2F70686F746F732E636F6D2F3274001468747470733E2F2F70686F746F732E636F6D2F3374001468747470733E2F2F70686F746F732E636F6D2F3474001468747470733E2F2F70686F746F732E636F6D2F3574001468747470733E2F2F70686F746F732E636F6D2F36', 
    2, 1, 14, 5, 18);
INSERT INTO users (id, bio, birth_date, classroom, email, gender, name, password, photos, school_year, shift, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 2', '2002-12-18', 'B', 'test2@email.com', '1', 'TEST USER 2', 
    '$2a$10$y9L4HgglvWy0fnpyQBNDxu59MfQIRBXxbIHnfSmcT9Q2CsVY56To.', 
    E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001468747470733A2F2F70686F746F732E636F6D2F3174001468747470733A2F2F70686F746F732E636F6D2F3274001468747470733A2F2F70686F746F732E636F6D2F3374001468747470733A2F2F70686F746F732E636F6D2F3474001468747470733A2F2F70686F746F732E636F6D2F3574001468747470733A2F2F70686F746F732E636F6D2F36', 
    2, 1, 15, 5, 19);
INSERT INTO users (id, bio, birth_date, classroom, email, gender, name, password, photos, school_year, shift, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 3', '2002-12-18', 'C', 'test3@email.com', '1', 'TEST USER 3', 
    '$2a$10$6fH/lEhilZWbnNVu1.NTQew6gKQI1AX4I5YMf7PbZW77Lm.bgZzcG', 
    E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001468747470733A2F2F70686F746F732E636F6D2F3174001468747470733A2F2F70686F746F732E636F6D2F3274001468747470733A2F2F70686F746F732E636F6D2F3374001468747470733A2F2F70686F746F732E636F6D2F3474001468747470733A2F2F70686F746F732E636F6D2F3574001468747470733A2F2F70686F746F732E636F6D2F36', 
    2, 1, 16, 5, 20);
INSERT INTO users (id, bio, birth_date, classroom, email, gender, name, password, photos, school_year, shift, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 4', '2002-12-18', 'D', 'test4@email.com', '1', 'TEST USER 4', 
    '$2a$10$7AwPby5UXVLGnqbVFaTQQeQ9wb5Pl/zAyAR5A4fq78I9IJRAQc05u', 
    E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001468747470733A2F2F70686F746F732E636F6D2F3174001468747470733A2F2F70686F746F732E636F6D2F3274001468747470733A2F2F70686F746F732E636F6D2F3374001468747470733A2F2F70686F746F732E636F6D2F3474001468747470733A2F2F70686F746F732E636F6D2F3574001468747470733A2F2F70686F746F732E636F6D2F36', 
    2, 1, 17, 5, 21);

INSERT INTO users_subject (users_id, subjects_id) VALUES (22, 10);
INSERT INTO users_subject (users_id, subjects_id) VALUES (23, 11);
INSERT INTO users_subject (users_id, subjects_id) VALUES (24, 12);


INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 22, 23);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 23, 22);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 23, 24);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 24, 25);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 25, 24);
INSERT INTO match (id, like_id, mutuallike_id) VALUES (nextval('hibernate_sequence'), 26, 27);
INSERT INTO match (id, like_id, mutuallike_id) VALUES (nextval('hibernate_sequence'), 29, 28);


INSERT INTO preferences_subject (preferences_id, subjects_id) VALUES (18, 10);
INSERT INTO preferences_subject (preferences_id, subjects_id) VALUES (19, 11);
INSERT INTO preferences_subject (preferences_id, subjects_id) VALUES (20, 12);