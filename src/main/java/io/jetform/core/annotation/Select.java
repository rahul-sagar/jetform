package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.jetform.core.enums.ResourceType;
import io.jetform.core.enums.SelectionType;

/**
 * Select Annotation helps to take
 * Single or multitype select value HTML elements
 * @author  Sumant Chaubey
 * @see Text
 * @see Date
 * @see File
 * @see Hidden
 * @see Telephone
 * @see Radio
 * @see Email
 * @see Password
 * @see Checkbox
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface Select {
	///use boolean multi select remove SelectionType
	//SelectionType selectionType() default SelectionType.Single;
	boolean multiSelect() default false;
	DataProvider dataProvider() default @DataProvider(resource = ResourceType.WEB);
	//String callBackUrl() default "";
	String[] options() default {};
	
}
