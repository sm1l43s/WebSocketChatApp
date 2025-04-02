package by.tsvetkov.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/*
 * Класс - заменитель UserDetails для Spring Security
 */
public record PrincipalDto(Long id, Collection<? extends GrantedAuthority> authorities) {
}
