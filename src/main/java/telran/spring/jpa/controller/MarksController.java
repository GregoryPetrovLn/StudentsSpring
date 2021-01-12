package telran.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.spring.jpa.dto.supported_interfaces.IntervalMarks;
import telran.spring.jpa.service.interfaces.Students;

import java.util.List;

@RestController
@RequestMapping(value = "/marks")
public class MarksController {
    @Autowired
    Students students;

    @GetMapping("/widespread/{subject}")
    List<Integer> marksSubject(@RequestParam(name = "n_marks", required = true) int nMarks,
                               @PathVariable("subject") String subject) {
        return students.getTopMarksEncountered(nMarks, subject);
    }

    @GetMapping("/distribution")
    List<IntervalMarks> marksDistribution(@RequestParam(name = "interval", defaultValue = "10") int interval) {
        return students.getIntervalsMarks(interval);
    }

    @DeleteMapping("/{subject}")
    void deleteMarks(@PathVariable("subject") String subject,
                     @RequestParam(name = "name", required = true) String name) {
        students.deleteMarks(name, subject);
    }

    @GetMapping("/set/avg")
    void setAvgMark(@RequestParam(name = "name", required = true) String name,
                    @RequestParam(name = "subject", required = true) String subject){
        students.setAvgMark(name,subject);
    }

    @GetMapping("/get/avg")
    Integer getAvgMark(@RequestParam(name = "name", required = true) String name,
                    @RequestParam(name = "subject", required = true) String subject){
       return students.getAvgMark(name,subject);
    }


}
