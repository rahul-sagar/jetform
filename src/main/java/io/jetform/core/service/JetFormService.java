package io.jetform.core.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import io.jetform.core.annotation.model.JetFormWrapper;

public interface JetFormService {
     String getFormJson(String className);
     List  getList(String className);
     List<String> getEntities();
    JetFormWrapper getFormWrapper(String className);
    JetFormWrapper getFormWrapperWithValues(Long id,String className);
    
    public Object saveEntity(MultiValueMap<String, String> formData);
}
