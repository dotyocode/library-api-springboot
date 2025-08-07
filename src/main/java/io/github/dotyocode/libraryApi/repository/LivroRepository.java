package io.github.dotyocode.libraryApi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dotyocode.libraryApi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

}
