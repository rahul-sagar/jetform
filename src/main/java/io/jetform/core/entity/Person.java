package io.jetform.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementGroup;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.TextArea;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Relation;
import io.jetform.core.enums.Type;
import io.jetform.core.enums.ValidationType;

@Entity
@Table(name="person")
@JetForm(actions = { @FormAction(name = "create", action = Action.CREATE,type=Type.SUBMIT, label = "Create"),
		@FormAction(name = "update", action = Action.UPDATE,type=Type.BUTTON, label = "Update"),
		@FormAction(name = "delete", action = Action.DELETE,type=Type.BUTTON, label = "Delete"),
		@FormAction(name = "list", action = Action.READ,type=Type.BUTTON, label = "Read") }
        )
public class Person {

	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private long id;
	
	@FormElement(listable = true,autocomplete = true ,validations = { @Validation(type = ValidationType.REQUIRED, value = "true") })
	private String firstName;
	
	@FormElement(listable = true,validations = { @Validation(type = ValidationType.REQUIRED, value = "true") })
	private String lastName;
	
	@FormElement(listable = true, number = @Number(format = "#####"))
	private int age;
	
	@FormElement(form = @Form(formClass = "io.jetform.core.entity.Address",relation = Relation.ONE_TO_ONE),listable=true)
	@OneToOne(cascade = {CascadeType.ALL})
	private Address address = new Address();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", address=" + address + "]";
	}

}
