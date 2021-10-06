package io.jetform.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import io.jetform.core.annotation.FormEntity;

@SpringBootApplication
public class JetformApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JetformApplication.class, args);

		Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(FormEntity.class);
		Set<String> keySet = beansWithAnnotation.keySet();
		Set<Object> collect = keySet.stream().map(e -> beansWithAnnotation.get(e)).collect(Collectors.toSet());
		
		//create full qualified name
		List<String> fullQualifiedName = collect.stream().map(a -> {
			String name = a.getClass().getPackage().getName();
			String simpleName = a.getClass().getSimpleName();
			return name + "." + simpleName;
		}).collect(Collectors.toList());
		System.out.println("Map:"+beansWithAnnotation);
		System.out.println("Values"+collect);
		System.out.println("Qualified Names"+fullQualifiedName);		

	}

}