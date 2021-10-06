package io.jetform.core.helperclasses;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.jetform.core.annotation.FormField;
import io.jetform.core.annotation.model.FormFieldBase;
import io.jetform.core.annotation.model.FormFieldWrapper;

public class ScanEntity {
	// static Gson gson = new Gson();
	// String json = gson.toJson(someInstanceOfStaff);
	/*
	 * public static void main(String[] args) { Gson gson = new Gson();
	 * List<FormFieldWrapper> readEntity =
	 * readEntity("form.builder.models.Employee"); String json =
	 * gson.toJson(readEntity); System.out.println(json);
	 * 
	 * readEntity.forEach(a -> { String json = gson.toJson(a); System.out.prin });
	 * 
	 * 
	 * }
	 */

	/*
	 * public static List<FormFieldWrapper> readEntity(String className) {
	 * List<FormFieldWrapper> fieldWrappers = new ArrayList<>(); Class<?> clazz; try
	 * { clazz = Class.forName(className); Field[] declaredFields =
	 * clazz.getDeclaredFields(); for (Field field : declaredFields) { if
	 * (field.isAnnotationPresent(FormField.class)) { FormField formField =
	 * field.getAnnotation(FormField.class);
	 * 
	 * if (formField.fieldType() == FieldType.TEXT) { populateFields(new
	 * TextWrapper(formField), fieldWrappers, formField, field); } else if
	 * (formField.fieldType() == FieldType.RADIO) { populateFields(new
	 * RadioWrapper(formField), fieldWrappers, formField, field); } else if
	 * (formField.fieldType() == FieldType.CHECKBOX) { populateFields(new
	 * CheckBoxWrapper(formField), fieldWrappers, formField, field); } else if
	 * (formField.fieldType() == FieldType.SELECT) { populateFields(new
	 * SelectWrapper(formField), fieldWrappers, formField, field); } else if
	 * (formField.fieldType() == FieldType.NUMBER) { populateFields(new
	 * NumberWrapper(formField), fieldWrappers, formField, field); } else if
	 * (formField.fieldType() == FieldType.EMAIL) { populateFields(new
	 * EmailWrapper(formField), fieldWrappers, formField, field); }
	 * 
	 * } } } catch (ClassNotFoundException e) { e.printStackTrace(); } return
	 * fieldWrappers;
	 * 
	 * }
	 */

	/*
	 * public static List<FormFieldBase> readEntityR(String className) { Class<?>
	 * clazz; List<FormFieldBase> collect = null; try { clazz =
	 * Class.forName(className); Field[] declaredFields = clazz.getDeclaredFields();
	 * collect = Arrays.stream(declaredFields) .filter(e ->
	 * e.isAnnotationPresent(FormField.class)) .map(ScanEntity::checkFieldType)
	 * .collect(Collectors.toList()); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); } return collect;
	 * 
	 * }
	 */
	
	/*
	 * public static List<String> generateHeader(String className) { Class<?> clazz;
	 * List<String> collect = null; try { clazz = Class.forName(className); Field[]
	 * declaredFields = clazz.getDeclaredFields(); collect =
	 * Arrays.stream(declaredFields) .filter(e ->
	 * e.isAnnotationPresent(FormField.class) &&
	 * e.getAnnotation(FormField.class).listable()) .map(ScanEntity::createHeader)
	 * .collect(Collectors.toList()); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); } return collect;
	 * 
	 * }
	 */
	
	public static List<String> generateAttributes(String className) {
		Class<?> clazz;
		List<String> collect = null;
		try {
			clazz = Class.forName(className);
			Field[] declaredFields = clazz.getDeclaredFields();
			  collect = Arrays.stream(declaredFields)
					          .filter(e -> e.isAnnotationPresent(FormField.class) && e.getAnnotation(FormField.class).listable())
					          .map(e->e.getName())
					          .collect(Collectors.toList());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return collect;

	}

	public static List<String> generateHeaders(List<String> collect) {
	    return collect.stream().map(ScanEntity::createLabel).collect(Collectors.toList());
	}
	
	/*
	 * static FormFieldBase checkFieldType(Field field) {
	 * 
	 * FormField formField = field.getAnnotation(FormField.class); if
	 * (formField.fieldType() == FieldType.TEXT) { return populateFieldsR(new
	 * TextWrapper(formField), formField, field); } else if (formField.fieldType()
	 * == FieldType.RADIO) { return populateFieldsR(new RadioWrapper(formField),
	 * formField, field); } else if (formField.fieldType() == FieldType.CHECKBOX) {
	 * return populateFieldsR(new CheckBoxWrapper(formField), formField, field);
	 * 
	 * } else if (formField.fieldType() == FieldType.SELECT) { return
	 * populateFieldsR(new SelectWrapper(formField), formField, field);
	 * 
	 * } else if (formField.fieldType() == FieldType.NUMBER) { return
	 * populateFieldsR(new NumberWrapper(formField), formField, field);
	 * 
	 * } else if (formField.fieldType() == FieldType.EMAIL) { return
	 * populateFieldsR(new EmailWrapper(formField), formField, field);
	 * 
	 * } return null; }
	 */
	static FormFieldBase populateFieldsR(FormFieldBase form, FormField formField, Field field) {

		if (formField.id().equals("")) {
			form.setId(field.getName().toLowerCase());
		}
		if (formField.placeHolder().equals("Enter Some Text  ")) {
			form.setPlaceHolder("Enter the " + createLabel(field.getName()));
		}
		if (formField.label().equals("")) {
			form.setLabel(createLabel(field.getName()));
		}
		if (formField.name().equals("")) {
			form.setName(field.getName().toLowerCase());
		}

		return form;

	}

	static void populateFields(FormFieldBase form, List<FormFieldWrapper> fieldWrappers, FormField formField,
			Field field) {
		// TextWrapper wrapper = new TextWrapper(formField);
		if (formField.id().equals("")) {
			form.setId(field.getName().toLowerCase());
		}
		if (formField.placeHolder().equals("Enter Some Text  ")) {
			form.setPlaceHolder("Enter the " + createLabel(field.getName()));
		}
		if (formField.label().equals("")) {
			form.setLabel(createLabel(field.getName()));
		}
		if (formField.name().equals("")) {
			form.setName(field.getName().toLowerCase());
		}

		fieldWrappers.add(form);

	}

	static String createLabel(String label) {

		String[] words = label.split("(?=\\p{Upper})");
		StringBuilder stringBuilder = new StringBuilder();

		for (String str : words)
			stringBuilder.append(str.substring(0, 1).toUpperCase()).append(str.substring(1, str.length())).append(" ");

		return stringBuilder.toString().trim();
	}
}
