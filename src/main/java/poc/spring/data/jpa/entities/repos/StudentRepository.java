package poc.spring.data.jpa.entities.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poc.spring.data.jpa.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

	// [PROBLEM]
	// @Query("SELECT s FROM Student s WHERE s.adresses.id = :aId")
	
	// [SOLUTION]
	@Query("SELECT s FROM Student s INNER JOIN s.adresses sa WHERE sa.id = :aId")
	Student findByAddressId(@Param("aId")Long addressId); 
}
