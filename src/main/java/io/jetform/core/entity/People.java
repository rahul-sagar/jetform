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

@Entity
@Table(name="people")
@FormEntity(actions = {
		@FormAction(url = "/create", action = Action.CREATE, buttonOrLinkValue = "Create"),
		@FormAction(url="/update",action = Action.UPDATE, buttonOrLinkValue = "Update"),
		@FormAction(url = "/delete",action = Action.DELETE, buttonOrLinkValue = "Delete"),
		@FormAction(url = "/list",action = Action.READ, buttonOrLinkValue = "Read")})
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
