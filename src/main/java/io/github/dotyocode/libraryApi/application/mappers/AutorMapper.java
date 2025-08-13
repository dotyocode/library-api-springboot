package io.github.dotyocode.libraryApi.application.mappers;

import org.mapstruct.Mapper;

import io.github.dotyocode.libraryApi.application.dto.AutorDto;
import io.github.dotyocode.libraryApi.domain.autor.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorDto toDto(Autor autor);

    Autor toEntity(AutorDto dto);

}
