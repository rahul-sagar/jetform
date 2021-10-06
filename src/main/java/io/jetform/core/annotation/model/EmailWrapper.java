package io.jetform.core.annotation.model;

import io.jetform.core.annotation.FormField;
import io.jetform.core.enums.FieldType;
import io.jetform.core.helperclasses.FormBuilderUtils;

public class EmailWrapper extends FormFieldBase {
	private String pattern;

	public EmailWrapper() {}
	
	public EmailWrapper(FormField formField) {

		setId(formField.id());
		setLabel(formField.label());
		setPlaceHolder(formField.placeHolder());
		setName(formField.name());
		setReadOnly(formField.readOnly());
		setDisabled(formField.disabled());
		setRequired(formField.required());
		setErrorMessage(formField.errorMessage());
		setValue(formField.value());
		setFieldType(FieldType.EMAIL.name());
		setListable(formField.listable());
		setPattern(formField.email().pattern());
		setValidtions(FormBuilderUtils.getValidations(formField));
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return "EmailWrapper [pattern=" + pattern + ", id=" + id + ", name=" + name + ", label=" + label + ", value="
				+ value + ", placeHolder=" + placeHolder + ", errorMessage=" + errorMessage + ", required=" + required
				+ ", readOnly=" + readOnly + ", disabled=" + disabled + ", listable=" + listable + ", fieldType="
				+ fieldType + ", validtions=" + validtions + "]";
	}
		
}