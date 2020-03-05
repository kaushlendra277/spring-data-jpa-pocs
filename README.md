# spring-data-jpa-pocs

### Problem Statement
```problem-statement
	Using EntityManager to mass update
```
```java

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

@Repository
@Transactional
public class StudentEntityManager {

	@Autowired
	private EntityManager em;
	
	public void createStudent(Student request) {
		em.persist(request);
	}
	
	public Student getStudentById(Long id) {
		TypedQuery<Student> query = 
		em.createQuery("Select  c  From Student c where id = "+id, Student.class);
		return query.getResultList().get(0);
	}
	
	public void updateStudentsByIds(Long... ids) {
		StringJoiner joiner = new StringJoiner(", "," ( "," )");
		Stream.of(ids).forEach(id -> joiner.add(id+""));
		Query query = em.createQuery("Update Student set name = 'new-name'  WHERE id IN "+joiner.toString());
		query.executeUpdate();
	}
}
```


### RESOLUTION:
```resolution 
	- refer above StudentEntityManagerTest.java
```

#### Note -
```note
	- @Transaction over StudentEntityManager is mandatory 
	because we are createing and updating Student records

```