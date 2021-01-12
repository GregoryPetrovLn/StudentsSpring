package telran.spring.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.entities.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from students where name=:name", nativeQuery = true)
    Student findByName(@Param("name") String name);

    /*******************************************************************************/
    @Modifying
    @Query(value = "delete from students where name=:name", nativeQuery = true)
    void deleteStudent(@Param("name") String name);


    String sqlRequest3 = "select mark " +
            "from students_marks_subjects " +
            "where subject = :subject " +
            "and name = :name " +
            "order by mark desc";

    @Query(value = sqlRequest3, nativeQuery = true)
    List<Integer> getStudentSubjectMarks(@Param("name") String name, @Param("subject") String subject);
}
