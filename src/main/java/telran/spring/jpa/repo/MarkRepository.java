package telran.spring.jpa.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.entities.Mark;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    //find best students
    String sqlRequest = "select name from students " +
            "join marks on stid = student_stid group by name " +
            "having avg(mark) > (select avg(mark) from marks) " +
            "order by avg(mark) desc";

    @Query(value = sqlRequest, nativeQuery = true)
    List<String> findBestStudents();
    //================================
    //================================
    //================================

    //find top best students
    String sqlRequest2 = "select name from students " +
            "join marks on stid = student_stid group by name " +
            "order by avg(mark) desc " +
            "limit :n_students";

    @Query(value = sqlRequest2, nativeQuery = true)
    List<String> findTopBestStudents(@Param("n_students") int nStudents);
    //================================
    //================================
    //================================

    //find best student by subject
    String sqlRequest3 = "select name from students_marks_subjects " +
            "where subject = :subject " +
            "group by name having avg(mark) > " +
            "(" +
            "select avg(mark) from students_marks_subjects " +
            "where subject = :subject" +
            ") " +
            "order by avg(mark)";

    @Query(value = sqlRequest3, nativeQuery = true)
    List<String> findBestStudentsSubject(@Param("subject") String subject);
    //================================
    //================================
    //================================

    String sqlRequest4 = "select name from students_marks_subjects " +
            "where subject = :subject " +
            "group by name order by avg(mark) desc limit :n_students";

    @Query(value = sqlRequest4, nativeQuery = true)
    List<String> findTopBestStudentsSubject(@Param("n_students") int nStudents, @Param("subject") String subject);
    //================================
    //================================
    //================================

    String sqlRequest5 = "select name, count(mark) as marksCount " +
            "from students left join marks on stid = student_stid " +
            "group by name";
    @Query(value = sqlRequest5, nativeQuery = true)
    List<StudentMarksCount> findStudentMarksCount();



}
