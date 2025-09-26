
package com.TGarciaProgramacionNCapas25.Proyect.Component;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "Credenciales inválidas";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Usuario o contraseña incorrectos";
        } else if (exception instanceof DisabledException) {
            errorMessage = "La cuenta está desactivada";
        } else if (exception instanceof LockedException) {
            errorMessage = "La cuenta está bloqueada";
        }

        response.sendRedirect("/usuario/Login?error=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8));
    }
}
