package io.github.dotyocode.libraryApi.mappers.livro;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.dotyocode.libraryApi.model.dto.CadastroLivroDto;
import io.github.dotyocode.libraryApi.model.dto.PesquisaLivroDto;
import io.github.dotyocode.libraryApi.model.entities.livros.Livro;
import io.github.dotyocode.libraryApi.repository.autor.AutorRepository;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {
    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.autorId()).orElseThrow(() -> new RuntimeException(\"Autor não encontrado\")))")
    public abstract Livro toEntity(CadastroLivroDto dto);

    public abstract PesquisaLivroDto toPesquisaDto(Livro livro);
}
