package io.jetform.core.engine.helper.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.model.FormAction;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.annotation.processor.FormElementProcessor;
import io.jetform.core.annotation.processor.impl.FormElementProcessorImpl;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.helperclasses.JetFormUtils;

@Component
public class FormRendererImpl implements FormRenderer{

	@Override
	public JetFormWrapper getFormByClass(String formClass) {
		JetFormWrapper jetFormWrapper = null;
		try {
			Class<?> forName = Class.forName(formClass);
			 jetFormWrapper = getFormByClass(forName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jetFormWrapper;
	}

	@Override
	public JetFormWrapper getFormByClass(Class<?> clazz) {
		JetFormWrapper jetFormWrapper = null;
		JetForm jetForm = null;
		if(clazz.isAnnotationPresent(JetForm.class)) {
			jetForm = clazz.getAnnotation(JetForm.class);
			jetFormWrapper = readForm(jetForm);
		}
	     populate(jetFormWrapper, clazz.getSimpleName(), jetForm);
		List<FormElementWrapper> readFormElements = readFormElements(clazz);
		jetFormWrapper.setFormElementWrappers(readFormElements);
		return jetFormWrapper;
	}

	@Override
	public JetFormWrapper readForm(JetForm jetFormAnnotation) {
		JetFormWrapper formWrapper = new JetFormWrapper(jetFormAnnotation);
		    
		List<FormAction> collect = Arrays.stream(jetFormAnnotation.actions())
				                         .map(e -> new FormAction(e))
				                         .collect(Collectors.toList());
		formWrapper.setFormAction(collect);
		
		return formWrapper;
	}

	@Override
	public List<FormElementWrapper> readFormElements(Class<?> clazz) {
		FormElementProcessor formElementProcessor= new FormElementProcessorImpl();

		return Arrays.stream(clazz.getDeclaredFields())
		      .filter(e -> e.isAnnotationPresent(FormElement.class))
		      .map(formElementProcessor::process)
		      .collect(Collectors.toList()); 
	}
	


	private void populate(JetFormWrapper jetFormWrapper, String className, JetForm jetForm) {

		if (jetForm.id().equals("")) {
			jetFormWrapper.setId(className);
		}
		if (jetForm.name().equals("")) {
			jetFormWrapper.setName(className);
		}
		if (jetForm.title().equals("")) {
			jetFormWrapper.setTitle(JetFormUtils.createLabel(className));
		}
         //return jetFormWrapper;
	}

}


/*
 * private static FormElementWrapper populate(FormElementWrapper formFieldBase,
 * Field field, FormElement formField) { if (formField.id().equals("")) {
 * formFieldBase.setId(field.getName().toLowerCase()); } if
 * (formField.placeHolder().equals("Enter Some Text  ") && (formFieldBase
 * instanceof CheckBoxWrapper || formFieldBase instanceof SelectWrapper ||
 * formFieldBase instanceof RadioWrapper)) {
 * formFieldBase.setPlaceHolder("Choose the " +
 * JetFormUtils.createLabel(field.getName()) + "."); } else if
 * (formField.placeHolder().equals("Enter Some Text  ") && (formFieldBase
 * instanceof FormWrapper)) {
 * formFieldBase.setPlaceHolder(JetFormUtils.createLabel(field.getName())); }
 * else { formFieldBase.setPlaceHolder("Enter the " +
 * JetFormUtils.createLabel(field.getName() + ".")); } if
 * (formField.label().equals("")) {
 * formFieldBase.setLabel(JetFormUtils.createLabel(field.getName())); } if
 * (formField.name().equals("")) { formFieldBase.setName(field.getName()); }
 * return formFieldBase; }
 */
/*
 * private static FormElementWrapper scan1(Field field, Class<?> clazz) {
 * FormElement formField = field.getAnnotation(FormElement.class); if
 * (!(formField.form().childKey().isEmpty() ||
 * formField.form().parentKey().isEmpty())) { return populate(new
 * FormWrapper(field, clazz), field, formField); } else if
 * (!formField.number().format().isEmpty()) { return populate(new
 * NumberWrapper(formField), field, formField); } else if
 * (!formField.email().pattern().isEmpty()) { return populate(new
 * EmailWrapper(formField), field, formField); } else if
 * ((!formField.radio().dataProvider().path().isEmpty()) ^
 * formField.radio().options().length > 0) { return populate(new
 * RadioWrapper(formField), field, formField); } else if
 * ((!formField.checkbox().dataProvider().path().isEmpty()) ^
 * formField.checkbox().options().length > 0) { return populate(new
 * CheckBoxWrapper(formField), field, formField); } else if
 * ((!formField.select().dataProvider().path().isEmpty()) ^
 * formField.select().options().length > 0) { return populate(new
 * SelectWrapper(formField), field, formField); } else { return populate(new
 * TextWrapper(formField), field, formField); } }
 * 
 * private static FormElementWrapper scan(Field field, Class<?> clazz) {
 * FormElement formField = field.getAnnotation(FormElement.class); if
 * (!(formField.form().childKey().isEmpty() ||
 * formField.form().parentKey().isEmpty())) { return populate(new
 * FormWrapper(field, clazz), field, formField); } else if
 * (!formField.number().format().isEmpty()) { return populate(new
 * NumberWrapper(formField), field, formField); } else if
 * (!formField.email().pattern().isEmpty()) { return populate(new
 * EmailWrapper(formField), field, formField); } else if
 * ((!formField.radio().dataProvider().path().isEmpty()) ^
 * formField.radio().options().length > 0) { return populate(new
 * RadioWrapper(formField), field, formField); } else if
 * ((!formField.checkbox().dataProvider().path().isEmpty()) ^
 * formField.checkbox().options().length > 0) { return populate(new
 * CheckBoxWrapper(formField), field, formField); } else if
 * ((!formField.select().dataProvider().path().isEmpty()) ^
 * formField.select().options().length > 0) { return populate(new
 * SelectWrapper(formField), field, formField); } else { return populate(new
 * TextWrapper(formField), field, formField); } }
 */