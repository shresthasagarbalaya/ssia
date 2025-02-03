package com.example.ssia.services;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @RolesAllowed("ADMIN")
    public String getName() {
        return "Fantastico";
    }
}
