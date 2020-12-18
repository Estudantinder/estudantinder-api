INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 1', 'ADDRESS 1');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 2', 'ADDRESS 2');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 3', 'ADDRESS 3');
INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'SCHOOL 4', 'ADDRESS 4');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 1', 1);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 2', 2);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 3', 3);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 4', 4);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'COURSE 5', 4);

INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile1', '@testeProfile1', '@testeProfile1', 5511123456781);
INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile2', '@testeProfile2', '@testeProfile2', 5511123456782);
INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile3', '@testeProfile3', '@testeProfile3', 5511123456783);
INSERT INTO contacts (id, facebook, instagram, twitter, whatsapp) VALUES (nextval('hibernate_sequence'), 'teste.profile4', '@testeProfile4', '@testeProfile4', 5511123456784);

INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), 'Masculino', 0, 0, null);
INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), 'Feminino', 1, 0, null);
INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), 'Feminino', 2, 3, null);
INSERT INTO preferences (id, gender, shift, school_year , course_id) VALUES (nextval('hibernate_sequence'), 'Masculino', 1, 3, 8);


INSERT INTO student (id, bio, birth_date, email, classroom, subjects, gender, name, password, photos, shift, school_year, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 1', '2000-11-23', 'test1@email.com', 'A', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000374000A4D4154454D4154494341740009504F5254554755455374000947454F475241464941', 'Masculino', 'TEST USER 1', '$2a$10$H4Q4ud1ffknJutry490mfOKsxRCrEtHZ9vN/GJwYt0rhzewMC2oiG', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001868747470733A2F2F7465737470686F746F732E636F6D2F3174001868747470733A2F2F7465737470686F746F732E636F6D2F3274001868747470733A2F2F7465737470686F746F732E636F6D2F3374001868747470733A2F2F7465737470686F746F732E636F6D2F3474001868747470733A2F2F7465737470686F746F732E636F6D2F3574001868747470733A2F2F7465737470686F746F732E636F6D2F36', 2, 2, 10, 8, 14);
INSERT INTO student (id, bio, birth_date, email, classroom, subjects, gender, name, password, photos, shift, school_year, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 2', '2002-11-23', 'test2@email.com', 'B', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000374000B455354415449535449434174000A534F43494F4C4F47494174000946494C4F534F464941', 'Feminino', 'TEST USER 2', '$2a$10$EqsV3XvB44bjFA6/mAIVwu4fOUd2t8J9CLn9MyncApGQI9B07skGW', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001868747470733A2F2F7465737470686F746F732E636F6D2F3174001868747470733A2F2F7465737470686F746F732E636F6D2F3274001868747470733A2F2F7465737470686F746F732E636F6D2F3374001868747470733A2F2F7465737470686F746F732E636F6D2F3474001868747470733A2F2F7465737470686F746F732E636F6D2F3574001868747470733A2F2F7465737470686F746F732E636F6D2F36', 2, 3, 11, 9, 15);
INSERT INTO student (id, bio, birth_date, email, classroom, subjects, gender, name, password, photos, shift, school_year, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 3', '2003-11-23', 'test3@email.com', 'C', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B470200007870000000037400075155494D49434174000842494F4C4F474941740006464953494341', 'Masculino', 'TEST USER 3', '$2a$10$.qzqpEjvdITYLuIG3.9bOOBab6KK.PW4MTSviF7JWxqAzp8v7mfg6', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001868747470733A2F2F7465737470686F746F732E636F6D2F3174001868747470733A2F2F7465737470686F746F732E636F6D2F3274001868747470733A2F2F7465737470686F746F732E636F6D2F3374001868747470733A2F2F7465737470686F746F732E636F6D2F3474001868747470733A2F2F7465737470686F746F732E636F6D2F3574001868747470733A2F2F7465737470686F746F732E636F6D2F36', 1, 3, 12, 8, 16);
INSERT INTO student (id, bio, birth_date, email, classroom, subjects, gender, name, password, photos, shift, school_year, contacts_id, course_id, preferences_id) VALUES 
    (nextval('hibernate_sequence'), 'TEST BIOGRAPHY 4', '2004-11-23', 'test4@email.com', 'D', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000374000A534F43494F4C4F474941740009504F5254554755455374000947454F475241464941', 'Feminino', 'TEST USER 4', '$2a$10$0Ng6OTyQF6VxM0zku4Q48.ojKVeJZFyd9gZqisoRJo8.pc6fn5X.u', E'\\xACED0005757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B4702000078700000000674001868747470733A2F2F7465737470686F746F732E636F6D2F3174001868747470733A2F2F7465737470686F746F732E636F6D2F3274001868747470733A2F2F7465737470686F746F732E636F6D2F3374001868747470733A2F2F7465737470686F746F732E636F6D2F3474001868747470733A2F2F7465737470686F746F732E636F6D2F3574001868747470733A2F2F7465737470686F746F732E636F6D2F36', 1, 2, 13, 9, 17);

INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 18, 19);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 19, 18);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 19, 20);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 20, 21);
INSERT INTO likes (id, receiver_id, sender_id) VALUES (nextval('hibernate_sequence'), 21, 20);
INSERT INTO match (id, like_id, mutuallike_id) VALUES (nextval('hibernate_sequence'), 22, 23);
INSERT INTO match (id, like_id, mutuallike_id) VALUES (nextval('hibernate_sequence'), 25, 26);