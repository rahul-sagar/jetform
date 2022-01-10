package io.jetform.core.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.entity.DocumentMedia;

public interface JetFormService {
    public String getFormJson(String className);
    public List<?> getFilteredList(String className,String filter);
    public List<?> getList(String className);
    public List<String> getEntities();
    public JetFormWrapper getFormWrapper(String className);
    public JetFormWrapper getFormWrapperWithValues(Long id,String className);
    public Object saveEntity(MultiValueMap<String, Object> formData);
    public Object saveEntity(Object object);
    public boolean deleteEntity(Long id,String className);
    public boolean deleteMultiple(Long []deletedIDs,String className) throws ClassNotFoundException;
    public List<String> getAutoCompleteSourceData(String className,String fieldName);
	public DocumentMedia saveDocument(MultipartFile multipartFile, String uploadPath);
	public Object saveEntityByOGNL(MultiValueMap<String, Object> formData);
	
	static List<String> getData(String data) {
		System.out.println("printing the data :: "+data);
		Map<String, List<String>> dataMap = new TreeMap<>();
		dataMap.put("Section", List.of("Section-A", "Section-B", "Section-C", "Section-D"));
		List<String> list = dataMap.get(data);
		return list;
	}
}
