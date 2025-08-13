package io.github.dotyocode.libraryApi.mappers.usuario;

import org.mapstruct.Mapper;

import io.github.dotyocode.libraryApi.model.dto.UsuarioDto;
import io.github.dotyocode.libraryApi.model.entities.usuario.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto dto);

    UsuarioDto toDto(Usuario entity);
}
