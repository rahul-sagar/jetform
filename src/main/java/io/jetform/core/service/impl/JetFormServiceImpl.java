package io.jetform.core.service.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.activation.FileDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.model.FormElementWrapper;
import io.jetform.core.annotation.model.FormWrapper;
import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.entity.Customer;
import io.jetform.core.entity.DocumentMedia;
import io.jetform.core.repository.DocumentMediaRepo;
import io.jetform.core.repository.JetFormRepository;
import io.jetform.core.service.JetFormService;
import io.jetform.core.service.exception.StorageException;
import io.jetform.util.ReflectionUtils;

@Component
public class JetFormServiceImpl implements JetFormService {

	/*
	 * @Value("${upload.path}") private String path;
	 */

	@Autowired
	private FormRenderer formRenderer;

	@Autowired
	private JetFormRepository repository;

	@Autowired
	DocumentMediaRepo documentrepo;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public DocumentMedia saveDocument(MultipartFile file, String path) {

		if (file.isEmpty()) {

			throw new StorageException("Failed to store empty file");
		}

		try {
			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();
			String tempPath = path + fileName;
			Files.copy(is, Paths.get(tempPath), StandardCopyOption.REPLACE_EXISTING);
			DocumentMedia documentMedia = new DocumentMedia();
			documentMedia.setName(file.getOriginalFilename());
			documentMedia.setSize(file.getSize());
			documentMedia.setContentType(file.getContentType());
			documentMedia.setFilePath(tempPath);
			documentMedia = documentrepo.save(documentMedia);
			return documentMedia;
		} catch (IOException e) {

			String msg = String.format("Failed to store file %f", file.getName());

			throw new StorageException(msg, e);
		}

	}

	/*
	 * @Override public DocumentMedia saveDocument(MultipartFile multipartFile) {
	 * 
	 * 
	 * 
	 * System.out.println(multipartFile.getName());
	 * System.out.println(multipartFile.getOriginalFilename());
	 * System.out.println(multipartFile.getSize());
	 * System.out.println(multipartFile.getContentType());
	 * 
	 * DocumentMedia documentMedia=new DocumentMedia();
	 * documentMedia.setName(multipartFile.getOriginalFilename());
	 * documentMedia.setSize(multipartFile.getSize());
	 * documentMedia.setContentType(multipartFile.getContentType()); return
	 * documentrepo.save(documentMedia);
	 * 
	 * }
	 */

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

		//populateElements(elements, entity);
		System.out.println("Elements before populating: " + elements);
		populateElements(elements, entity);
		System.out.println("Elements after populating: " + elements);
		//formWrapper.setElements(elements);
		return formWrapper;
	}
	
	private void populateElements(List<FormElementWrapper> elements ,Object entity) {
		
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		//elements.stream().filter(e-> e.getName().equalsIgnoreCase(null)).forEach(e->e.setValue(null));
		Arrays.stream(declaredFields).forEach(f->{
			  f.setAccessible(true);
			  if(f.isAnnotationPresent(FormElement.class)&& !f.getAnnotation(FormElement.class).form().formClass().isBlank()) {
				 
				  String formClass = f.getAnnotation(FormElement.class).form().formClass();
				  JetFormWrapper jetFormWrapper = getFormWrapper(formClass);
				  List<FormElementWrapper> elements2 = jetFormWrapper.getElements();
				  elements.stream().filter(e-> e.getName().equalsIgnoreCase(f.getName())).forEach(e -> {
						 FormWrapper formWrapper =(FormWrapper)e;
						 formWrapper.setJetFormWrapper(jetFormWrapper);
					  });
				//  System.out.println("Printing the elements :: "+elements);
				  Object object = null;
					try {
						object = f.get(entity);
					} catch (IllegalArgumentException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  populateElements(elements2,object);
				  
			  }else {
				  try {
					Object object = f.get(entity);
					elements.stream().filter(e-> e.getName().equalsIgnoreCase(f.getName())).forEach(e -> e.setValue(object.toString()));
					  } catch (IllegalArgumentException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					  }
			  }
		});
	}
	
	
	/*
	 * private void populateElements(List<FormElementWrapper> elements, Object
	 * entity) {
	 * 
	 * //Map<String, String> elementsMapWithValues = new HashMap<String, String>();
	 * Map<String, Object> elementsMapWithValues = new HashMap<String, Object>();
	 * 
	 * Field[] declaredFields = entity.getClass().getDeclaredFields();
	 * 
	 * Arrays.stream(declaredFields).forEach(field -> {
	 * 
	 * try { field.setAccessible(true); System.out.println(field.getClass()); Object
	 * object = field.get(entity); //elementsMapWithValues.put(field.getName(),
	 * String.valueOf(object)); elementsMapWithValues.put(field.getName(), object);
	 * } catch (IllegalArgumentException | IllegalAccessException e1) {
	 * e1.printStackTrace(); } });
	 * 
	 * System.out.println("Elements Map: " + elementsMapWithValues);
	 * 
	 * elements.forEach(e -> { if(!e.getFieldType().equalsIgnoreCase("form")) {
	 * e.setValue(String.valueOf(elementsMapWithValues.get(e.getName()))); }else {
	 * String name = e.getName(); FormWrapper formWrapper = (FormWrapper)e; Object
	 * object = elementsMapWithValues.get(name); String formClass =
	 * formWrapper.getFormClass(); JetFormWrapper formWrapper2 =
	 * formWrapper.getJetFormWrapper(); List<FormElementWrapper> elements2 =
	 * formWrapper2.getElements(); populateElements(elements, entity); }
	 * 
	 * });
	 * 
	 * //ognl }
	 * 
	 */
	@Override
	public Object saveEntity(MultiValueMap<String, Object> formData) {
		// List<String> list = formData.get("className");
		List<Object> list = formData.get("className");
		System.out.println("printing the className " + list.get(0));
		Class<?> clazz = null;
		Object entity = null;
		try {
			clazz = Class.forName(list.get(0).toString());
			System.out.println(clazz.getName());
			//entity = getClassField(formData, clazz);
			entity = getClassFieldV2(formData, clazz);
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
		} /*catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
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
		}*/
        //return entity;
		return repository.save(entity);
	}

	private <T> T castObject(Class<T> clazz, Object object) {
		return (T) object;
	}

	private Object getClassFieldV2(MultiValueMap<String, Object> formData, Class<?> clazz) {
		Object saveObject = null;
		try {
			Object newInstance = clazz.getDeclaredConstructor().newInstance();
			
			Arrays.stream(clazz.getDeclaredFields()).forEach(f -> populateObject(formData, newInstance, f));
			saveObject = newInstance;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return saveObject;
	}

	private void populateObject(MultiValueMap<String, Object> formData, Object newInstance, Field f) {
		 f.setAccessible(true);
		if (f.isAnnotationPresent(FormElement.class)
				&& !f.getAnnotation(FormElement.class).form().formClass().isBlank()) {
			String formClass = f.getAnnotation(FormElement.class).form().formClass();
                    Class<?> clazz = getClazz(formClass);  
                   
                    Object classFieldV2 = getClassFieldV2(formData, clazz);
                    try {
						f.set(newInstance, classFieldV2);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //getClassFieldV2(formData, clazz);
		} else {
			try {
				if(formData.keySet().contains(f.getName())) {
					System.out.println("printing the f.getName() :: "+f.getName());
					f.set(newInstance, ReflectionUtils.parse(formData.get(f.getName()).get(0).toString(), f.getType()));
					formData.remove(f.getName());
				}else {
					
					String name = f.getDeclaringClass().getSimpleName().toLowerCase();
					System.out.println("printing the name :: "+name);
					System.out.println("printing the map name :: "+ (name+"."+f.getName()));
					boolean containsKey = formData.containsKey(name+"."+f.getName());
					System.out.println(containsKey);
					System.out.println(formData.get(name+"."+f.getName()).get(0));
					f.set(newInstance, ReflectionUtils.parse(formData.get(name+"."+f.getName()).get(0).toString(), f.getType()));
					formData.remove(name+"."+f.getName());
				}
				//f.set(newInstance, ReflectionUtils.parse(formData.get(f.getName()).get(0).toString(), f.getType()));
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Class<?> getClazz(String className) {
		Class<?> clazz =null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clazz;
	}
	
	public static <T> boolean isJDKClass(T t) {
		
	     return t.getClass().getPackage().getName().startsWith("java");
	}

	public Object getClassField(MultiValueMap<String, Object> formData, Class<?> clazz) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, IntrospectionException, JsonMappingException, JsonProcessingException {
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
		/*
		 * Set<String> keySet = formData.keySet(); Object object =
		 * clazz.getDeclaredConstructor().newInstance(); //Field field;
		 * formData.remove("className"); ObjectMapper mapper = new ObjectMapper();
		 * String writeValueAsString = mapper.writeValueAsString(formData); String
		 * replace = writeValueAsString.replace("[", "").replace("]", "");
		 * System.out.println(writeValueAsString); System.out.println(replace); Object
		 * mappedObject = mapper.readValue(replace, object.getClass());
		 * System.out.println(mappedObject);
		 * System.out.println(mappedObject.getClass().getName()); Object save =
		 * repository.save(mappedObject); //Object readValue =
		 * mapper.readValue(mapper.writeValueAsString(formData), object.getClass());
		 * //mapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
		 * /* MultiValueMap valueMap = new LinkedMultiValueMap<String, Object>();
		 * Map<String, Object> fieldMap = objectMapper.convertValue(requestObject, new
		 * TypeReference<Map<String, Object>>() {}); valueMap.setAll(fieldMap);
		 * System.out.println(readValue); /* for(String attribute:keySet) {
		 * if(attribute.equalsIgnoreCase("classname")) continue;
		 * 
		 * try { field = clazz.getDeclaredField(attribute); Class<?> type =
		 * field.getType(); System.out.println(type.getName());
		 * field.setAccessible(true); System.out.println("FIeld name"+ field.getName());
		 * System.out.println(formData.get(attribute).get(0));
		 * field.set(object,castObject(field.getType(),formData.get(attribute).get(0)));
		 * 
		 * //field.set(object,field.getType().cast(formData.get(attribute).get(0)));
		 * //castObject(field.getType(),formData.get(attribute).get(0))
		 * //field.set(object,formData.get(attribute).get(0)); //
		 * field.set(object,type.cast(formData.get(attribute).get(0))); //f.set(t,
		 * f.getType().cast(entry.getValue())); } catch (NoSuchFieldException
		 * |SecurityException | IllegalArgumentException |IllegalAccessException e) {
		 * e.printStackTrace(); }
		 * 
		 * }
		 */

		Object newInstance = clazz.getDeclaredConstructor().newInstance();
		keySet.stream().filter(e -> !e.equalsIgnoreCase("className")).forEach(attr -> {
			Field f;
			try {
				f = clazz.getDeclaredField(attr);
				System.out.println(f.getName());
				System.out.println(formData.get(attr).get(0));
				f.setAccessible(true);
				f.set(newInstance, ReflectionUtils.parse(formData.get(attr).get(0).toString(), f.getType()));
				// f.set(clazz, formData.get(attr).get(0));
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
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

	public static Object invokeGetter(Object obj, String variableName) {
		Object f = null;
		try {
			PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
			Method getter = pd.getReadMethod();
			f = getter.invoke(obj);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			e.printStackTrace();
		}
		return f;
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
	public boolean deleteMultiple(Long[] deletedIDs, String className) throws ClassNotFoundException {

		Class<?> clazz = Class.forName(className);
		Arrays.stream(deletedIDs).forEach(a -> {
			repository.delete(a, clazz);
		});

		return false;
	}

	@Override
	public List<String> getAutoCompleteSourceData(String className, String fieldName) {
		List<?> list = getList(className);
		List<String> autoCompleteSourceData = new ArrayList<>();
		for (Object object : list) {
			Class<? extends Object> clazz = object.getClass();
			System.out.println("printing the class " + clazz.getName());
			try {
				Field declaredField = clazz.getDeclaredField(fieldName);
				declaredField.setAccessible(true);
				Object object2 = declaredField.get(object);
				// Field field = class1.getField(fieldName);
				// field.setAccessible(true);
				// Object fieldValue = field.get(object);
				autoCompleteSourceData.add(object2.toString());
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" inside service " + autoCompleteSourceData);
		return autoCompleteSourceData;
	}

	@Override
	public List<?> getFilteredList(String className, String filter) {
		List<?> list = getList(className);
		String[] split = filter.split(":");
		System.out.println("printing the the split :: "+filter);
		List<Object> collect = list.stream().map(o -> filter(o, split)).filter(notNull).collect(Collectors.toList());
		
		//list.stream().filter()
		return collect;
	}

	private Predicate<Object> notNull = (Object o)-> o != null;

	private Object filter(Object o,String[] filter) {
		Class clazz = o.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		Object object = Arrays.asList(declaredFields).stream().map( f -> isExists(o, filter, f)).filter(notNull).findFirst().orElse(null);
		return object;
	};

	private Object isExists(Object o,String[] filter,Field field) {
		System.out.println("printing the filter :: "+filter[0]+" "+filter[1]);
		field.setAccessible(true);
		try {
			boolean isFieldValueMatched = field.get(o).toString().equalsIgnoreCase(filter[1]);
			if(field.getName().equalsIgnoreCase(filter[0]) && isFieldValueMatched) {
				
				return o;
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}