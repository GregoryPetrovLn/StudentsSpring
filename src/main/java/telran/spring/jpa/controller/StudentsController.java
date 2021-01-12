package telran.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.spring.jpa.dto.supported_interfaces.IntervalMarks;
import telran.spring.jpa.dto.supported_interfaces.StudentMarksCount;
import telran.spring.jpa.service.interfaces.Students;


@RestController
@RequestMapping(value = "/students")
public class StudentsController {
    @Autowired
    Students students;

    @GetMapping("/best")
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

    @GetMapping("/marks/count")
    List<StudentMarksCount> studentsMarksCount() {
        return students.getStudentsMarksCount();
    }


}
