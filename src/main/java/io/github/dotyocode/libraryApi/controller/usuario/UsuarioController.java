package io.github.dotyocode.libraryApi.controller.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dotyocode.libraryApi.mappers.usuario.UsuarioMapper;
import io.github.dotyocode.libraryApi.model.dto.UsuarioDto;
import io.github.dotyocode.libraryApi.services.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody @Valid UsuarioDto dto) {
        var usuario = usuarioMapper.toEntity(dto);
        usuarioService.criarUsuario(usuario);
    }

}
