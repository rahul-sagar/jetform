package io.jetform.core.annotation.model;

import java.util.Arrays;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.enums.FieldType;


public class CheckBoxWrapper extends FormFieldBase {

	private DataProvider dataProvider;
	private boolean multiSelect;
	private String[] options;

	public CheckBoxWrapper() {

	}

	public CheckBoxWrapper(FormElement formField) {
		setId(formField.id());
		setLabel(formField.label());
		setPlaceHolder(formField.placeHolder());
		setName(formField.name());
		setReadOnly(formField.readOnly());
		setDisabled(formField.disabled());
		setRequired(formField.required());
		setErrorMessage(formField.errorMessage());
		setValue(formField.value());
		setFieldType(FieldType.CHECKBOX.name());
		setListable(formField.listable());
		check(formField);
	}
	
	public void check(FormElement formField) {
		if(!(formField.radio().options().length == 0)) {
			setOptions(formField.radio().options());
		}else {
			setDataProvider(new DataProvider(formField.checkbox()));
		}
	}
	
	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}
	
	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	@Override
	public String toString() {
		return "CheckBoxWrapper [dataProvider=" + dataProvider + ", multiSelect=" + multiSelect + ", options="
				+ Arrays.toString(options) + "]";
	}
	/*
	 * public SelectionType getSelectionType() { return selectionType; }
	 * 
	 * public void setSelectionType(SelectionType selectionType) {
	 * this.selectionType = selectionType; }
	 */
	
}
