package io.github.dotyocode.libraryApi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.dotyocode.libraryApi.enums.GenerosLivros;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroLivroDto(
                @NotBlank(message = "ISBN é obrigatório") String isbn,
                @NotBlank(message = "Título é obrigatório") String titulo,
                @NotNull(message = "Data de publicação é obrigatória") @Past(message = "Data de publicação deve ser uma data no passado") LocalDate dataPublicacao,
                @NotNull(message = "Gênero é obrigatório") GenerosLivros genero,
                BigDecimal preco, UUID autorId) {

}
