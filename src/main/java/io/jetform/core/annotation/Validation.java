package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.jetform.core.enums.ValidationType;


@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD })
public @interface Validation {
	
	  ValidationType type() default ValidationType.REQUIRED; 
	  String value() default "";

}