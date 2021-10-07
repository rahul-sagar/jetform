package io.jetform.core.annotation.model;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.enums.FieldType;

public class RadioWrapper extends FormElementWrapper {
	private DataProvider dataProvider;
	private String[] options;
	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public RadioWrapper() {
	}

	public RadioWrapper(FormElement formField) {
		setId(formField.id());
		setLabel(formField.label());
		setPlaceHolder(formField.placeHolder());
		setName(formField.name());
		setReadOnly(formField.readOnly());
		setDisabled(formField.disabled());
		setRequired(formField.required());
		setErrorMessage(formField.errorMessage());
		setValue(formField.value());
		setFieldType(FieldType.RADIO.name());
		setListable(formField.listable());
		check(formField);
		//setDataProvider(new DataProvider(formField));
	}
	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public void check(FormElement formField) {
		if((formField.radio().options().length > 0)){
			setOptions(formField.radio().options());
		}else {
			formField.radio().dataProvider();
			setDataProvider(new DataProvider(formField));
		}
		
	}
	/*
	 * @Override public String toString() { return "RadioWrapper [dataProvider=" +
	 * dataProvider + ", getId()=" + getId() + ", getName()=" + getName() +
	 * ", getLabel()=" + getLabel() + ", getValue()=" + getValue() +
	 * ", getPlaceHolder()=" + getPlaceHolder() + ", getErrorMessage()=" +
	 * getErrorMessage() + ", isRequired()=" + isRequired() + ", isReadOnly()=" +
	 * isReadOnly() + ", isDisabled()=" + isDisabled() + ", isListable()=" +
	 * isListable() + ", getFieldType()=" + getFieldType() + ", toString()=" +
	 * super.toString() + ", getClass()=" + getClass() + ", hashCode()=" +
	 * hashCode() + "]"; }
	 */
	
	
}
