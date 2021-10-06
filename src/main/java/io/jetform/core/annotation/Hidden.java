package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Hidden Annotation helps to genrate
 * Hidden type input field elements in Html
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see File
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
public @interface Hidden {

	String value() default "";
	

}
