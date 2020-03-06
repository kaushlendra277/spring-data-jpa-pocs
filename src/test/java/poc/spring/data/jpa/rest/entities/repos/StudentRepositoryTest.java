package poc.spring.data.jpa.rest.entities.repos;

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
		studentRepository.delete(savedEntity);
		
	}

}
