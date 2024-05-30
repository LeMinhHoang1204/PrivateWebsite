package com.example.springbootreact.pw.Config;

import com.example.springbootreact.pw.Service.OurUserDetailsService;
import com.example.springbootreact.pw.Service.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    //@Autowired
    private final JWTUtils jwtUtils;

    //@Autowired
    private final OurUserDetailsService ourUserDetailsService;

    @Autowired
    public JWTAuthFilter(OurUserDetailsService ourUserDetailsService, JWTUtils jwtUtils) {
        this.ourUserDetailsService = ourUserDetailsService;
        this.jwtUtils = jwtUtils;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwtToken;
            final Integer userId;

            if (authHeader == null || authHeader.isBlank()) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.substring(7);

            userId = jwtUtils.extractUsername(jwtToken);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = ourUserDetailsService.loadUserByUsername(String.valueOf(userId));

                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // Ghi log ngoại lệ và ném lại ServletException để xử lý ngoại lệ trong Spring
            logger.error("Error occurred during request processing", e);
            throw new ServletException("Error occurred during request processing", e);
        }
    }

}
