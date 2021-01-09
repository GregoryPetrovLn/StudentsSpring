package telran.spring.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.spring.jpa.entities.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    String sqlRequest = "select subject, sum(mark) from students_marks_subjects group by subject\n" +
            "order by sum desc";

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

    String sqlRequest3 = "select mark, count(mark)\n" +
            "from students_marks_subjects\n" +
            "where subject = :subject\n" +
            "group by mark\n" +
            "order by mark desc";

    @Query(value = sqlRequest3, nativeQuery = true)
    List<Integer> findMarksEncountered(@Param("subject") String subject);
    //================================
    //================================
    //================================

    String sqlRequest4 = "select mark, count(mark)\n" +
            "from students_marks_subjects\n" +
            "where subject = :subject\n" +
            "group by mark\n" +
            "order by mark desc\n" +
            "limit :n_marks";

    @Query(value = sqlRequest4, nativeQuery = true)
    List<Integer> findTopMarksEncountered(@Param("subject") String subject, @Param("n_marks") int nMarks);
    //================================
    //================================
    //================================

}
