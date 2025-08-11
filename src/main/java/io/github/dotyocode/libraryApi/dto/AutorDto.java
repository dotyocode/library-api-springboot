package io.github.dotyocode.libraryApi.dto;

import java.time.LocalDate;

import io.github.dotyocode.libraryApi.model.Autor;

public record AutorDto(String nome, LocalDate dataNascimento, String nascionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setDataNascimento(dataNascimento);
        autor.setNascionalidade(nascionalidade);
        return autor;
    }

}
