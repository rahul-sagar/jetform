package io.jetform.core.helperclasses;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.model.Validation;


public class FormBuilderUtils {

	public static List<Validation> getValidations(FormElement formField){
		
		List<Validation> collect = Arrays.stream(formField.validations())
		      .map(validation -> new Validation(validation.type().name(), validation.value()))
		      .collect(Collectors.toList());
		return collect;
	}
}
/*
 * List<com.adjecti.jetform.annotation.model.Validation> validations = new
 * ArrayList<>(); for(Validation validation:formField.validations())
 * validations.add(new
 * com.adjecti.jetform.annotation.model.Validation(validation.type().name(),
 * validation.value()));
 */