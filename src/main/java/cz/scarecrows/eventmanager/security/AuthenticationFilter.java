package cz.scarecrows.eventmanager.security;

import java.io.IOException;
import java.util.Set;

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


    private static final Set<String> SWAGGER_RELATED_ALLOWED_PATHS = Set.of(
            "/swagger-ui/index.html",
            "/swagger-ui/springfox.css",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/springfox.js",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/swagger-resources/configuration/ui",
            "/v2/api-docs"
    );

    @Value("${auth.jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain) throws IOException, ServletException {


        if (SWAGGER_RELATED_ALLOWED_PATHS.contains(request.getRequestURI()) || request.getRequestURI().contains("swagger")) {
            chain.doFilter(request, response);
            return;
        }

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
