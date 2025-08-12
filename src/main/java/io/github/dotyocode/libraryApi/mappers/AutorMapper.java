package io.github.dotyocode.libraryApi.mappers;

import org.mapstruct.Mapper;

import io.github.dotyocode.libraryApi.dto.AutorDto;
import io.github.dotyocode.libraryApi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorDto toDto(Autor autor);

    Autor toEntity(AutorDto dto);

}
