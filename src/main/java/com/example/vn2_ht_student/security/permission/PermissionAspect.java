package com.example.vn2_ht_student.security.permission;

import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.ResourceType;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.security.annotation.PermissionCheck;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {

    private final Policy policy;
    @Before("@annotation(permissionCheck)")
    public void checkPermission(
            JoinPoint joinPoint,
            PermissionCheck permissionCheck
    ) {
        Long id = extractId(joinPoint.getArgs());

        if (!policy.allow(
                permissionCheck.roles(),
                permissionCheck.resource(),
                permissionCheck.action(),
                id
        )) {
            throw new AccessDeniedException("Access Denied");
        }
    }


    private Long extractId(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        return null;
    }
}