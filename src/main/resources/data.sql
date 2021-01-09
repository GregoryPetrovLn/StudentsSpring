create or replace view students_marks_subjects  as
    select stid, suid, name, mark, subject from students join marks on stid = student_stid
    join subjects on suid = subject_suid;