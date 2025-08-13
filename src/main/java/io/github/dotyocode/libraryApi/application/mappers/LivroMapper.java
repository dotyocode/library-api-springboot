package io.github.dotyocode.libraryApi.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.dotyocode.libraryApi.application.dto.CadastroLivroDto;
import io.github.dotyocode.libraryApi.application.dto.PesquisaLivroDto;
import io.github.dotyocode.libraryApi.domain.autor.repository.AutorRepository;
import io.github.dotyocode.libraryApi.domain.livro.model.Livro;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {
    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.autorId()).orElseThrow(() -> new RuntimeException(\"Autor não encontrado\")))")
    public abstract Livro toEntity(CadastroLivroDto dto);

    public abstract PesquisaLivroDto toPesquisaDto(Livro livro);
}
