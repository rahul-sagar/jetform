package io.jetform.core.annotation.model;

import java.lang.reflect.Field;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.enums.FieldType;


public class FormWrapper extends FormElementWrapper{
	private String formClass;
	private String mappingClass;
	private String parentKey;
	private String childKey;
	
	public FormWrapper() {
	}
	
	public FormWrapper(Field field, Class clazz) {
		FormElement formField = field.getAnnotation(FormElement.class);
		setId(formField.id());
		setLabel(formField.label());
		setPlaceHolder(formField.placeHolder());
		setName(formField.name());
		setReadOnly(formField.readOnly());
		setDisabled(formField.disabled());
		setRequired(formField.required());
		setErrorMessage(formField.errorMessage());
		setValue(formField.value());
		setFieldType(FieldType.FORM.name());
		setListable(formField.listable());
		setParentKey(formField.form().parentKey());
		setChildKey(formField.form().childKey());
		setFormFields(field, clazz);
	}

	void setFormFields(Field field, Class<?> clazz) {
		Class<?> type = field.getType();
		String packageName = type.getPackage().getName();
		System.out.println(packageName+"."+type.getSimpleName());
		setFormClass(packageName);
		setMappingClass(packageName+"."+type.getSimpleName());
	}
	public String getFormClass() {
		return formClass;
	}
	public void setFormClass(String formClass) {
		this.formClass = formClass;
	}
	public String getMappingClass() {
		return mappingClass;
	}
	public void setMappingClass(String mappingClass) {
		this.mappingClass = mappingClass;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getChildKey() {
		return childKey;
	}
	public void setChildKey(String childKey) {
		this.childKey = childKey;
	}
	
}
