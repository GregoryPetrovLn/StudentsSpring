package telran.spring.jpa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.support_interfaces.IntervalMarks;
import telran.spring.jpa.dto.support_interfaces.StudentMarksCount;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.entities.Mark;
import telran.spring.jpa.entities.Student;
import telran.spring.jpa.entities.Subject;
import telran.spring.jpa.repo.*;
import telran.spring.jpa.service.interfaces.Students;

import java.util.List;

@Service
public class StudentsJpaImpl implements Students {
    @Autowired
    StudentRepository students;
    @Autowired
    SubjectRepository subjects;
    @Autowired
    MarkRepository marks;

    //================================
    //========= Students =============
    //================================

    @Override
    @Transactional
    public void addStudent(StudentDto studentDto) {
        Student student = new Student(studentDto.stid, studentDto.name);
        students.save(student);
    }

    @Override
    @Transactional
    public void addSubject(SubjectDto subjectDto) {
        Subject subject = new Subject(subjectDto.suid, subjectDto.subject);
        subjects.save(subject);
    }

    @Override
    @Transactional
    public void addMark(MarkDto markDto) {
        Student student = students.findById(markDto.stid).orElse(null);
        Subject subject = subjects.findById(markDto.suid).orElse(null);
        Mark mark = new Mark(markDto.mark, student, subject);
        marks.save(mark);
    }

    @Override
    public void deleteMarks(String name, String subject) {
        //TODO
    }

    //================================
    //========= Marks ================
    //================================

    @Override
    public List<String> bestStudents() {
        return marks.findBestStudents();
    }

    @Override
    public List<String> bestStudents(int nStudents) {
        return marks.findTopBestStudents(nStudents);
    }

    @Override
    public List<String> bestStudentsSubject(String subject) {
        return marks.findBestStudentsSubject(subject);
    }

    @Override
    public List<String> bestStudentsSubject(int nStudents, String subject) {
        return marks.findTopBestStudentsSubject(nStudents, subject);
    }

    @Override
    public List<StudentMarksCount> getStudentMarksCount() {
        return marks.findStudentMarksCount();
    }


    //================================
    //============ Subjects ==========
    //================================


    @Override
    public List<String> getSubjectsHighestMarks() {
        return subjects.getSubjectsHighestMarks();
    }

    @Override
    public List<String> getTopSubjectsHighestMarks(int nSubjects) {
        return subjects.getTopSubjectsHighestMarks(nSubjects);
    }

    @Override
    public List<Integer> findMarksEncountered(String subject) {
        return subjects.findMarksEncountered(subject);
    }

    @Override
    public List<Integer> findTopMarksEncountered(String subject, int nMarks) {
        return subjects.findTopMarksEncountered(subject, nMarks);
    }

    @Override
    public List<IntervalMarks> findIntervalMarks(int interval) {
        return marks.findIntervalMarks(interval);
    }
}
