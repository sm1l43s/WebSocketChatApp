package by.tsvetkov.config;

import by.tsvetkov.dto.PrincipalDto;
import by.tsvetkov.exception.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        final String userRole;
        final Long userId;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        userRole = jwtService.getUserRole(jwt);
        userId = jwtService.getUserId(jwt);
        if (userEmail != null && getContext().getAuthentication() == null) {
            final PrincipalDto principalDto = new PrincipalDto(userId, List.of(new SimpleGrantedAuthority(userRole)));
            if (!jwtService.isTokenValid(jwt)) {
                throw new InvalidTokenException("Некорректный токен");
            }
            final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principalDto,
                    null, principalDto.authorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}


