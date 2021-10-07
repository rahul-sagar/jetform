package io.jetform.core.annotation.processor;

import io.jetform.core.annotation.Checkbox;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.model.CheckBoxWrapper;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.RadioWrapper;

public interface FormElementProcessor {
	FormElementWrapper process(FormElement annotation);
	CheckBoxWrapper process(Checkbox annotation);
	RadioWrapper process(Radio annotation);
	
	Object processFieldElement(Object annotation);
}
