package poc.spring.data.jpa.entities.repos;

import org.springframework.data.repository.CrudRepository;

import poc.spring.data.jpa.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
