package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface RangeSlider {
	
	String label() default "";

	boolean required() default false;

	int max() default 100;
	
	int min() default 1;

	String errorMessage() default "";

}
