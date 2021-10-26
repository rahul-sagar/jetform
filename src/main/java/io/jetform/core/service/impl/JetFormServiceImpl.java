package io.jetform.core.service.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.repository.JetFormRepository;
import io.jetform.core.service.JetFormService;
import io.jetform.util.ReflectionUtils;

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
	public List<?> getList(String className) {
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

	@Override
	public Object saveEntity(MultiValueMap<String, Object> formData) {
		//List<String> list = formData.get("className");
		 List<Object> list = formData.get("className");
		System.out.println("printing the className "+list.get(0));
		Class<?> clazz = null;
		Object entity = null;
		try {
			clazz = Class.forName(list.get(0).toString());
			System.out.println(clazz.getName());
			entity = getClassField(formData, clazz);
			// clazz.getDeclaredConstructor().newInstance();
			// Object object = clazz.getDeclaredConstructor().newInstance();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return repository.save(entity);
	}
	
	private <T> T castObject(Class<T> clazz, Object object) {
		  return (T) object;
	}

	public Object getClassField(MultiValueMap<String, Object> formData, Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException, JsonMappingException, JsonProcessingException {
		Set<String> keySet = formData.keySet();
		/*
		 * Set<String> keySet = formData.keySet();
		 * 
		 * Object obj = clazz.getDeclaredConstructor().newInstance(); Class<?extends
		 * Object> class1 = obj.getClass();
		 * 
		 * for(String attribute:keySet) { if(attribute.equalsIgnoreCase("classname"))
		 * continue; PropertyDescriptor pd = new PropertyDescriptor(attribute,
		 * obj.getClass()); //Method getter = pd.getReadMethod(); Method getter =
		 * pd.getWriteMethod(); String string = formData.get(attribute).get(0); Object
		 * object=string; Object invoke = getter.invoke(obj, object); // Object f =
		 * getter.invoke(obj); }
		 */
		  /*Set<String> keySet = formData.keySet();
		  Object object = clazz.getDeclaredConstructor().newInstance();
		  //Field field;
		  formData.remove("className");
		  ObjectMapper mapper = new ObjectMapper();
		  String writeValueAsString = mapper.writeValueAsString(formData);
		  String replace = writeValueAsString.replace("[", "").replace("]", "");
		  System.out.println(writeValueAsString);
		  System.out.println(replace);
		  Object mappedObject = mapper.readValue(replace, object.getClass());
		  System.out.println(mappedObject);
		  System.out.println(mappedObject.getClass().getName());
		  Object save = repository.save(mappedObject);
		  //Object readValue = mapper.readValue(mapper.writeValueAsString(formData), object.getClass());
		  //mapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
		 /* MultiValueMap valueMap = new LinkedMultiValueMap<String, Object>();
		  Map<String, Object> fieldMap = objectMapper.convertValue(requestObject, new TypeReference<Map<String, Object>>() {});
		  valueMap.setAll(fieldMap);
		  System.out.println(readValue);
		  /*	for(String attribute:keySet) { 
		  if(attribute.equalsIgnoreCase("classname"))
		          continue; 
			  
			    try { 
			    	 field = clazz.getDeclaredField(attribute);
			    	 Class<?> type = field.getType();
			    	 System.out.println(type.getName());
		             field.setAccessible(true); 
		             System.out.println("FIeld name"+ field.getName());
		             System.out.println(formData.get(attribute).get(0));
		             field.set(object,castObject(field.getType(),formData.get(attribute).get(0)));
		             
		             //field.set(object,field.getType().cast(formData.get(attribute).get(0)));
		             //castObject(field.getType(),formData.get(attribute).get(0))
		             //field.set(object,formData.get(attribute).get(0));
		            // field.set(object,type.cast(formData.get(attribute).get(0)));
		               //f.set(t, f.getType().cast(entry.getValue()));
		  } catch (NoSuchFieldException |SecurityException | IllegalArgumentException
		  |IllegalAccessException e) { e.printStackTrace(); }
		  
		  }*/
		 
		
		  Object newInstance = clazz.getDeclaredConstructor().newInstance();
	  keySet.stream()
		        .filter(e -> !e.equalsIgnoreCase("className"))
		        .forEach(attr -> { 
		        	  Field f; 
		        	  try { 
		        		   f = clazz.getDeclaredField(attr);
		        		   System.out.println(f.getName());
		        		   System.out.println(formData.get(attr).get(0));
		                   f.setAccessible(true); 
		                   f.set(newInstance, ReflectionUtils.parse(formData.get(attr).get(0).toString(), f.getType()));
		                  // f.set(clazz, formData.get(attr).get(0)); 
		                   } catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) { 
		                	   // TODO // Auto-generated // catch // block
		                        e.printStackTrace(); 
		                   } 
		        	  });
		 
		/*
		 * Arrays.stream(clazz.getDeclaredFields()).forEach(e -> {
		 * e.setAccessible(true); e.set(clazz, e); });
		 */
		return newInstance;
	}
	
	 public static Object invokeGetter(Object obj, String variableName)
	    {    Object f =null;
	        try {
	            PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
	            Method getter = pd.getReadMethod();
	             f = getter.invoke(obj);
	           
	        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
	            e.printStackTrace();
	        }
	        return f;
	    }

	private void populateElements(List<FormElementWrapper> elements, Object entity) {

		Map<String, String> elementsMapWithValues = new HashMap<String, String>();

		Field[] declaredFields = entity.getClass().getDeclaredFields();

		Arrays.stream(declaredFields).forEach(field -> {

			try {
				field.setAccessible(true);
				System.out.println(field.getClass());
				Object object = field.get(entity);
				elementsMapWithValues.put(field.getName(), String.valueOf(object));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		});

		System.out.println("Elements Map: " + elementsMapWithValues);

		elements.forEach(e -> {
			e.setValue(elementsMapWithValues.get(e.getName()));
		});
	}

	@Override
	public boolean deleteEntity(Long id, String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
			repository.delete(id, clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public Object saveEntity(Object object) {
		// TODO Auto-generated method stub
		Object save = repository.save(object);
		return save;
	}

	@Override
	public boolean deleteMultiple(Long [] deletedIDs, String className) throws ClassNotFoundException {
		
		Class<?> clazz = Class.forName(className);
		Arrays.stream(deletedIDs)
			.forEach(a->{
				repository.delete(a, clazz);
			});
	
		return false;
	}

}