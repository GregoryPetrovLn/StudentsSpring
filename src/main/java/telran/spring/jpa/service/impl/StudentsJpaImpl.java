package telran.spring.jpa.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
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

}
