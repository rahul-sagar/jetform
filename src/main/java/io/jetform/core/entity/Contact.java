package io.jetform.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormEntity;
import io.jetform.core.enums.Action;


@Entity
@Table(name = "pis_contact")
@FormEntity(actions = {
		@FormAction(url = "/create", action = Action.CREATE, buttonOrLinkValue = "Create"),
		@FormAction(url="/update",action = Action.UPDATE, buttonOrLinkValue = "Update"),
		@FormAction(url = "/delete",action = Action.DELETE, buttonOrLinkValue = "Delete"),
		@FormAction(url = "/list",action = Action.READ, buttonOrLinkValue = "Read")})
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contactId")		
	private long id;
	private String altEmailAddress;
	private String  emergencyMobile;
	private String facebookId; 
	private String instagramId;
	private String linkedInId;
	private String mobile;
	private String phone; 
	private String twitterId;
	private String landline;
	private String emergencyNo;
	private String fax;
	private String telegram;
	private String telex; 
	private String personalEmail ;
	private boolean deleted;
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
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getInstagramId() {
		return instagramId;
	}
	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getEmergencyNo() {
		return emergencyNo;
	}
	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelegram() {
		return telegram;
	}
	public void setTelegram(String telegram) {
		this.telegram = telegram;
	}
	public String getTelex() {
		return telex;
	}
	public void setTelex(String telex) {
		this.telex = telex;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
}
