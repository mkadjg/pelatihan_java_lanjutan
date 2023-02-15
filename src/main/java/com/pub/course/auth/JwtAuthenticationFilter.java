package com.pub.course.auth;

import com.pub.course.exception.AuthorizationException;
import com.pub.course.exception.SkipFilterException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    JwtConfig jwtConfig;

    public JwtAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            Claims claims = getClaims(request, response);

            // check token expired
            if (claims.getExpiration().before(new Date())) {
                response.setStatus(401);
                response.getWriter().write("Token Expired!");
                return;
            }

            // check username
            String username = getUsername(claims);

            // check role / authorities
            List<String> authorities = getAuthorities(claims);

            setAuth(username, authorities);
        } catch (SkipFilterException e) {
            // the request skipped
        } catch (AuthorizationException ae) {
            response.setStatus(401);
            response.getWriter().write("User doesn't have access!");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private Claims getClaims(HttpServletRequest request, HttpServletResponse response) throws SkipFilterException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null || !authorizationHeader.startsWith(jwtConfig.getPrefix())) {
            throw new SkipFilterException();
        }

        String token = authorizationHeader.replace(jwtConfig.getPrefix(), "");

        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        response.addHeader(HttpHeaders.AUTHORIZATION, jwtConfig.getPrefix() + token);

        return claims;
    }

    private String getUsername(Claims claims) throws AuthorizationException {
        String username = claims.getSubject();
        if (username == null) {
            throw new AuthorizationException();
        }
        return username;
    }

    private List<String> getAuthorities(Claims claims) throws AuthorizationException {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");
        return authorities;
    }

    private void setAuth(String username, List<String> authorities) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities.stream().map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
