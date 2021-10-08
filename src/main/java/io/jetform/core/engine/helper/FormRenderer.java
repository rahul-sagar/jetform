package io.jetform.core.engine.helper;

import java.util.List;

import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;

public interface FormRenderer {
	
	JetFormWrapper getForm(String formClass);
	JetFormWrapper getForm(Class<?> formClass);
	JetFormWrapper getForm(JetForm formAnnotation);
	List<FormElementWrapper> getFormElements(Class<?> formClass);
}
