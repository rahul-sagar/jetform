package io.jetform.core;

import com.google.gson.Gson;

import io.jetform.core.annotation.model.JetFormWrapper;
import io.jetform.core.engine.helper.FormRenderer;
import io.jetform.core.engine.helper.impl.FormRendererImpl;

public class Testing {
	/*
	 * @Autowired JetFormService formService;
	 */
	public static void main(String[] args) {
		
		  FormRenderer formRenderer = new FormRendererImpl(); JetFormWrapper
		  jetFormWrapper = formRenderer.getForm("io.jetform.core.entity.Employee");
		  
		  Gson gson = new Gson(); String json = gson.toJson(jetFormWrapper);
		  System.out.println(json);
		 
		//Testing testing = new Testing();
		//testing.getJson("io.jetform.core.entity.Employee");
	}
	
	/*
	 * public void getJson(String className) { JetFormService formService = new
	 * JetFormServiceImpl(); String formJson = formService.getFormJson(className);
	 * System.out.println(formJson); }
	 */
}