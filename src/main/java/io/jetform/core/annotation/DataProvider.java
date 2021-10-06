package io.jetform.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.jetform.core.enums.LoadType;
import io.jetform.core.enums.ResourceType;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DataProvider {
	
	String path() default "";
	ResourceType resource();
	LoadType loadType() default LoadType.EAGER;
	String key() default "";
	String value() default "";
}
