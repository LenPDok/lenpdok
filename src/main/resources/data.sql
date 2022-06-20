INSERT INTO USER (USER_ID, USERNAME, PASSWORD, NICKNAME, ACTIVATED) VALUES (1, 'admin', '$2a$10$7ohB6RaNlwExCnVJ0FEGpeZ9WzUvozzWApm4SlQjo4rC1KbShOLla', 'admin', 1);
INSERT INTO USER (USER_ID, USERNAME, PASSWORD, NICKNAME, ACTIVATED) VALUES (2, 'user', '$2a$10$y8b7pzz3zMJQWxocDPM6nu32E8QXmgcAqmFnM5/vsMju/Zj3.WmgG', 'user', 1);

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (2, 'ROLE_USER');

INSERT INTO STUDY_TIME (USERNAME, TIME, DATE) values ('user', 7200 , '2022-06-20');
INSERT INTO STUDY_TIME (USERNAME, TIME, DATE) values ('user', 24000 , '2022-06-21');
INSERT INTO STUDY_TIME (USERNAME, TIME, DATE) values ('user', 20000 , '2022-06-22');
INSERT INTO STUDY_TIME (USERNAME, TIME, DATE) values ('user', 8500 , '2022-06-23');
INSERT INTO STUDY_TIME (USERNAME, TIME, DATE) values ('user', 800 , '2022-06-24');
INSERT INTO STUDY_TIME (USERNAME, TIME, DATE) values ('user', 36000 , '2022-06-25');

INSERT INTO PLAN(ACTIVATE, PLAN_DATE, TITLE, USERNAME) values (0, sysdate(), '알고리즘공부', 'user');
INSERT INTO PLAN(ACTIVATE, PLAN_DATE, TITLE, USERNAME) values (0, sysdate(), 'java언어공부', 'user');
INSERT INTO PLAN(ACTIVATE, PLAN_DATE, TITLE, USERNAME) values (0, sysdate(), 'spring 프레임워크 공부', 'user');

INSERT INTO COMMUNITY( CONTENT, WRITE_DATE, TITLE, USERNAME) values('내용부분 test', sysdate(), 'admin의 글입니다.', 'admin');
INSERT INTO COMMUNITY( CONTENT, WRITE_DATE, TITLE, USERNAME) values('내용부분 test', sysdate(), 'user의 글입니다.', 'user');