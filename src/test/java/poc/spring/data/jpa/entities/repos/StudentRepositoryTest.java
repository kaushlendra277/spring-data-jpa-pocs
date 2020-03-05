package poc.spring.data.jpa.entities.repos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import poc.spring.data.jpa.entities.Address;
import poc.spring.data.jpa.entities.Student;

@SpringBootTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED) // WE need to add this because savedEntity.getAdresses() was
	 												   // was throwing com.sun.jdi.InvocationException (but why ?)
													   // it should be throwing LazyInitException 
	void test_GetStudentByAddressId() {
		Student entity = new Student();
		entity.setName("KSC");
		
		List<Address> studentAddresses = new ArrayList<Address>();
		studentAddresses.add(Address.builder().city("Pune").build());
		studentAddresses.add(Address.builder().city("Mumbai").build());
		entity.setAdresses(studentAddresses);
		Student savedEntity = studentRepository.save(entity);
	
		savedEntity = studentRepository.findByAddressId(savedEntity.getAdresses().get(0).getId());
		assertThat(savedEntity != null);
		assertThat(savedEntity.getAdresses() != null);
		assertThat(savedEntity.getAdresses().size() == entity.getAdresses().size());
		
	}

}
