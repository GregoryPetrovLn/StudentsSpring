package telran.spring.jpa.entities;
import javax.persistence.*;
@Entity
@Table(name="marks")
public class Mark {
	@GeneratedValue
	@Id
	int id;
	int mark;
	
	@ManyToOne
	Student student;
	@ManyToOne
	Subject subject;
public Mark() {
}
public Mark(int mark, Student student, Subject subject) {
	super();
	this.mark = mark;
	this.student = student;
	this.subject = subject;
}	


}
