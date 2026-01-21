package com.example.vn2_ht_student.security.annotation;

import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.ResourceType;
import com.example.vn2_ht_student.model.enums.Role;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionCheck {
    Role[] roles();

    ResourceType resource();

    Action action();


}
