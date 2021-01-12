package telran.spring.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.spring.jpa.entities.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    String sqlRequest = "select subject from students_marks_subjects group by subject\n" +
            "order by sum(mark) desc";

    @Query(value = sqlRequest, nativeQuery = true)
    List<String> getSubjectsHighestMarks();
    //================================
    //================================
    //================================

    String sqlRequest2 = "select subject, sum(mark) from students_marks_subjects group by subject\n" +
            "order by sum desc limit :n_subjects";

    @Query(value = sqlRequest2, nativeQuery = true)
    List<String> getTopSubjectsHighestMarks(@Param("n_subjects") int nSubjects);
    //================================
    //================================
    //================================

    String sqlRequest3 = "select mark from students_marks_subjects\n" +
            "where subject = :subject\n" +
            "and mark > (\n" +
            "select avg(mark) from marks\n" +
            ")\n" +
            "group by mark, subject\n" +
            "order by count(*) desc,\n" +
            "mark desc";

    @Query(value = sqlRequest3, nativeQuery = true)
    List<Integer> findMarksEncountered(@Param("subject") String subject);
    //================================
    //================================
    //================================

    String sqlRequest4 = "select mark from students_marks_subjects\n" +
            "where subject = :subject\n" +
            "and mark > (\n" +
            "select avg(mark) from marks\n" +
            ")\n" +
            "group by mark, subject\n" +
            "order by count(*) desc,\n" +
            "mark desc limit :n_marks";

    @Query(value = sqlRequest4, nativeQuery = true)
    List<Integer> findTopMarksEncountered(@Param("subject") String subject, @Param("n_marks") int nMarks);
    //================================
    //================================
    //================================

}
