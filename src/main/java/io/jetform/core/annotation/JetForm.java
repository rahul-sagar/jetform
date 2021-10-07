package io.jetform.core.annotation;

public @interface JetForm {
    String id() default "";
    String name() default "";
    String title() default "";
    FormAction [] actions();
}
