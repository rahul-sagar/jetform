package io.jetform.core.service.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jetform.core.JetformApplication;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.repository.JetFormRepository;
import io.jetform.core.service.JetFormService;

@Component
public class JetFormServiceImpl implements JetFormService {

	@Autowired
	private FormRenderer formRenderer;

	@Autowired
	private JetFormRepository repository;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public String getFormJson(String className) {
		Gson gson = new Gson();
		JetFormWrapper form = formRenderer.getForm(className);
		String json = gson.toJson(form);
		return json;
	}

	@Override
	public List getList(String className) {
		Class<?> forName = null;
		try {
			forName = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repository.getAll(forName);
	}

	@Override
	public List<String> getEntities() {

		Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(JetForm.class);
		Set<String> keySet = beansWithAnnotation.keySet();
		Set<Object> collect = keySet.stream().map(e -> beansWithAnnotation.get(e)).collect(Collectors.toSet());

		// create full qualified name
		List<String> fullQualifiedName = collect.stream().map(a -> {
			String name = a.getClass().getPackage().getName();
			String simpleName = a.getClass().getSimpleName();
			return name + "." + simpleName;
		}).collect(Collectors.toList());
		System.out.println("Map:" + beansWithAnnotation);
		System.out.println("Values" + collect);
		System.out.println("Qualified Names" + fullQualifiedName);
		return fullQualifiedName;
	}

	private Object getEntity(long id, String clasName) {

		Class<?> clazz = null;
		Object entity = null;
		try {
			clazz = Class.forName(clasName);
			entity = repository.getEntity(id, clazz);
			System.out.println("Entity: " + entity);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	public JetFormWrapper getFormWrapper(String className) {

		return formRenderer.getForm(className);
	}

	@Override
	public JetFormWrapper getFormWrapperWithValues(Long id, String className) {

		Object entity = getEntity(id, className);
		JetFormWrapper formWrapper = getFormWrapper(className);

		System.out.println("Entity :" + entity);
		System.out.println("Form wrapper: " + formWrapper);
		List<FormElementWrapper> elements = formWrapper.getElements();

		populateElements(elements, entity);
		System.out.println("Elements after populating: " + elements);
		formWrapper.setElements(elements);
		return formWrapper;
	}

	private void populateElements(List<FormElementWrapper> elements, Object entity) {

		Map<String, String> elementsMapWithValues = new HashMap<String, String>();

		Field[] declaredFields = entity.getClass().getDeclaredFields();
		
		/*
		 * Arrays.stream(declaredFields) .forEach(field->{
		 * 
		 * try {System.out.println(field.getClass()); Object object = field.get(entity);
		 * elementsMapWithValues.put(field.getName(),String.valueOf(object)); } catch
		 * (IllegalArgumentException |IllegalAccessException e1) { e1.printStackTrace();
		 * } });
		 */
		
		for (Field field : declaredFields) {
			field.setAccessible(true);
			
			try {
				Object object = field.get(entity);
				System.out.println("field name: " + field.getName());
				System.out.println("field value: " + object);
				elementsMapWithValues.put(field.getName(), object.toString());
				
			} 
			catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Elements Map: "+ elementsMapWithValues);
		
		elements.forEach(e->{
			e.setValue(elementsMapWithValues.get(e.getName()));
		});
//		System.out.println("elements after population values");
	}

}