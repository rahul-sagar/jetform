package io.jetform.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.jetform.core.annotation.model.FormFieldBase;
import io.jetform.core.annotation.model.TextWrapper;
import io.jetform.core.enums.FieldType;
import io.jetform.core.enums.LoadType;
import io.jetform.core.enums.ResourceType;

@Radio
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
	//FormFieldBase formFieldBase() default FormBase.getFromBase();
	//public static final TextWrapper textWrapper = new TextWrapper();
	Text text() default @Text();
	Form form() default @Form();
	Select select() default @Select();
	Number number() default @Number();
	Email email() default @Email();
	Radio radio() default @Radio(dataProvider = @DataProvider(loadType = LoadType.LAZY,path = "",resource = ResourceType.WEB));	
	Checkbox checkbox() default @Checkbox(dataProvider = @DataProvider(loadType = LoadType.LAZY,path = "",resource = ResourceType.WEB));

    class FormBase{
    	static FormFieldBase getFromBase() {
    		return new FormFieldBase();
    	}
    }


}
