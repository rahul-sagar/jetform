package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * File Annotation helps to genrate
 * File type input field elements in Html
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see Hidden
 * @see Number
 * @see Telephone
 * @see Select
 * @see Email
 * @see Password
 * @see Checkbox
 * @see Radio
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface File {
	
    String label() default "";
	
	String placeHolder() default "Select File...";
	
    boolean required() default false;
	
	String errorMessage() default "";
	
	boolean autoUploader() default false;


}
