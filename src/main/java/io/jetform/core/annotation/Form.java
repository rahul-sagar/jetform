package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD })
public @interface Form {
	String formClass() default "" ;
	String mappingClass() default "" ;
	String parentKey() default "";
	String childKey() default "";
	//String label() default ""; 
	//String listClass() default "";
	//boolean editable() default false;	
}
//@FormElement(name = "contact",
//controlType = "form",
//parentKey = "empId",
//childKey = "contactId",
//formClass = "form.builder.models.Employee",
//mappingClass = "form.builder.models.Contact",
//groupId = 3)