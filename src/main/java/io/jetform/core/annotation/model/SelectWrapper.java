package io.jetform.core.annotation.model;

import java.util.Arrays;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.enums.FieldType;


public class SelectWrapper extends FormElementWrapper {
	//private String callBackUrl;
	//private SelectionType selectionType;
	private DataProvider dataProvider;
	private boolean multiSelect;
	private String[] options;
	
	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public SelectWrapper() {
		// TODO Auto-generated constructor stub
	}

	public SelectWrapper(FormElement formField) {
		this.setId(formField.id());
		this.setLabel(formField.label());
		this.setPlaceHolder(formField.placeHolder());
		this.setName(formField.name());
		this.setReadOnly(formField.readOnly());
		this.setDisabled(formField.disabled());
		this.setRequired(formField.required());
		this.setErrorMessage(formField.errorMessage());
		this.setValue(formField.value());
		this.setFieldType(FieldType.SELECT.name());
		this.setListable(formField.listable());
		this.setMultiSelect(formField.select().multiSelect());
		//this.setOptions(formField.select().options());
		//this.setDataProvider(new DataProvider(formField.select()));
		//(formField.select().options().length > 0) ? setOptions(formField.select().options()) : setDataProvider(new DataProvider(formField.select()));
		//setCallBackUrl(formField.type().select().callBackUrl());
		//setSelectionType(formField.select().selectionType());
		/*
		 * if((formField.select().options().length > 0)) {
		 * System.out.println("Inside op");
		 * System.out.println(Arrays.toString(formField.select().options()));
		 * this.setOptions(formField.radio().options()); System.out.println(this); }else
		 * { System.out.println("Inside data"); this.setDataProvider(new
		 * DataProvider(formField.checkbox())); }
		 */
		check(formField);
	}
	
	/*
	 * public void check(FormField formField) {
	 * if(!(formField.select().options()[0].isEmpty())) {
	 * setOptions(formField.select().options()); }
	 * 
	 * }
	 */
	public void check(FormElement formField) {
		if((formField.select().options().length > 0)) {
			this.setOptions(formField.select().options());
		}else {
			this.setDataProvider(new DataProvider(formField.select()));
		}
	}

	/*
	 * public String getCallBackUrl() { return callBackUrl; }
	 * 
	 * public void setCallBackUrl(String callBackUrl) { this.callBackUrl =
	 * callBackUrl; }
	 * 
	 * public SelectionType getSelectionType() { return selectionType; }
	 * 
	 * public void setSelectionType(SelectionType selectionType) {
	 * this.selectionType = selectionType; }
	 */
	
	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	@Override
	public String toString() {
		return "SelectWrapper [dataProvider=" + dataProvider + ", multiSelect=" + multiSelect + ", options="
				+ Arrays.toString(options) + ", id=" + id + ", name=" + name + ", label=" + label + ", value=" + value
				+ ", placeHolder=" + placeHolder + ", errorMessage=" + errorMessage + ", required=" + required
				+ ", readOnly=" + readOnly + ", disabled=" + disabled + ", listable=" + listable + ", fieldType="
				+ fieldType + ", validtions=" + validtions + "]";
	}

	

	
}