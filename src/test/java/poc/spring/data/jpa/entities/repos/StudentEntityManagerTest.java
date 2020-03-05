package poc.spring.data.jpa.entities.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import poc.spring.data.jpa.entities.Student;

@SpringBootTest
class StudentEntityManagerTest {

	@Autowired
	private StudentEntityManager studentEntityManager;
	
	@Test
	void test() {
		Student request = Student.builder().name("KSC").build();
		studentEntityManager.createStudent(request );
		
		Student getStudent = studentEntityManager.getStudentById(1L);
		System.out.println(getStudent);
		
		studentEntityManager.updateStudentsByIds(1L, 2L, 3L);
		
		getStudent = studentEntityManager.getStudentById(1L);
		System.out.println(getStudent);
	}

}
