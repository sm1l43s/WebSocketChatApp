package by.tsvetkov.config;

import by.tsvetkov.dto.PrincipalDto;
import by.tsvetkov.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
@RequiredArgsConstructor
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        try {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest req = servletRequest.getServletRequest();

            String token = req.getParameter("token");

            if (token != null && token.startsWith("Bearer_")) {
                token = token.substring(7);
            }

            if (token == null) {
                String authHeader = req.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                }
            }

            if (token == null) {
                String xAuth = req.getHeader("X-Auth-Token");
                if (xAuth != null) {
                    token = xAuth.substring(7);
                }
            }

            if (token == null) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

            boolean authenticated = false;

            String userEmail = jwtService.extractUsername(token);
            String userRole = jwtService.getUserRole(token);
            Long userId = jwtService.getUserId(token);
            if (userEmail != null && getContext().getAuthentication() == null) {
                final PrincipalDto principalDto = new PrincipalDto(userId, List.of(new SimpleGrantedAuthority(userRole)));
                if (!jwtService.isTokenValid(token)) {
                    throw new InvalidTokenException("Некорректный токен");
                }
                final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principalDto,
                        null, principalDto.authorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                getContext().setAuthentication(authToken);
                authenticated = true;
            }

            return authenticated;

        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }
}
