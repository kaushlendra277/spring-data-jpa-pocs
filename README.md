# spring-data-jpa-pocs

### Problem Statement
```problem-statement
hibernate.QueryException: 
illegal attempt to dereference collection [student0_.id.adresses] with element property reference [id] 
[SELECT s FROM poc.spring.data.jpa.entities.Student s WHERE s.adresses.id = :aId]
- here Student entity has List<Address> and we are trying to query student by Address Id

```
```java

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

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "addr_id")
	private List<Address> adresses;

}

@Entity
@Table(name = "student_adresses")
@Getter
@Setter
@Builder
public class Address {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String city;

}

public interface StudentRepository extends CrudRepository<Student, Long>{

	// [PROBLEM]
	// @Query("SELECT s FROM Student s WHERE s.adresses.id = :aId")
	
	// [SOLUTION]
	@Query("SELECT s FROM Student s INNER JOIN s.adresses sa WHERE sa.id = :aId")
	Student findByAddressId(@Param("aId")Long addressId); 
}

```


```resolution
RESOLUTION: 
		- we write incorrect query as
			SELECT s FROM Student s WHERE s.adresses.id = :aId
		
		- we need to use JOIN as getting Student from addressId requires user-info from Address Table i.e userId.
			- SELECT s FROM Student s INNER JOIN s.adresses sa WHERE sa.id = :aId
			refer - https://stackoverflow.com/questions/24750754/org-hibernate-queryexception-illegal-attempt-to-dereference-collection
```

