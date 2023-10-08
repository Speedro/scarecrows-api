package cz.scarecrows.eventmanager.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import cz.scarecrows.eventmanager.exception.UnauthorizedExcpetion;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${auth.jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(request));

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
        final String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)) {
            final DecodedJWT verifiedToken = JWT.require(Algorithm.HMAC256(secret)).build().verify(token.replace("Bearer ", ""));
            return new UsernamePasswordAuthenticationToken(verifiedToken.getSubject(), verifiedToken.getClaim("roles"));
        }
        throw new UnauthorizedExcpetion("Access denied.");
    }
}
