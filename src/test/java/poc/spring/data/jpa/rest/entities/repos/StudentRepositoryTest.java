package poc.spring.data.jpa.rest.entities.repos;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import poc.spring.data.jpa.entities.Student;
import poc.spring.data.jpa.entities.repos.StudentRepository;

@SpringBootTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	void test_SoftDeleteStudentById() {
		Student entity = new Student();
		entity.setName("KSC");
		
		Student savedEntity = studentRepository.save(entity);
		studentRepository.deleteById(savedEntity.getId());
		
	}
	
	@Test
	void test_SoftDeleteStudentByStudent() {
		Student entity = new Student();
		entity.setName("KSC");
		
		Student savedEntity = studentRepository.save(entity);
		studentRepository.delete(savedEntity);
		
	}
	
	@Test
	void test_BulkSoftDeleteStudent() {
		Student entity = new Student();
		entity.setName("KSC");
		
		Student savedEntity = studentRepository.save(entity);
		// studentRepository.deleteAll(Stream.of(savedEntity).collect(Collectors.toList()));
		studentRepository.deleteAll();
		
	}

}
