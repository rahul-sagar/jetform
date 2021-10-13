package io.jetform.core.annotation.processor;

import java.lang.reflect.Field;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.model.FormElementWrapper;

public interface FormElementProcessor {
	FormElementWrapper process(FormElement annotation);
	FormElementWrapper process(Field field);
//	CheckBoxWrapper process(Checkbox annotation);
//	RadioWrapper process(Radio annotation);
//	SelectWrapper process(Select select);
//	NumberWrapper process(Number number);
//	EmailWrapper process(Email email);
//	TextWrapper process(Text text);
//	FormWrapper process(Form form);
	
	Object processFieldElement(Object annotation);
}
