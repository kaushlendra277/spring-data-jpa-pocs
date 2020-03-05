package poc.spring.data.jpa.entities.repos;

import java.util.StringJoiner;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import poc.spring.data.jpa.entities.Student;

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
