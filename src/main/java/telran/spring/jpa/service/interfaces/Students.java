package telran.spring.jpa.service.interfaces;

import telran.spring.jpa.dto.*;

public interface Students {
	void addStudent(StudentDto studentDto);
	void addSubject(SubjectDto subjectDto);
	void addMark(MarkDto markDto);

}
