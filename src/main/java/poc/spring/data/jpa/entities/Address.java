package poc.spring.data.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
