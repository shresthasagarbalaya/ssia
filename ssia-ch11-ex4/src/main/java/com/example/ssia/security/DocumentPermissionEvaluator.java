package com.example.ssia.security;

import com.example.ssia.models.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {


    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {
        Document document = (Document) targetDomainObject;
        String p = (String) permission;

        boolean admin = authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(
                                    g -> g.getAuthority().equals(p));
        return admin ||
                document.getOwner()
                        .equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
