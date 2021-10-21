package io.jetform.core.service;

import java.util.List;

import io.jetform.core.annotation.model.JetFormWrapper;

public interface JetFormService {
    public String getFormJson(String className);
    public List  getList(String className);
    public List<String> getEntities();
    public Object getEntity(long id , String clasName);
    JetFormWrapper getFormWrapper(String className);
}
