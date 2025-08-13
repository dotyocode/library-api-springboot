package io.github.dotyocode.libraryApi.mappers.autor;

import org.mapstruct.Mapper;

import io.github.dotyocode.libraryApi.model.dto.AutorDto;
import io.github.dotyocode.libraryApi.model.entities.autor.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorDto toDto(Autor autor);

    Autor toEntity(AutorDto dto);

}
