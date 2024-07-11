package com.challengeone.forohub.configuration;

import com.challengeone.forohub.entity.UserEntity;
import com.challengeone.forohub.reposotory.UserRepository;
import com.challengeone.forohub.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = Optional.ofNullable(request.getHeader("Authorization")).orElse("");
        jwtToken = jwtToken.replace("Bearer ", "");
//        System.out.println("jwtToken: " + jwtToken);
        Optional<String> subject = jwtService.getSubject(jwtToken);
        if (subject.isPresent()) {
            UserEntity user = userRepository.findByUsername(subject.get());
            if (user != null) {
                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
