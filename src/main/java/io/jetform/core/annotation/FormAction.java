package io.jetform.core.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.jetform.core.enums.Action;
import io.jetform.core.enums.CTAType;
import io.jetform.core.enums.FormActionType;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@EnableFormBuilder
public @interface FormAction {
     String url() ;
     Action action()  default Action.CREATE;
     FormActionType type() default FormActionType.CRUD;
     CTAType ctaType() default CTAType.BUTTON;
     String buttonOrLinkValue() default "";
     boolean listable() default false;
}
