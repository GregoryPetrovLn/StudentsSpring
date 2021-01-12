package telran.spring.jpa.service.interfaces;


import java.util.List;

import telran.spring.jpa.dto.*;
import telran.spring.jpa.dto.supported_interfaces.IntervalMarks;
import telran.spring.jpa.dto.supported_interfaces.StudentMarksCount;

public interface Students {
    void addStudent(StudentDto studentDto);

    void addSubject(SubjectDto subjectDto);

    void addMark(MarkDto markDto);

    void deleteMarks(String name, String subject);

    List<String> bestStudents(); //list of student names having average mark higher than the total one

    List<String> bestStudents(int nStudents); //list of nStudents best student names based on average marks

    List<String> bestStudentsSubject(String subject); //list of student names having average mark on a given subject higher than the total average mark on that subject

    List<String> bestStudentsSubject(int nStudents, String subject);

    List<StudentMarksCount> getStudentsMarksCount();

    List<String> getSubjectsHighestMarks();

    List<String> getTopSubjectsHighestMarks(int nSubjects);

    List<Integer> getTopMarksEncountered(int nMarks, String subject);

    List<IntervalMarks> getIntervalsMarks(int interval);

    void deleteStudent(String string);

    List<Integer> getStudentSubjectMarks(String name, String subject);

    void setAvgMark(String name, String subject);

    Integer getAvgMark(String name, String subject);
}
