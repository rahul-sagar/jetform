package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface ToggleButton {
	boolean selectedValue() default true;

	String label() default "";

	boolean required() default false;
	
	String errorMessage() default "";
}
