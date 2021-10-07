package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormEntity;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.Validation;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.ValidationType;


@Entity
@Table(name = "tbl_employee")
@FormEntity(actions = {
		@FormAction(url = "/create", action = Action.CREATE, buttonOrLinkValue = "Create"),
		@FormAction(url="/update",action = Action.UPDATE, buttonOrLinkValue = "Update"),
		@FormAction(url = "/delete",action = Action.DELETE, buttonOrLinkValue = "Delete"),
		@FormAction(url = "/list",action = Action.READ, buttonOrLinkValue = "Read")})
public class Employee {

	@FormElement(select = @Select(multiSelect = true ,options = { "F:Finance",
	"M:Marketing" }))
String department;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement()
	Long id;

	@FormElement(listable = true, validations = { @Validation(type = ValidationType.REQUIRED, value = "true") })
	String employeeName;

	// @FormField(listable = true,fieldType = FieldType.RADIO, radio =
	// @Radio(dataProvider = @DataProvider(loadType = LoadType.LAZY, path =
	// "/gender/list", resource = ResourceType.WEB,key = "id",value = "name")))
	@FormElement(listable = true, radio = @Radio(options = { "M:Male", "F:Female", "T:TransGender" }))
	String gender;

	
	/*
	 * @FormField(listable = true ,fieldType = FieldType.NUMBER, number
	 * = @Number(format = "######"),validations = {
	 * 
	 * @Validation(type = ValidationType.MIN, value = "000000"),
	 * 
	 * @Validation(type = ValidationType.MAX, value = "999999") })
	 */
	@FormElement(listable = true,number = @Number(format="#####"))
	int pinCode;
	//@FormField()
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
	//@FormField(form = @Form(childKey = "contactId", parentKey = "empId"))
//	private Contact contact;
	int age;
	
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