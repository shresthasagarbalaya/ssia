package com.example.ssia.security;

import com.example.ssia.models.Document;
import com.example.ssia.repository.DocumentRepository;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {


    private final DocumentRepository documentRepository;

    public DocumentsPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId,
                                 String targetType, Object permission) {
        String code = (String) targetId;
        Document document = documentRepository.findDocument(code);

        String p = permission.toString();
        boolean admin = authentication.getAuthorities()
                .stream()
                .anyMatch(g -> g.getAuthority().equals(p));
        return admin || document.getOwner().equals(authentication.getName());
    }
}
