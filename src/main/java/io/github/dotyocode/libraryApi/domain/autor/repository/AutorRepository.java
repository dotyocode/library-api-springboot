package io.github.dotyocode.libraryApi.domain.autor.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dotyocode.libraryApi.domain.autor.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

    List<Autor> findByNomeOrNascionalidade(String nome, String nascionalidade);

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    List<Autor> findByNascionalidadeContainingIgnoreCase(String nascionalidade);

    Optional<Autor> findByNome(String nome);

    boolean existsByNome(String nome);

    Optional<Autor> findByNomeAndDataNascimentoAndNascionalidade(String nome, LocalDate dataNascimento,
            String nascionalidade);

}
