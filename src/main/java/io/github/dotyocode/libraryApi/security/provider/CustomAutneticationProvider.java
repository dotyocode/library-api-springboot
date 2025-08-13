package io.github.dotyocode.libraryApi.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.model.entities.usuario.Usuario;
import io.github.dotyocode.libraryApi.security.CustomAuthentication;
import io.github.dotyocode.libraryApi.services.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAutneticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.obterPorLogin(login);

        if (usuario == null) {
            throw getErroUsuarioNaoEncontrado();
        }

        String senhaCriptografada = usuario.getSenha();

        boolean senhaValida = passwordEncoder.matches(senha, senhaCriptografada);

        if (senhaValida) {
            return new CustomAuthentication(usuario);
        }

        throw getErroUsuarioNaoEncontrado();
    }

    private UsernameNotFoundException getErroUsuarioNaoEncontrado() {
        return new UsernameNotFoundException("Usuário e/ou senha incorretos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
