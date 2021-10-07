package io.jetform.core.annotation.model;

import java.util.ArrayList;
import java.util.List;

import io.jetform.core.enums.FieldType;

public class FormElementWrapper implements FormFieldWrapper{

	protected String id;
	protected String name;
	protected String label;
	protected String value="";
	protected String placeHolder="";
	protected String errorMessage="";
	protected boolean required=false;
	protected boolean readOnly=false;
	protected boolean disabled=false;
	protected boolean listable=false;
	protected String fieldType=FieldType.TEXT.name();
	protected List<Validation> validtions = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPlaceHolder() {
		return placeHolder;
	}
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isListable() {
		return listable;
	}
	public void setListable(boolean listable) {
		this.listable = listable;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public List<Validation> getValidtions() {
		return validtions;
	}
	public void setValidtions(List<Validation> validtions) {
		this.validtions = validtions;
	}
	
	
}
