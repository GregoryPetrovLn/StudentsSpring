package telran.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import telran.spring.jpa.service.interfaces.Students;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectsController {
    @Autowired
    Students students;

    @GetMapping("/marks/count")
    List<Integer> getStudentSubjectMarks(@RequestParam(name = "name", required = true) String name,
                                         @RequestParam(name = "subject", required = true) String subject) {
        return students.getStudentSubjectMarks(name, subject);
    }

    @GetMapping("/marks/highest")
    List<String> subjectsMarksHighest(@RequestParam(name = "n_subjects", defaultValue = "0") int nSubjects) {
        return nSubjects == 0 ? students.getSubjectsHighestMarks() :
                students.getTopSubjectsHighestMarks(nSubjects);
    }
}
