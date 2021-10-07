package io.jetform.core.engine.helper;

import java.util.List;

import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;

public interface FormRenderer {
	
	JetFormWrapper getFormByClass(String formClass);
	JetFormWrapper getFormByClass(Class<?> formClass);
	JetFormWrapper readForm(JetForm formAnnotation);
	List<FormElementWrapper> readFormElements(Class<?> formClass);
}
