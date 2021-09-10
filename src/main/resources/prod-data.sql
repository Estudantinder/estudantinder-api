INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'ITB PROF.ª MARIA SYLVIA CHALUPPE MELLO', 'ENGENHO NOVO');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Analises Clínicas', 1);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Enfermagem', 1);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Farmácia', 1);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Informática para internet', 1);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Química', 1);


INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'ITB BRASÍLIO FLORES DE AZEVEDO', 'JARDIM BELVAL');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Edificações', 7);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Eletroeletrônica', 7);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Informática', 7);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Telecomunicações', 7);


INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'ITB PROF.º HERCULES ALVES DE OLIVEIRA', 'JARDIM MUTINGA');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Administração', 12);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Design de Interiores', 12);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Logística', 12);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Publicidade', 12);


INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'ITB PROF.º MUNIR JOSÉ', 'JARDIM PAULISTA');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Administração', 17);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Recursos Humanos', 17);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Redes de Computadores', 17);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Secretariado', 17);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Segurança de Trabalho', 17);


INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'ITB PROF.º MOACYR DOMINGOS SAVIO VERONEZI', 'PARQUE IMPERIAL');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Logistica', 23);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Manutenção e suporte em informática', 23);


INSERT INTO school (id, name, address) VALUES (nextval('hibernate_sequence'), 'ITB PROF.º ANTONIO ARANTES FILHO', 'PARQUE VIANA');

INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Contabilidade', 26);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Finanças', 26);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Hospedagem', 26);
INSERT INTO course (id, name, school_id) VALUES (nextval('hibernate_sequence'), 'Informática', 26);



insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Matemática', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024537/hpfkqgkamyf9lk87gnjj.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Biologia', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024566/x73inilgkomrkgnwsiqx.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Quimica', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024580/xwqqhatd2n6m2hpecsgq.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Português', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024596/pojr2ajs7hj765edcfuo.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Historia', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024605/ot8i4tvsiv8eauox7geq.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Fisica', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024617/hzvknz6os1mqxlnattc1.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Geografia', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024626/dxebbesw01x3rvjr8k0o.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Inglês', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024638/yafqedtlmtlgon8ibvt4.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Artes', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024645/u2rijt8r1mvd829amz0m.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Educação Fisica', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024655/uv7udvcjnlaimoznbc7j.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Filosofia', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024682/oumy0mdytsuyojbgjhro.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Literatura', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024690/eugsb4xcscgpxbvobfb8.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Sociologia', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024699/cmas6gxdd3toodngx2m7.png');
insert into "subject" ("id", "name", "photo") values (nextval('hibernate_sequence'), 'Espanhol', 'https://res.cloudinary.com/adamaugustinsky/image/upload/v1631024709/hhghdknn5pbpafsdfs8q.png');