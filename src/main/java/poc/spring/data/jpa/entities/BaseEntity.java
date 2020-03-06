package poc.spring.data.jpa.entities;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;
}
