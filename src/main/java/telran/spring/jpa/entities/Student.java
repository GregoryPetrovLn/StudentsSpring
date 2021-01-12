package telran.spring.jpa.entities;


import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "students")
public class Student {
    @Id
    int stid;
    @Column(unique = true, nullable = false)
    String name;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Mark> marks;

    public Student() {

    }

    public Student(int stid, String name) {
        this.stid = stid;
        this.name = name;
    }


}
