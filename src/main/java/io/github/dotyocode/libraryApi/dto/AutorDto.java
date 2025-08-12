package io.github.dotyocode.libraryApi.dto;

import java.time.LocalDate;
import java.util.UUID;

import io.github.dotyocode.libraryApi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record AutorDto(
        UUID id,
        @NotBlank(message = "Nome é obrigatório") @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") String nome,

        @NotNull(message = "Data de nascimento é obrigatória") @Past(message = "Não pode ser uma data futura") LocalDate dataNascimento,

        @NotBlank(message = "Nacionalidade é obrigatória") @Size(min = 3, max = 50, message = "Nacionalidade deve ter entre 3 e 50 caracteres") String nascionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setDataNascimento(dataNascimento);
        autor.setNascionalidade(nascionalidade);
        return autor;
    }

}
