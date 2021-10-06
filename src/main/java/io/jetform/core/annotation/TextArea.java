package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface TextArea {
	String placeHolder() default "Write somthing";
	
    String label() default "";
	
    boolean required() default false;
	
	String errorMessage() default "";
    int rows() default 4;
    int cols() default 50;
}
