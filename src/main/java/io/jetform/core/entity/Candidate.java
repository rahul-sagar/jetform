package io.jetform.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementGroup;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Relation;
import io.jetform.core.enums.Type;

@Entity
@Table(name = "tbl_candidate")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") },
         groups = {@FormElementGroup(id = "basic-info",label = "Basic Info",elementsPerRow = 2),
        		   @FormElementGroup(id = "experince",label = "Employee Experience",elementsPerRow = 1),
        		   }
,formTemplate = "invoice") //formTemplate = "invoice"
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FormElement(hidden = @Hidden(value = "0"),group = "basic-info")
	private int id;
	
	@Column(name = "firstName")
	@FormElement(group = "basic-info")
	private String firstName;
	
	@Column(name = "lastName")
	@FormElement(group = "basic-info")
	private String lastName;
	
	@Column(name = "pincode")
	@FormElement(group = "basic-info")
	private String pincode;
	
	@FormElement(listable = true,form = @Form(formClass = "io.jetform.core.entity.EmployeeExperience",relation = Relation.ONE_TO_MANY,inline = true),group = "experince")///madel false 
	@OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	List<EmployeeExperience> employeeExperince;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public List<EmployeeExperience> getEmployeeExperince() {
		return employeeExperince;
	}

	public void setEmployeeExperince(List<EmployeeExperience> employeeExperince) {
		this.employeeExperince = employeeExperince;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pincode=" + pincode+"]";
	}
 	
}
