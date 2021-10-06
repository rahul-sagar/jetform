package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.jetform.core.enums.ResourceType;


/**
 * Radio Annotation helps to take
 * Single or multitype radioBox value HTML elements
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see File
 * @see Hidden
 * @see Telephone
 * @see Select
 * @see Email
 * @see Password
 * @see Checkbox
 * 
 */


//@Target(ElementType.TYPE)
@Target(value = {FIELD,ElementType.TYPE})
@Retention(RUNTIME)
@EnableFormBuilder
public @interface Radio {
	DataProvider dataProvider() default @DataProvider(resource = ResourceType.WEB);
	String[] options() default {};
}