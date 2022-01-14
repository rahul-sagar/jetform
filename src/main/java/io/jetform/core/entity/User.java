package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.Checkbox;
import io.jetform.core.annotation.CustomField;
import io.jetform.core.annotation.DataProvider;
import io.jetform.core.annotation.DependentField;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.LoadType;
import io.jetform.core.enums.ResourceType;
import io.jetform.core.enums.Type;
import io.jetform.core.enums.ValidationType;

@Entity
@Table(name = "tbl_User")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private long id;

	@FormElement(listable = true, dependentFields = {
			@DependentField(child = "education", datapath = "data", type = "load") }, select = @Select(options = {
					"UP:UP", "MP:MP", "UK:UK" }), validations = {
							@Validation(type = ValidationType.REQUIRED, value = "true"),
							@Validation(type = ValidationType.MINLENGTH, value = "2") })
	private String state;
	// options = { "Ghaziabad:Ghaziabad", "Bhopal:Bhopal", "Hariduar:Hariduar" }
	@FormElement(listable = true, select = @Select(dataProvider = @DataProvider(resource = ResourceType.REST, path = "data", loadType = LoadType.LAZY)), validations = {
			@Validation(type = ValidationType.REQUIRED, value = "true"),
			@Validation(type = ValidationType.MINLENGTH, value = "2") })
	private String city;

	@FormElement(listable = true, validations = { @Validation(type = ValidationType.REQUIRED, value = "true"),
			@Validation(type = ValidationType.MINLENGTH, value = "2") })
	private String street;
	// options = { "Edu:Educated", "UnEdu:UnEducated"})
	@FormElement(listable = true, checkbox = @Checkbox(dataProvider = @DataProvider(resource = ResourceType.REST, path = "data", loadType = LoadType.LAZY)), dependentFields = {
			@DependentField(child = "qualification", datapath = "Edu", type = "hidden"),
			@DependentField(child = "street", datapath = "Edu", type = "hidden") })
	private String education;

	@FormElement(listable = true, radio = @Radio(options = { "UP:UP", "MP:MP", "UK:UK" }),dependentFields = {@DependentField(child = "qualification",datapath = "data",type = "load")})
	private String gender;

	@FormElement(listable = true, checkbox = @Checkbox(dataProvider = @DataProvider(resource = ResourceType.REST, path = "data", loadType = LoadType.LAZY)))
	private String qualification;
	
	@FormElement(listable = true, customField = @CustomField(filePath = "select")) // WEB-INF/jsp/select.html  // /file/select.html													
	private String section;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

}