package io.jetform.core.annotation.processor.impl;

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
import io.jetform.core.annotation.processor.FormElementProcessor;

public class FormElementProcessorImpl implements FormElementProcessor {
	
	@Override
	public FormElementWrapper process(FormElement annotation) {
		FormElementWrapper formElementWrapper = new FormElementWrapper();
		
		Object processFieldElement = processFieldElement(annotation);
		return (FormElementWrapper) processFieldElement;
	}

	@Override
	public SelectWrapper process(Select select) {
		
		return new SelectWrapper(select);
	}

	@Override
	public NumberWrapper process(Number number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailWrapper process(Email email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextWrapper process(Text text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormWrapper process(Form form) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public CheckBoxWrapper process(Checkbox annotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RadioWrapper process(Radio annotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processFieldElement(Object annotation) {
		FormElement formElement = null;
		if (annotation instanceof FormElement) {
			formElement = (FormElement) annotation;
		}
		if (!(formElement.form().childKey().isEmpty() || formElement.form().parentKey().isEmpty())) {
			return process(formElement.form());
			//return populate(new FormWrapper(field, clazz), field, formField);
		} else if (!formElement.number().format().isEmpty()) {
			return process(formElement.number());
			//return populate(new NumberWrapper(formField), field, formField);
		} else if (!formElement.email().pattern().isEmpty()) {	
			return process(formElement.email());
			//return populate(new EmailWrapper(formField), field, formField);
		} else if ((!formElement.radio().dataProvider().path().isEmpty()) ^ formElement.radio().options().length > 0) {
			return process(formElement.radio());
			///return populate(new RadioWrapper(formField), field, formField);
		} else if ((!formElement.checkbox().dataProvider().path().isEmpty()) ^ formElement.checkbox().options().length > 0) {
			return process(formElement.checkbox());
			//return populate(new CheckBoxWrapper(formField), field, formField);
		} else if ((!formElement.select().dataProvider().path().isEmpty()) ^ formElement.select().options().length > 0) {
			return process(formElement.select());
			//return populate(new SelectWrapper(formElement), field, formField);
		} else {
			return process(formElement.text());
			//return populate(new TextWrapper(formField), field, formField);
		}
	}

}
