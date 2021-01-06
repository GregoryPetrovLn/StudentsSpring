package telran.spring.jpa.controller;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import telran.spring.jpa.dto.*;
import telran.spring.jpa.generatedDTO.*;
import telran.spring.jpa.service.interfaces.Students;
import static telran.spring.jpa.generatedDTO.GetRandomInt.*;
import java.util.stream.Stream;

@Component
public class StudentsController {
    private final int AMOUNT_OF_GENERATED_STUDENTS = 20;
    private final int AMOUNT_OF_GENERATED_SUBJECTS = 20;
    private final int AMOUNT_OF_GENERATED_MARKS = 100;

    @Autowired
    Students students;

    @PostConstruct
    void fillDB() {
        addStudents();
        addSubjects();
        addMarks();
    }

    private void addMarks() {
        Stream.iterate(0, n -> n + 1)
                .limit(AMOUNT_OF_GENERATED_MARKS)
                .forEach(i -> students.addMark(new MarkDto(
                        randomInt(0, Names.names.length - 1),
                        randomInt(0, Subjects.subjects.length - 1),
                        Marks.getRandomMark())));
    }

    private void addSubjects() {
        Stream.iterate(0, n -> n + 1)
                .limit(AMOUNT_OF_GENERATED_SUBJECTS)
                .forEach(i -> students.addSubject(
                        new SubjectDto(i, Subjects.getSubject(i))));
    }

    private void addStudents() {
        Stream.iterate(0, n -> n + 1)
                .limit(AMOUNT_OF_GENERATED_STUDENTS)
                .forEach(i -> students.addStudent(
                        new StudentDto(i,
                                Names.getName(randomInt(0, Names.names.length - 1)))));
    }
}
