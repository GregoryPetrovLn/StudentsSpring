package telran.spring.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.spring.jpa.entities.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
