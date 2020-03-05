package poc.spring.data.jpa.entities.repos;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import poc.spring.data.jpa.entities.Address;
import poc.spring.data.jpa.entities.Student;

@SpringBootTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	void test_GetStudentByAddressId() {
		Student entity = new Student();
		entity.setName("KSC");
		
		List<Address> studentAddresses = new ArrayList<Address>();
		studentAddresses.add(Address.builder().city("Pune").build());
		studentAddresses.add(Address.builder().city("Mumbai").build());
		entity.setAdresses(studentAddresses);
		Student savedEntity = studentRepository.save(entity);
	
		savedEntity = studentRepository.findByAddressId(savedEntity.getAdresses().get(0).getId());
		System.out.println(savedEntity);
	}

}
