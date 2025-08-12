package io.github.dotyocode.libraryApi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.dotyocode.libraryApi.dto.CadastroLivroDto;
import io.github.dotyocode.libraryApi.model.Livro;
import io.github.dotyocode.libraryApi.repository.AutorRepository;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {
    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.autorId()).orElseThrow(() -> new RuntimeException(\"Autor não encontrado\")))")
    public abstract Livro toEntity(CadastroLivroDto dto);
}
