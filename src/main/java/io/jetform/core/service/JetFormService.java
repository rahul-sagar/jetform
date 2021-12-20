package io.jetform.core.service;

import java.util.List;

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
	DocumentMedia saveDocument(MultipartFile multipartFile, String uploadPath);
}
