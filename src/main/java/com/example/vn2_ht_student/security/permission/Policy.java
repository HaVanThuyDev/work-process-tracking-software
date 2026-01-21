package com.example.vn2_ht_student.security.permission;

import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.ResourceType;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Policy {

    public boolean allow(
            Role[] roles,
            ResourceType resource,
            Action action,
            Long resourceId
    ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        boolean hasRole = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + roles[0].name()));

        if (!hasRole) {
            return false;
        }
        for (Scope scope : Scope.values()) {
            String authority = resource + "." + action + ":" + scope;
            if (hasAuthority(auth, authority)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasAuthority(Authentication auth, String authority) {
        return auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }
}
