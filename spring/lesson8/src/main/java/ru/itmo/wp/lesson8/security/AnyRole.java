package ru.itmo.wp.lesson8.security;

import ru.itmo.wp.lesson8.domain.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnyRole {
    Role.Name[] value() default {};
}
