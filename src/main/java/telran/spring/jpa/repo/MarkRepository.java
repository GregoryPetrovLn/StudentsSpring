package telran.spring.jpa.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.dto.supported_interfaces.IntervalMarks;
import telran.spring.jpa.dto.supported_interfaces.StudentMarksCount;
import telran.spring.jpa.entities.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    @Query(value = "select name from students join marks"
            + " on stid=student_stid group by name having avg(mark) > "
            + " (select avg(mark) from marks) order by avg(mark) desc", nativeQuery = true)
    List<String> findBestStudents();

    /******************************************************************/
    @Query(value = "select name from students join marks"
            + " on stid=student_stid group by name "
            + "  order by avg(mark) desc limit :n_students", nativeQuery = true)
    List<String> findTopBestStudents(@Param("n_students") int nStudents);

    /****************************************************************************/
    @Query(value = "select name from students_marks_subjects where subject=:subject "
            + "group by name having avg(mark) >= (select avg(mark) from students_marks_subjects "
            + "where subject=:subject) order by avg(mark)", nativeQuery = true)
    List<String> findBestStudentsSubject(@Param("subject") String subject);

    /*********************************************************************************/
    @Query(value = "select name from students_marks_subjects where subject=:subject "
            + "group by name order by avg(mark) desc limit :n_students "
            , nativeQuery = true)
    List<String> findTopBestStudentsSubject(@Param("n_students") int nStudents, @Param("subject") String subject);

    @Query(value = "select name, count(mark) as marksCount from students left join marks on "
            + "stid=student_stid group by name", nativeQuery = true)
    List<StudentMarksCount> findStudentsMarksCount();
    /***********************************************************************/
    /*********************HW #53*************************************************/
    @Query(value = "select subject from marks_subjects group by subject order by sum(mark) desc limit :n_subjects", nativeQuery = true)
    List<String> findTopSubjectsHighestMarks(@Param("n_subjects") int nSubjects);

    /**********************************************************************************/
    @Query(value = "select subject from marks_subjects group by subject having sum(mark) >"
            + " (select avg(total) from (select sum(mark) as total from marks_subjects group by subject )as totals) order by sum(mark) desc", nativeQuery = true)
    List<String> findSubjectsHighestMarks();

    /***************************************************************************************************/
    @Query(value = "select mark from marks_subjects where subject=:subject group by mark"
            + " order by count(*) desc limit :n_marks", nativeQuery = true)
    List<Integer> findTopMarksEncountered(@Param("n_marks") int nMarks, @Param("subject") String subject);

    /********************************************************************************************************/
    @Query(value = "select mark/:interval * :interval as min, :interval * (mark/:interval + 1) - 1 as max, count(*) as occurrences from " +
            " marks group by min, max  order by min", nativeQuery = true)
    List<IntervalMarks> findIntervalsMarks(@Param("interval") int interval);

    /***********************************************************************************/
    @Modifying
    @Query(value = "delete from marks where student_stid=(select stid from students where "
            + "name=:name) and subject_suid=(select suid from subjects where subject=:subject)",
            nativeQuery = true)
    void deleteMarks(@Param("name") String name, @Param("subject") String subject);

    /*******************************************************************/



    @Modifying
    @Query(value = "update marks " +
            " set mark = :mark where student_stid=(select stid from students where name=:name) " +
            " and subject_suid=(select suid from subjects where subject=:subject)",  nativeQuery = true)
    void setAvgMark(@Param("name") String name, @Param("subject") String subject, @Param("mark") int mark);


    @Query(value = "select round(avg(mark), 0) " +
            "from students_marks_subjects " +
            "where subject = :subject " +
            "and name = :name",
            nativeQuery = true)
    Integer getAvgMark(@Param("name") String name, @Param("subject") String subject);


}
