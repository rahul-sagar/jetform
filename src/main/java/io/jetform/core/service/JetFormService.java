package io.jetform.core.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import io.jetform.core.annotation.model.JetFormWrapper;

public interface JetFormService {
    public String getFormJson(String className);
    public List<?> getList(String className);
    public List<String> getEntities();
    public JetFormWrapper getFormWrapper(String className);
    public JetFormWrapper getFormWrapperWithValues(Long id,String className);
    public Object saveEntity(MultiValueMap<String, Object> formData);
    public Object saveEntity(Object object);
    public boolean deleteEntity(Long id,String className);
    public boolean deleteMultiple(Long []deletedIDs,String className) throws ClassNotFoundException;
}
