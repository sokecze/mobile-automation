package com.sokecze.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Element {
    String xpath() default "";

    String id() default "";

    String uiAutomator() default "";

    String accessibilityId() default "";
}
