package io.github.dotyocode.libraryApi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dotyocode.libraryApi.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

    List<Autor> findByNomeOrNascionalidade(String nome, String nascionalidade);

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    List<Autor> findByNascionalidadeContainingIgnoreCase(String nascionalidade);
}
