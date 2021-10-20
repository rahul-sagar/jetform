package io.jetform.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.Email;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormEntity;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.ValidationType;


@Entity
@Table(name = "pis_contact")
@JetForm(actions= {
		@FormAction(name = "/create", action = Action.CREATE, label = "Create"),
		@FormAction(name="/update",action = Action.UPDATE, label = "Update"),
		@FormAction(name = "/delete",action = Action.DELETE, label = "Delete"),
		@FormAction(name = "/list",action = Action.READ, label = "Read")})
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contactId")
	@FormElement
	private long id;
	
	@FormElement(email=@Email(pattern="######"),listable=true,validations= @Validation(type=ValidationType.REQUIRED,value="true"))
	private String altEmailAddress;
	
	@FormElement(number=@Number(format="#####"),validations=@Validation(type=ValidationType.REQUIRED,value="true"),listable=true)
	private String  emergencyMobile;
	
		
	@FormElement
	private String linkedInId;
	
	@FormElement(number=@Number(format="######"),listable=true)
	private String mobile;

	
	@FormElement
	private String personalEmail ;
	
	@FormElement
	private boolean deleted;
	
	@FormElement
	private int status;

	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAltEmailAddress() {
		return altEmailAddress;
	}
	public void setAltEmailAddress(String altEmailAddress) {
		this.altEmailAddress = altEmailAddress;
	}
	public String getEmergencyMobile() {
		return emergencyMobile;
	}
	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}


	public String getLinkedInId() {
		return linkedInId;
	}
	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
}
