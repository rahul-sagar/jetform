package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.Date;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Type;
import io.jetform.core.enums.ValidationType;

@Entity
@Table(name = "tbl_employee")
@JetForm(listIndex=false,filter = "gender:F",actions = { @FormAction(name = "create", action = Action.CREATE,type=Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE,type=Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE,type=Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ,type=Type.BUTTON, label = "Read") })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	Long id;
//@Validation(type = ValidationType.REQUIRED, value = "true"),
	@FormElement(listable = true, autocomplete=true,validations = {@Validation(type = ValidationType.REQUIRED, value = "true"),@Validation(type = ValidationType.MINLENGTH, value = "2")})
	String employeeName;
	

	// @FormField(listable = true,fieldType = FieldType.RADIO, radio =
	// @Radio(dataProvider = @DataProvider(loadType = LoadType.LAZY, path =
	// "/gender/list", resource = ResourceType.WEB,key = "id",value = "name")))
	@FormElement(listable = true, radio = @Radio(options = { "M:Male", "F:Female", "T:TransGender" }),
			validations = @Validation(type = ValidationType.REQUIRED,value = "true"))
	String gender;

	@FormElement(select = @Select(multiSelect = false, options = { "F:Finance", "M:Marketing" }),listable=true,
			validations = @Validation(type = ValidationType.REQUIRED,value = "true"))
	String department;
	/*
	 * @FormField(listable = true ,fieldType = FieldType.NUMBER, number
	 * = @Number(format = "######"),validations = {
	 * 
	 * @Validation(type = ValidationType.MIN, value = "000000"),
	 * 
	 * @Validation(type = ValidationType.MAX, value = "999999") })
	 */
	@FormElement(listable = true, number = @Number(format = "#####"),
			validations = {@Validation(type = ValidationType.REQUIRED,value = "true"),
					       @Validation(type = ValidationType.MINLENGTH,value = "6")})
	int pinCode;
	// @FormField()
	@FormElement(listable=true,number=@Number(format="#####"),
			validations = {@Validation(type = ValidationType.REQUIRED,value = "true"),
					       @Validation(type = ValidationType.MIN,value = "10000"),
					       @Validation(type = ValidationType.MAX,value = "100000")})
	int salary;
	/*
	 * @FormField(fieldType = FieldType.CHECKBOX,checkbox = @Checkbox(selectionType
	 * =SelectionType.Single)) boolean married;
	 */
	/*
	 * @FormField(listable = true,fieldType = FieldType.NUMBER, number
	 * = @Number(format = "dd"),validations = {
	 * 
	 * @Validation(type = ValidationType.MIN, value = "18"),
	 * 
	 * @Validation(type = ValidationType.MAX, value = "99") })
	 */

	// @JoinColumn(name = "contactId")
	// @FormField(form = @Form(childKey = "contactId", parentKey = "empId"))
//	private Contact contact;
	@FormElement(number=@Number(format = "####"),
			validations = {@Validation(type = ValidationType.REQUIRED,value = "true"),
				           @Validation(type = ValidationType.MIN,value = "18"),
				           @Validation(type = ValidationType.MAX,value = "90")})
	int age;

	@FormElement(date = @Date(format = "dd/mm/yy"))
	private String dateOfBirth;
	
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [department=" + department + ", id=" + id + ", employeeName=" + employeeName + ", gender="
				+ gender + ", pinCode=" + pinCode + ", salary=" + salary + ", age=" + age + "]";
	}


}