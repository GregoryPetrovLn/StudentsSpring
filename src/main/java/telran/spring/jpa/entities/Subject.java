package telran.spring.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*; //JPA

@Entity
@Table(name = "subjects")
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    int suid;
    @Column(unique = true, nullable = false)
    String subject;

}
