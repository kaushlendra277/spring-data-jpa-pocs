package poc.spring.data.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE students SET status_id = 3 WHERE id = ?")
public class Student extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	// Not required for this POC
	/*
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "addr_id")
	private List<Address> adresses;
	*/
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
