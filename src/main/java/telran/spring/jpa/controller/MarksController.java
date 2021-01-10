package telran.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.spring.jpa.dto.support_interfaces.IntervalMarks;
import telran.spring.jpa.service.interfaces.Students;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {
    @Autowired
    Students students;

    @GetMapping("/widespread/{subject}")
    List<Integer> getTopMarksEncountered(@PathVariable String subject,
                                         @RequestParam(name = "n_marks", defaultValue = "0") int nMarks) {

        if (nMarks == 0) {
            return students.findMarksEncountered(subject);
        }
        return students.findTopMarksEncountered(subject, nMarks);
    }

    @GetMapping("/distribution")
    List<IntervalMarks> getIntervalMarks(@RequestParam(name = "m_interval", defaultValue = "10") int interval){
        return students.findIntervalMarks(interval);
    }



}
