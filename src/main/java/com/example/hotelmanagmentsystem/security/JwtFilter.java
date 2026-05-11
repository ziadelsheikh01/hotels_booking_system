package com.example.hotelmanagmentsystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
@Component
public class JwtFilter extends OncePerRequestFilter {

    private  JwtService jwtService ;
    private UserDetailsService userDetailsService ;

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");

        if ( authHeader!= null&&SecurityContextHolder.getContext().getAuthentication()== null && authHeader.startsWith("Bearer ") )
        {
           String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);
           UserDetails userDetails = userDetailsService.loadUserByUsername(email);
           if (userDetails != null && jwtService.validateToken(token,userDetails))
           {
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request)
               );
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }
        filterChain.doFilter(request,response);
    }
}
