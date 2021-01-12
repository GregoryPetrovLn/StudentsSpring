package telran.spring.jpa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.jpa.dto.supported_interfaces.IntervalMarks;
import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.supported_interfaces.StudentMarksCount;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.entities.Mark;
import telran.spring.jpa.entities.Student;
import telran.spring.jpa.entities.Subject;
import telran.spring.jpa.repo.*;
import telran.spring.jpa.service.interfaces.Students;

@Service
public class StudentsJpaImpl implements Students {
    @Autowired
    StudentRepository students;
    @Autowired
    SubjectRepository subjects;
    @Autowired
    MarkRepository marks;

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
    @Transactional
    public void deleteMarks(String name, String subject) {
        marks.deleteMarks(name, subject);

    }

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
    public List<StudentMarksCount> getStudentsMarksCount() {

        return marks.findStudentsMarksCount();
    }

    @Override
    public List<String> getSubjectsHighestMarks() {

        return marks.findSubjectsHighestMarks();
    }

    @Override
    public List<String> getTopSubjectsHighestMarks(int nSubjects) {

        return marks.findTopSubjectsHighestMarks(nSubjects);
    }

    @Override
    public List<Integer> getTopMarksEncountered(int nMarks, String subject) {
        return marks.findTopMarksEncountered(nMarks, subject);
    }

    @Override
    public List<IntervalMarks> getIntervalsMarks(int interval) {

        return marks.findIntervalsMarks(interval);
    }

    @Override
    @Transactional
    public void deleteStudent(String name) {
//		Student student = students.findByName(name);
//		if (student != null) {
//			students.delete(student);
//		}
        students.deleteStudent(name);


    }

    @Override
    public List<Integer> getStudentSubjectMarks(String name, String subject) {
        return students.getStudentSubjectMarks(name, subject);
    }

    @Override
    public void setAvgMark(String name, String subject) {
        int avgMark = getAvgMark(name, subject);
        deleteMarks(name, subject);
        marks.setAvgMark(name,subject,avgMark);
    }

    @Override
    public Integer getAvgMark(String name, String subject) {
        return marks.getAvgMark(name, subject);
    }


}
