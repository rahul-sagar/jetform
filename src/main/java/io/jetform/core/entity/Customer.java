package io.jetform.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementGroup;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Relation;
import io.jetform.core.enums.Type;
import io.jetform.core.enums.ValidationType;

@Entity
@Table(name = "tbl_customer")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") },
        groups = {@FormElementGroup(id = "personal_information",elementsPerRow = 2,label = "Personal Information"),
        		  @FormElementGroup(id = "customer_address",elementsPerRow = 1,label = "Address Info")}
        ,formTemplate = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private Long id;

	@FormElement(listable = true, autocomplete = true, validations = {
			@Validation(type = ValidationType.REQUIRED, value = "true"),
			@Validation(type = ValidationType.MINLENGTH, value = "2") },group = "personal_information")
	private String firstName;

	@FormElement(listable = true, validations = {
			@Validation(type = ValidationType.REQUIRED, value = "true"),
			@Validation(type = ValidationType.MINLENGTH, value = "2") },group = "personal_information")
	private String lastName;

	
	@FormElement(form = @Form(formClass = "io.jetform.core.entity.Address",relation = Relation.ONE_TO_MANY),listable=true,group = "customer_address")
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Address> address = new ArrayList<>();
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", LastName=" + lastName + ", address=" + address
				+ "]";
	}

}