package io.jetform.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Number Annotation helps to generated
 * number type input field elements in Html
 *
 * @author Sumant Chaubey
 * @see Text
 * @see Date
 * @see File
 * @see Hidden
 * @see Telephone
 * @see Select
 * @see Email
 * @see Password
 * @see Checkbox
 * @see Radio
 */
@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface Number {
	String format() default "";
}
