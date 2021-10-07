package io.jetform.core.annotation.processor.impl;

import io.jetform.core.annotation.Checkbox;
import io.jetform.core.annotation.Email;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Radio;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.Text;
import io.jetform.core.annotation.model.CheckBoxWrapper;
import io.jetform.core.annotation.model.EmailWrapper;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.NumberWrapper;
import io.jetform.core.annotation.model.RadioWrapper;
import io.jetform.core.annotation.model.SelectWrapper;
import io.jetform.core.annotation.model.TextWrapper;
import io.jetform.core.annotation.processor.FormElementProcessor;

public class FormElementProcessorImpl implements FormElementProcessor {

	@Override
	public FormElementWrapper process(FormElement annotation) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectWrapper process(Select select) {
		// TODO Auto-generated method stub
		return null;
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

	

}
