delete from marks;
delete from students;
delete from subjects;
insert into students (stid, name) values (1, 'Moshe');
insert into students (stid, name) values (2, 'Sara');
insert into subjects (suid, subject) values (1, 'Java'), ( 2, 'React');
insert into marks (id, student_stid, subject_suid, mark) values (1, 1, 1, 100), (2, 2, 1, 60),
                                                                (3, 1, 2, 100), (4, 1, 1, 90);