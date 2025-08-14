package io.github.dotyocode.libraryApi.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.model.entities.usuario.Usuario;
import io.github.dotyocode.libraryApi.services.usuario.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UsuarioService usuarioService;
    @Value("${app.security.default-password:123456}")
    private String SENHA_PADRAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String email = auth2AuthenticationToken.getPrincipal().getAttribute("email");
        Usuario usuario = usuarioService.obterPorEmail(email);

        if (usuario == null) {
            usuario = criarUsuario(email);
        }

        authentication = new CustomAuthentication(usuario);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private Usuario criarUsuario(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setLogin(email);
        usuario.setSenha(SENHA_PADRAO);
        usuario.setRoles(List.of("USER"));

        usuarioService.criarUsuario(usuario);
        return usuario;
    }
}
