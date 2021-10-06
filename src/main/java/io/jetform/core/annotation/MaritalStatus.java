package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface MaritalStatus {
	 String label() default "Select your marital status";
		
	    boolean required() default false;
		
		String errorMessage() default "";
}
