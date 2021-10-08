package io.jetform.core.annotation.processor;

import java.lang.reflect.Field;

import io.jetform.core.annotation.Checkbox;
import io.jetform.core.annotation.Email;
import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.Text;
import io.jetform.core.annotation.model.CheckBoxWrapper;
import io.jetform.core.annotation.model.EmailWrapper;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.FormWrapper;
import io.jetform.core.annotation.model.NumberWrapper;
import io.jetform.core.annotation.model.RadioWrapper;
import io.jetform.core.annotation.model.SelectWrapper;
import io.jetform.core.annotation.model.TextWrapper;

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
