package io.jetform.core.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class EntityClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	@Override
	public String toString() {

		return "MasterEntity [id=" + id + "]";
	}


}
