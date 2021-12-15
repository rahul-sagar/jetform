package io.jetform.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Type;
import io.jetform.core.enums.ValidationType;

@Entity
@Table(name = "tbl_Address")
@JetForm(listIndex=false,actions = { @FormAction(name = "create", action = Action.CREATE,type=Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE,type=Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE,type=Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ,type=Type.BUTTON, label = "Read") })
public class Address {
 
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @FormElement(hidden = @Hidden(value = "0"))
	  private long id;	
	  
	  @FormElement(listable = true, autocomplete=true,validations = {@Validation(type = ValidationType.REQUIRED, value = "true"),@Validation(type = ValidationType.MINLENGTH, value = "2")})
	  private String city;
	   
	  @FormElement(listable = true, autocomplete=true,validations = {@Validation(type = ValidationType.REQUIRED, value = "true"),@Validation(type = ValidationType.MINLENGTH, value = "2")})
	  private String state;
	   
	  @FormElement(listable = true, autocomplete=true,validations = {@Validation(type = ValidationType.REQUIRED, value = "true"),@Validation(type = ValidationType.MINLENGTH, value = "2")})
	  private String street;
	  
	  @OneToOne(cascade = {CascadeType.ALL})
	  @FormElement(form = @Form(formClass = "io.jetform.core.entity.Contact"))
	  private Contact contact;
	  

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", state=" + state + ", street=" + street + ", contact="
				+ contact + "]";
	}

}
