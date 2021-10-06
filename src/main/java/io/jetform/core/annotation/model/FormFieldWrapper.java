package io.jetform.core.annotation.model;

public interface FormFieldWrapper {
	public String getId() ;
	public String getName() ;
	public String getLabel();
	public String getPlaceHolder();
	public String getErrorMessage() ;
	public boolean isRequired();
	public boolean isReadOnly();
	public boolean isDisabled();
	public boolean isListable();
	public String getFieldType();
	public String getValue();
}
