insert into student(id, username, password) values (1, 'sept', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e');

insert into student(id, username, password) values (2, 'sept2', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e');

insert into todo(id, username,description,target_date,is_done)values(10001, 'sept', 'Learn JPA', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)values(10002, 'sept', 'Learn Data JPA', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)values(10003, 'sept', 'Learn Microservices', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)values(10004, 'sept2', 'Deadline 1', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)values(10005, 'sept2', 'Deadline 2', sysdate(), false);

insert into course(id, username, coursename, code, status)values (1, 'sept', 'Programming 1', 'EEET0001', 'available');

insert into course(id, username, coursename, code, status)values (2, 'sept', 'Web Programming', 'EEET0002', 'available');

insert into course(id, username, coursename, code, status)values (3, 'sept', 'Software Fundamentals', 'COSC0034', 'full');

insert into course(id, username, coursename, code, status)values (4, 'sept', 'Software Testing', 'COSC0035', 'unavailable');

insert into course(id, username, coursename, code, status)values (5, 'sept', 'Capstone Project', 'EEET0003', 'available');

insert into enrolment(student_id, course_id)values (1, 1);

--insert into student_course(jwt_user_details_id, course_id)
--values (1L, 1)