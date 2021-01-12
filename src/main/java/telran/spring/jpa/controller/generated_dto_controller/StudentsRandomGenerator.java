package telran.spring.jpa.controller.generated_dto_controller;


import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.service.interfaces.Students;

@Component
public class StudentsRandomGenerator {
    private static final int N_MARKS = 100;
    private static final int MIN_MARK = 60;
    private static final int MAX_MARK = 100;
    @Autowired
    Students students;
    String names[] = {"Abraham", "Sarah", "Itshak", "Rahel", "Asaf", "Yacob", "Rivka", "Yosef",
            "Benyanim", "Dan", "Ruben", "Moshe", "Aron", "Yehashua", "David", "Salomon", "Nefertity",
            "Naftaly", "Natan", "Asher"};
    String subjects[] = {"Java core", "Java Technologies",
            "Spring Data", "Spring Security", "Spring Cloud", "CSS", "HTML", "JS", "React", "Material-UI"};

    @PostConstruct
    void fillDB() {

        addStudents();
        addSubjects();
        addMarks();
    }

    private void addMarks() {
        for (int i = 0; i < N_MARKS; i++) {
            students.addMark(new MarkDto(getRandomNumber(1, names.length),
                    getRandomNumber(1, subjects.length), getRandomNumber(MIN_MARK, MAX_MARK)));
        }

    }

    private int getRandomNumber(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private void addSubjects() {
        for (int i = 1; i <= subjects.length; i++) {
            students.addSubject(new SubjectDto(i, subjects[i - 1]));
        }

    }

    private void addStudents() {
        for (int i = 1; i <= names.length; i++) {
            students.addStudent(new StudentDto(i, names[i - 1]));
        }

    }
}
