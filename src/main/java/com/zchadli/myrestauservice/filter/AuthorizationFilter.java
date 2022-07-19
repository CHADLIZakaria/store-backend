package com.zchadli.myrestauservice.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zchadli.myrestauservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

   
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getServletPath().contains("/api/login")) {
           
            filterChain.doFilter(request, response);
        }
        else {
            String jwtToken = extractJwtFromToken(request);
            
            
            if(StringUtils.hasText(jwtToken)) {
                try {
                    //String token = authorizationHeader.substring("Bearer ".length());
                    //Algorithm algorithm = Algorithm.HMAC256("secret ".getBytes());
                    //JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    //DecodedJWT decodedJwt = jwtVerifier.verify(token);
                    //String username =  decodedJwt.getSubject();
                    //String roles [] = decodedJwt.getClaim("roles").asArray(String.class);
                    //Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    //Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                    //UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    //SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUtil.getusernameFromToken(jwtToken), null, jwtUtil.getRolesFromToken(jwtToken));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }
                catch(Exception exception) {
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    Map<String, String> error = new HashMap<String, String>();
                    error.put("error message", exception.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
            else {
                filterChain.doFilter(request, response);
            }

        }
    }

    private String extractJwtFromToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {          
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    
}
