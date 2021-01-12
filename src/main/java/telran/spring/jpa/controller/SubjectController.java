package telran.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.spring.jpa.service.interfaces.Students;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    Students students;

    @GetMapping("/marks/highest")
    List<String> getSubjectsHighestMarks(@RequestParam(name = "n_subjects", defaultValue = "0") int nSubjects) {
        if (nSubjects == 0) {
            return students.getSubjectsHighestMarks();
        }
        return students.getTopSubjectsHighestMarks(nSubjects);
    }
}
