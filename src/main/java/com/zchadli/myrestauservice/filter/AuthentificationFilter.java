package com.zchadli.myrestauservice.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zchadli.myrestauservice.entities.CustomUserDetails;
import com.zchadli.myrestauservice.entities.UserLogin;
import com.zchadli.myrestauservice.exceptions.ApiExceptionError;
import com.zchadli.myrestauservice.exceptions.BusinessException;
import com.zchadli.myrestauservice.exceptions.ErrorsMessage;
import com.zchadli.myrestauservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class AuthentificationFilter extends UsernamePasswordAuthenticationFilter {
    private final  AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserLogin user = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);
            log.info(user.getUsername()+" "+user.getPassword());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException failed) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        BusinessException exception = null;
        if(failed instanceof InternalAuthenticationServiceException) {
            exception=new BusinessException(ErrorsMessage.USERNAME_NOT_FOUND);
        }
        else if(failed instanceof BadCredentialsException) {
            exception=new BusinessException(ErrorsMessage.AUTHENTIFICATION_FAILED);
        }
        response.setStatus(exception.getErrorsMessage().getHttpStatus().value());
        ApiExceptionError error = new ApiExceptionError(exception.getErrorsMessage().getHttpStatus().value(), exception.getErrorsMessage().getMessage(), request.getServletPath());
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
 
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String access_token = jwtUtil.generateToken(user);
        HashMap<String, Object> data = new HashMap<>();
        data.put("access_token", access_token);
        data.put("isAdmin", jwtUtil.isAdmin(access_token));
        data.put("username", jwtUtil.getusernameFromToken(access_token));
        data.put("expires_in", jwtUtil.getExpiresInFromToken(access_token));
        data.put("imagePath", user.getImagePath());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }


}
