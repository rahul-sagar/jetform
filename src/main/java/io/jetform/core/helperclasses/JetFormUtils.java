package io.jetform.core.helperclasses;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.model.Validation;

public interface JetFormUtils {
	
    public static List<Validation> getValidations(FormElement formElement){
		
		return Arrays.stream(formElement.validations())
		      .map(validation -> new Validation(validation.type().name(), validation.value()))
		      .collect(Collectors.toList());
		
	}

	public static String createLabel(String label) {

		String[] words = label.split("(?=\\p{Upper})");
		StringBuilder stringBuilder = new StringBuilder();

		for (String str : words)
			stringBuilder.append(str.substring(0, 1).toUpperCase()).append(str.substring(1, str.length())).append(" ");

		return stringBuilder.toString().trim();
	}
}