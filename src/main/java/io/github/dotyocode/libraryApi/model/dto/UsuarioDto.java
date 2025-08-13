package io.github.dotyocode.libraryApi.model.dto;

import java.util.List;

public record UsuarioDto(String login, String senha, List<String> roles) {

}
