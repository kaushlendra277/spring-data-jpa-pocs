package poc.spring.data.jpa.rest.entities.repos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
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
		
		Optional<Student> getStudent = studentRepository.findById(savedEntity.getId());
		assertThat(getStudent.get().getStatus().getId() == 3);
	}
	
	@Test
	void test_SoftDeleteStudentByStudent() {
		Student entity = new Student();
		entity.setName("KSC");
		
		Student savedEntity = studentRepository.save(entity);
		studentRepository.delete(savedEntity);
		Optional<Student> getStudent = studentRepository.findById(savedEntity.getId());
		assertThat(getStudent.get().getStatus().getId() == 3);
	}
	
	@Test
	void test_BulkSoftDeleteStudent() {
		Student entity = new Student();
		entity.setName("KSC");
		
		Student savedEntity = studentRepository.save(entity);
		// studentRepository.deleteAll(Stream.of(savedEntity).collect(Collectors.toList()));
		studentRepository.deleteAll();
		Optional<Student> getStudent = studentRepository.findById(savedEntity.getId());
		assertThat(getStudent.get().getStatus().getId() == 3);
	}

}
