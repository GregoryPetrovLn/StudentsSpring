package telran.spring.jpa.entities;
import javax.persistence.*; //JPA
@Entity
@Table(name="subjects")
public class Subject {
@Id
int suid;
@Column(unique = true, nullable = false)
String subject;
public Subject() {
}
public Subject(int suid, String subject) {
	this.suid = suid;
	this.subject = subject;
}

}
