package io.jetform.core.annotation.processor.impl;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jetform.core.annotation.Checkbox;
import io.jetform.core.annotation.CustomField;
import io.jetform.core.annotation.Date;
import io.jetform.core.annotation.Email;
import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.Text;
import io.jetform.core.annotation.TextArea;
import io.jetform.core.annotation.Upload;
import io.jetform.core.annotation.model.CheckBoxWrapper;
import io.jetform.core.annotation.model.CustomFieldWrapper;
import io.jetform.core.annotation.model.DateWrapper;
import io.jetform.core.annotation.model.EmailWrapper;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.FormWrapper;
import io.jetform.core.annotation.model.HiddenWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.annotation.model.NumberWrapper;
import io.jetform.core.annotation.model.RadioWrapper;
import io.jetform.core.annotation.model.SelectWrapper;
import io.jetform.core.annotation.model.TextAreaWrapper;
import io.jetform.core.annotation.model.TextWrapper;
import io.jetform.core.annotation.model.UploadWrapper;
import io.jetform.core.annotation.processor.FormElementProcessor;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.helperclasses.JetFormUtils;

@Component
public class FormElementProcessorImpl implements FormElementProcessor {

	@Autowired
	private FormRenderer formRenderer;
	
	@Override
	public FormElementWrapper process(Field field) {
		FormElement annotation = field.getAnnotation(FormElement.class);
		Object processFieldElement = processFieldElement(annotation);
		FormElementWrapper formElementWrapper = populate((FormElementWrapper) processFieldElement, annotation, field);
		return formElementWrapper;
	}

	@Override
	public FormElementWrapper process(FormElement annotation) {
		Object processFieldElement = processFieldElement(annotation);
		return (FormElementWrapper) processFieldElement;
	}


	private SelectWrapper process(Select select) {
		return new SelectWrapper(select);
	}


	private NumberWrapper process(Number number) {
		return new NumberWrapper(number);
	}
	
	private TextAreaWrapper process(TextArea textArea) {
		return new TextAreaWrapper(textArea);
	}


	private EmailWrapper process(Email email) {
		return new EmailWrapper(email);
	}


	private TextWrapper process(Text text) {
		return new TextWrapper(text);
	}

	private HiddenWrapper process(Hidden hidden) {
		return new HiddenWrapper(hidden);
	}

	private FormWrapper process(Form form) {
		JetFormWrapper jetFormWrapper = formRenderer.getForm(form.formClass());
		System.out.println("printing the jetFormWrapper :: FormWrapper process(Form form)");
		System.out.println(jetFormWrapper);
		return new FormWrapper(form, jetFormWrapper);
		//return new FormWrapper(form);
	}

	private CheckBoxWrapper process(Checkbox checkbox) {
		return new CheckBoxWrapper(checkbox);
	}

	private RadioWrapper process(Radio radio) {
		return new RadioWrapper(radio);
	}
	
	private DateWrapper process(Date date) {
		return new DateWrapper(date);
	}
	
	private CustomFieldWrapper process(CustomField customField) {
		return new CustomFieldWrapper(customField);
	}
	
	private UploadWrapper process(Upload upload) {		
		return new UploadWrapper(upload);
	}

	@Override
	public Object processFieldElement(Object annotation) {

		FormElement formElement = null;

		if (annotation instanceof FormElement) 
			formElement = (FormElement) annotation;
		
		//if (!(formElement.form().childKey().isEmpty() || formElement.form().parentKey().isEmpty())) 
		//	return process(formElement.form());
		
		if (!(formElement.form().formClass().isEmpty())) 
			return process(formElement.form());
		
		else if (!formElement.number().format().isEmpty()) 
			return process(formElement.number());
		
		else if (!(formElement.textarea().rows()== 5 && formElement.textarea().cols()==50)) 
			return process(formElement.textarea());

		else if (!formElement.date().format().isEmpty()) 
			return process(formElement.date());
		
		else if (!formElement.customField().filePath().isEmpty()) 
			return process(formElement.customField());
		
		else if (!formElement.hidden().value().isEmpty()) 
			return process(formElement.hidden());

		else if (!formElement.email().pattern().isEmpty()) 
			return process(formElement.email());
		
		else if ((!formElement.radio().dataProvider().path().isEmpty()) ^ formElement.radio().options().length > 0) 
			return process(formElement.radio());
		
		else if ((!formElement.checkbox().dataProvider().path().isEmpty()) ^ formElement.checkbox().options().length > 0) 
			return process(formElement.checkbox());
		
		else if ((!formElement.select().dataProvider().path().isEmpty())
				^ formElement.select().options().length > 0)
			return process(formElement.select());
		 
		else if (!(formElement.upload().dataProvider().path().isEmpty())) 
			return process(formElement.upload());
		
		else 
			return process(formElement.text());
		
	}

	private FormElementWrapper populate(FormElementWrapper formElementWrapper, FormElement formElement, Field field) {
		if (formElement.id().equals("")) {
			formElementWrapper.setId(field.getName().toLowerCase());
		}
		if (formElement.name().equals("")) {
			formElementWrapper.setName(field.getName());
			System.out.println("Printing the field name : "+field.getName());
		}
		if (formElement.label().equals("")) {
			formElementWrapper.setLabel(JetFormUtils.createLabel(field.getName()));
		}
		if (formElement.placeHolder().equals("Enter Some Text  ") && (formElementWrapper instanceof CheckBoxWrapper
				|| formElementWrapper instanceof SelectWrapper || formElementWrapper instanceof RadioWrapper)) {
			formElementWrapper.setPlaceHolder("Choose the " + JetFormUtils.createLabel(field.getName()) + ".");
		} else if (formElement.placeHolder().equals("Enter Some Text  ")
				&& (formElementWrapper instanceof FormWrapper)) {
			formElementWrapper.setPlaceHolder(JetFormUtils.createLabel(field.getName()));
		} else {
			formElementWrapper.setPlaceHolder("Enter the " + JetFormUtils.createLabel(field.getName() + "."));
		}
		
		
		formElementWrapper.setDisabled(formElement.disabled());
		formElementWrapper.setListable(formElement.listable());
		formElementWrapper.setReadOnly(formElement.readOnly());
		formElementWrapper.setValidations(JetFormUtils.getValidations(formElement));
		formElementWrapper.setAutoComplete(formElement.autocomplete());
		formElementWrapper.setValue(formElement.value());
		formElementWrapper.setDependField(formElement.dependField());
		formElementWrapper.setDependentFields(JetFormUtils.getDependentFields(formElement));
		
		return formElementWrapper;
	}
}