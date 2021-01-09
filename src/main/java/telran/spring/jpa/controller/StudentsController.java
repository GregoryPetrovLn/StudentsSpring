package telran.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.service.interfaces.Students;

import java.util.List;

@RestController
public class StudentsController {
    private static final String STUDENTS_BEST = "/students/best";
    private static final String STUDENTS_MARKS_COUNT = "/students/marks/count";
    public static final String SUBJECT_MARKS_HIGHEST = "/subjects/marks/highest";
    public static final String MARKS_WIDESPREAD_SUBJECT = "/marks/widespread/{subject}";
    public static final String MARKS_DISTRIBUTION = "/marks/distribution";
    @Autowired
    Students students;

    @GetMapping(STUDENTS_BEST)
    List<String> bestStudentNames(@RequestParam(name = "n_students", defaultValue = "0") int nStudents,
                                  @RequestParam(name = "subject", required = false) String subject) {

        if (nStudents == 0 && subject == null) {
            return students.bestStudents();
        }
        if (nStudents == 0) {
            return students.bestStudentsSubject(subject);
        }
        if (subject == null) {
            return students.bestStudents(nStudents);
        }
        return students.bestStudentsSubject(nStudents, subject);
    }

    @GetMapping(STUDENTS_MARKS_COUNT)
    List<StudentMarksCount> studentMarksCounts() {
        return students.getStudentMarksCount();
    }


    @GetMapping(SUBJECT_MARKS_HIGHEST)
    List<String> getSubjectsHighestMarks(@RequestParam(name = "n_subjects", defaultValue = "0") int nSubjects) {
        if (nSubjects == 0) {
            return students.getSubjectsHighestMarks();
        }
        return students.getTopSubjectsHighestMarks(nSubjects);
    }

    @GetMapping(MARKS_WIDESPREAD_SUBJECT)
    List<Integer> getTopMarksEncountered( @PathVariable String subject,
                                          @RequestParam(name = "n_marks", defaultValue = "0") int nMarks) {

        if (nMarks == 0) {
            return students.findMarksEncountered(subject);
        }

        return students.findTopMarksEncountered(subject, nMarks);
    }


}
























