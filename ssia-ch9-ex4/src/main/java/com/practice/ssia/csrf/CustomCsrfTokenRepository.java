package com.practice.ssia.csrf;

import com.practice.ssia.entities.Token;
import com.practice.ssia.repositories.JpaTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpaTokenRepository jpaTokenRepository;

    public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        if(identifier != null) {
            System.out.println("X-IDENTIFIER missing");
        }
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token csrfToken = existingToken.get();
            csrfToken.setToken(token.getToken());
            jpaTokenRepository.save(csrfToken);
        } else {
            Token csrfToken = new Token();
            csrfToken.setToken(token.getToken());
            csrfToken.setIdentifier(identifier);
            jpaTokenRepository.save(csrfToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token csrfToken = existingToken.get();
            return new DefaultCsrfToken(
                    "X-CSRF-TOKEN",
                    "_csrf",
                    csrfToken.getToken());
        }
        return null;
    }
}
