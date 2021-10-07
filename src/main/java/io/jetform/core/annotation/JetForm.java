package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
@java.lang.annotation.Target(TYPE)
public @interface JetForm {
    String id() default "";
    String name() default "";
    String title() default "";
    FormAction [] actions();
}
