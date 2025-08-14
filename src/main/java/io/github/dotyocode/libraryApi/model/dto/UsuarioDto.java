package io.github.dotyocode.libraryApi.model.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioDto(
        @NotBlank(message = "O login é obrigatório") String login,
        @NotBlank(message = "A senha é obrigatória") String senha,
        @Email(message = "O email deve ser válido") String email,
        @NotEmpty(message = "As roles são obrigatórias") List<String> roles) {

}
