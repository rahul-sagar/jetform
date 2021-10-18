package io.jetform.core.service;

import java.util.List;

public interface JetFormService {
    public String getFormJson(String className);
    public List  getList(String className);
    public List<String> getEntities();
}
