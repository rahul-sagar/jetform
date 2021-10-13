package io.jetform.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.service.JetFormService;

@Component
public class JetFormServiceImpl implements JetFormService {

	@Autowired
	private FormRenderer formRenderer;
	
	@Override
	public String getFormJson(String className) {
		Gson gson = new Gson();
		JetFormWrapper form = formRenderer.getForm(className);
        String json = gson.toJson(form);
		return json;
	}

}
