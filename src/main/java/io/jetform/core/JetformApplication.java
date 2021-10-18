package io.jetform.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JetformApplication {
	public static void main(String[] args) {
		SpringApplication.run(JetformApplication.class, args);		
	


		/*
		 * ApplicationContext context =
		 * SpringApplication.run(JetformApplication.class,args);
		 * 
		 * Map<String, Object> beansWithAnnotation =
		 * context.getBeansWithAnnotation(JetForm.class); Set<String> keySet =
		 * beansWithAnnotation.keySet(); Set<Object> collect = keySet.stream().map(e ->
		 * beansWithAnnotation.get(e)).collect(Collectors.toSet());
		 * 
		 * //create full qualified name List<String> fullQualifiedName =
		 * collect.stream().map(a -> { String name =
		 * a.getClass().getPackage().getName(); String simpleName =
		 * a.getClass().getSimpleName(); return name + "." + simpleName;
		 * }).collect(Collectors.toList());
		 * System.out.println("Map:"+beansWithAnnotation);
		 * System.out.println("Values"+collect);
		 * System.out.println("Qualified Names"+fullQualifiedName);
		 */
	}
}