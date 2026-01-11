package com.example.vn2_ht_student.security.permission;

import com.example.vn2_ht_student.model.enums.Action;
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
        Role resource = permissionCheck.resource();
        Action action = permissionCheck.action();
        Long id = extractId(joinPoint.getArgs());
        if (!policy.allow(resource, action, id)) {
            throw new AccessDeniedException("Access Denied");
        }else {
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