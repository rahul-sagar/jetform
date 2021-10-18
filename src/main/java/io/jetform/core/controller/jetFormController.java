package io.jetform.core.controller;

import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.jetform.core.service.JetFormService;

@RestController
public class jetFormController {

	@Autowired
	private JetFormService jetFormService;
	
	@GetMapping("/jetJson/{className}")
	public String getForm(@PathVariable String className) {
		String formJson = jetFormService.getFormJson(className);
		return formJson;
	}

	@GetMapping("/getEntities")
	public String getEntities() {
		List<String> entities = jetFormService.getEntities();
		entities.forEach(System.out::print);
			
		return null;
	}
}