package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormEntity;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Type;

@Entity
@Table(name="people")
@FormEntity(actions = {
		@FormAction(name = "/create", action = Action.CREATE,type=Type.BUTTON ,label = "Create"),
		@FormAction(name="/update",action = Action.UPDATE, type=Type.BUTTON ,label = "Update"),
		@FormAction(name = "/delete",action = Action.DELETE, type=Type.BUTTON ,label = "Delete"),
		@FormAction(name = "/list",action = Action.READ, type=Type.BUTTON ,label = "Read")})
public class People {
	
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private int age;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "People [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}
	
}
