package telran.spring.jpa.service.interfaces;


import org.springframework.data.repository.query.Param;
import telran.spring.jpa.dto.*;

import java.util.*;


public interface Students {
    void addStudent(StudentDto studentDto);

    void addSubject(SubjectDto subjectDto);

    void addMark(MarkDto markDto);

    void deleteMarks(String name, String subject);

    List<String> bestStudents(); // list of student names have avg mark higher than the total one

    List<String> bestStudents(int nStudents); // list of nStudents best students based on  avg marks

    List<String> bestStudentsSubject(String subject); // list of student names having avg mark
    // on a given subject higher than the total avg mark on that subject

    List<String> bestStudentsSubject(int nStudents, String subject);

    List<StudentMarksCount> getStudentMarksCount();

    List<String> getSubjectsHighestMarks();

    List<String> getTopSubjectsHighestMarks(int nSubjects);

    List<Integer> findMarksEncountered(String subject);

    List<Integer> findTopMarksEncountered(String subject ,int nMarks);




}
