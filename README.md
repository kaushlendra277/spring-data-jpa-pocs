# spring-data-jpa-pocs

### Problem Statement
```problem-statement
	Using @SqlDelete and @SqlDeleteAll for soft delete operation(s).
```
### Pre-Populate data

```sql
	INSERT INTO statuses (id) VALUES (1)
	;
	INSERT INTO statuses (id) VALUES (2)
	;
	INSERT INTO statuses (id) VALUES (3)
```

### Java files

```java

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE students SET status_id = 3 WHERE id = ?") // NOTE THIS
public class Student extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}


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

```

#### Note -
```note
	- @SqlDelete , @SqlDeleteAll are hibernate specific annotation
```