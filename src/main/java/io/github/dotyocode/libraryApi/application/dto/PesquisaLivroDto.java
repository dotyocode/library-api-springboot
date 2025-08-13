package io.github.dotyocode.libraryApi.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.dotyocode.libraryApi.domain.livro.enums.GenerosLivros;

public record PesquisaLivroDto(UUID id, String titulo, AutorDto autor, String isbn, GenerosLivros genero,
                BigDecimal preco,
                LocalDate dataPublicacao) {

}
