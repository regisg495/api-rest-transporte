package com.br.transporteapi.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private AuthenticationTokenProvider authenticationTokenProvider;

    public AuthenticationTokenFilter(AuthenticationTokenProvider authenticationTokenProvider) {
        this.authenticationTokenProvider = authenticationTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = authenticationTokenProvider.recoverToken(request);

        if (token != null && authenticationTokenProvider.isValidToken(token)) {
            Authentication authentication = this.authenticationTokenProvider.getAuthentication(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
