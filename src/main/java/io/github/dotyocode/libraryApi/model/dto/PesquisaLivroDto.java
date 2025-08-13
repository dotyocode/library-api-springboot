package io.github.dotyocode.libraryApi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.dotyocode.libraryApi.enums.livros.GenerosLivros;

public record PesquisaLivroDto(UUID id, String titulo, AutorDto autor, String isbn, GenerosLivros genero,
        BigDecimal preco,
        LocalDate dataPublicacao) {

}
