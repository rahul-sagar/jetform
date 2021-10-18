package io.jetform.core.controller;

import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

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
		Gson gson=new Gson();
		String json = gson.toJson(entities);
		return json;
	}
	
	@GetMapping(value="/list/{className}",produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getList(@PathVariable("className") String className) {
		System.out.println("inside list"+className);
		Gson gson=new Gson();
		String json = gson.toJson(jetFormService.getList(className));
		System.out.println("Json:"+json);
		return json;
	}
	
}