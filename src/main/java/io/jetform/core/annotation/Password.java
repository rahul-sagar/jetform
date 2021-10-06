package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Password Annotation helps to genrate
 * Password input field elements
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see File
 * @see Hidden
 * @see Telephone
 * @see Select
 * @see Email
 * @see Radio
 * @see Checkbox
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface Password {

	    String placeHolder() default "Password..... ";
		
	    String label() default "";
		
	    boolean required() default false;
		
		String errorMessage() default "";
	
	    String pattern() default "";

}
