package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementGroup;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Type;

@Entity
@Table(name = "tbl_Process_File")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") },
         groups = {@FormElementGroup(id = "fileNo",label = "File Number",elementsPerRow = 4),
        		   @FormElementGroup(id = "category",label = "Category",elementsPerRow = 2),
        		   @FormElementGroup(id = "reference",label = "Reference",elementsPerRow = 2),
        		   @FormElementGroup(id = "description",label = "Description",elementsPerRow = 1)})
public class ProcessFile {
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private long id;
	
	@FormElement(listable = true, group = "fileNo")
	private String departmentId;
	
	@FormElement(listable = true, group = "fileNo")
	private String sectionId;
	
	@FormElement(listable = true, group = "fileNo")
	private String year;
	
	@FormElement(listable = true, group = "fileNo")
	private String subjectId;
	
	@FormElement(listable = true, group = "category")
	private String subCategoryId;
	
	@FormElement(listable = true, group = "category")
	private String mainCategoryId;
	
	@FormElement(listable = true, group = "reference")
	private String previousReference;
	
	@FormElement(listable = true, group = "reference")
	private String laterReference;
	
	@FormElement(listable = true, group = "description")
	private String subjectDescription;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(String mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public String getPreviousReference() {
		return previousReference;
	}

	public void setPreviousReference(String previousReference) {
		this.previousReference = previousReference;
	}

	public String getLaterReference() {
		return laterReference;
	}

	public void setLaterReference(String laterReference) {
		this.laterReference = laterReference;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	@Override
	public String toString() {
		return "ProcessFile [id=" + id + ", departmentId=" + departmentId + ", sectionId=" + sectionId + ", year="
				+ year + ", subjectId=" + subjectId + ", subCategoryId=" + subCategoryId + ", mainCategoryId="
				+ mainCategoryId + ", previousReference=" + previousReference + ", laterReference=" + laterReference
				+ ", subjectDescription=" + subjectDescription + "]";
	}
	
	
}
