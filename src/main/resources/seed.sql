insert into "school" ("id", "address", "name") values ('b68fa2c4-0463-4783-8ac0-c97edd0d34f4', 'School 1 Adress', 'School 1');
insert into "school" ("id", "address", "name") values ('b68fa2c4-0463-4783-8ac0-c97edd0d32f3', 'School 2 Adress', 'School 2');
insert into "school" ("id", "address", "name") values ('b68fa2c4-0463-4783-8ac0-c97edd0d32f2', 'School 3 Adress', 'School 3');
insert into "school" ("id", "address", "name") values ('b68fa2c4-0463-4783-8ac0-c97edd0d32f1', 'School 4 Adress', 'School 3');

insert into "course" ("id", "name", "school_id") values ('37a77482-61ea-45e5-935e-2b487a1c0299', 'course 1', 'b68fa2c4-0463-4783-8ac0-c97edd0d34f4');
insert into "course" ("id", "name", "school_id") values ('37a77482-61ea-45e5-935e-2b487a1c0298', 'course 2', 'b68fa2c4-0463-4783-8ac0-c97edd0d32f3');
insert into "course" ("id", "name", "school_id") values ('37a77482-61ea-45e5-935e-2b487a1c0297', 'course 3', 'b68fa2c4-0463-4783-8ac0-c97edd0d32f2');
insert into "course" ("id", "name", "school_id") values ('37a77482-61ea-45e5-935e-2b487a1c0296', 'course 4', 'b68fa2c4-0463-4783-8ac0-c97edd0d32f1');

insert into "contacts" ("facebook", "id", "instagram", "twitter", "whatsapp") values ('elon.musk', '04ce15ee-7753-4dc1-bafc-650ca6b3d8c4', 'elonmusk', '@elonmusk', '5511999999999');
insert into "preferences" ("course_id", "id", "school_id", "school_year", "shift") values ('37a77482-61ea-45e5-935e-2b487a1c0299', '75fcee8b-95d3-4a0e-82e9-28c871c51f70', 'b68fa2c4-0463-4783-8ac0-c97edd0d34f4', 2, 0);
insert into "app_user" ("bio", "birth_date", "classroom", "contacts_id", "course_id", "email", "gender", "id", "name", "password", "photos", "preferences_id", "school_year", "shift") values 
('kpop very good', '2006-06-05 21:00:00', 'F', '04ce15ee-7753-4dc1-bafc-650ca6b3d8c4', '37a77482-61ea-45e5-935e-2b487a1c0299', 'seed@email.com', '0', '22eba961-7b1e-456f-a3a0-b197313f2a80', 'test user', '$2a$10$qzCAAteqYRyIFf4tf6Cz9eTEZ9KPk/zbXt13jEMLTmN2Zcy7G7Y5K', NULL, '75fcee8b-95d3-4a0e-82e9-28c871c51f70', 1, 0);