package io.jetform.core.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jetform.core.JetformApplication;
import io.jetform.core.annotation.JetForm;
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

	@Override
	public Object getEntity(long id, String clasName) {
		
		Class<?> clazz=null;
		try {
			clazz = Class.forName(clasName);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repository.getEntity(id, clazz);
	}
	
	
}