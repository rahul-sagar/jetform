package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Tel Annotation helps to take
 * Phone or Mobile type input field elements in Html
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see File
 * @see Hidden
 * @see Select
 * @see Radio
 * @see Email
 * @see Password
 * @see Checkbox
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface Telephone {
	
	   String placeHolder() default "Enter Mobile Number....";
		
	    String label() default "";
		
	    boolean required() default false;
		
	    String pattern() default "";
		String errorMessage() default "";

}
