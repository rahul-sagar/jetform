package io.jetform.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.jetform.core.annotation.Date;
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
@Table(name = "tbl_employee_experience")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") })
public class EmployeeExperience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FormElement(hidden = @Hidden(value = "0"))
	private int id;
	
	@Column(name = "companyName")
	@FormElement
	private String companyName;

	@Column(name = "fromDate")
	@FormElement(date = @Date(format = "dd/mm/yy"))
	private String from;

	@Column(name = "toDate")
	@FormElement(date = @Date(format = "dd/mm/yy"))
	private String to;
	
	@ManyToOne
	@JoinColumn(name ="candidateId") 
	@FormElement(listable = true,form = @Form(formClass = "io.jetform.core.entity.Candidate",relation = Relation.MANY_TO_ONE))///madel false 
	@JsonBackReference
	private Candidate candidate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "EmployeeExperience [id=" + id + ", companyName=" + companyName + ", from=" + from + ", to=" + to
				+ ", candidate=" + candidate + "]";
	}

	
	
}
