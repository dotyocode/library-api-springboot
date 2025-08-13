package io.github.dotyocode.libraryApi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.model.entities.usuario.Usuario;
import io.github.dotyocode.libraryApi.services.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UsuarioService usuarioService;

    public Usuario obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthentication customAuth) {
            return customAuth.getUsuario();
        }
        return null;
    }
}
