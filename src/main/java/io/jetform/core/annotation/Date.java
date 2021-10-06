package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Date Annotation helps to genrate
 * Date type input field elements in Html
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see Hidden
 * @see Number
 * @see Telephone
 * @see Select
 * @see File
 * @see Password
 * @see Checkbox
 * @see Radio
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface Date {
	    String label() default "";
		
		String placeHolder() default "Select date...";
		
	    boolean required() default false;
		
		String errorMessage() default "";
	    
	
	
}
