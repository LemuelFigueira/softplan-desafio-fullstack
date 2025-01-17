package com.br.api.filter;

import com.br.api.model.User;
import com.br.api.repository.UserRepository;
import com.br.api.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request);
        boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
        if (tokenValid) {
            this.authenticate(tokenFromHeader, request);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String tokenFromHeader, HttpServletRequest request) {
        Integer id = tokenService.getTokenId(tokenFromHeader);

        Optional<User> optionalUser = userRepository.findById(id.longValue());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, Collections.singletonList(user.getProfile()));
            SecurityContext securityContext = SecurityContextHolder.getContext();

            securityContext.setAuthentication(usernamePasswordAuthenticationToken);

            // Set updated securityContext into session of user
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
