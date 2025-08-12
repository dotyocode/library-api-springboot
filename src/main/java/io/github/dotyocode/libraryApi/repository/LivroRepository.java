package io.github.dotyocode.libraryApi.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dotyocode.libraryApi.model.Autor;
import io.github.dotyocode.libraryApi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByTituloAndAutorAndPreco(String titulo, Autor autor, BigDecimal preco);

    default List<Livro> findByAutorOrThrow(Autor autor) {
        List<Livro> livros = findByAutor(autor);
        if (livros == null || livros.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado para o autor informado.");
        }
        return livros;
    }

    default List<Livro> findByTitulo(String titulo) {
        List<Livro> livros = findByTituloContainingIgnoreCase(titulo);
        if (livros == null || livros.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado para o titulo informado.");
        }
        return livros;
    }

    default List<Livro> findByTituloOrAutorOrPreco(String titulo, Autor autor, BigDecimal preco) {
        List<Livro> livros = findByTituloAndAutorAndPreco(titulo, autor, preco);
        if (livros == null || livros.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado para o titulo, autor e preco informados.");
        }
        return livros;
    }

    boolean existsByAutor(Autor autor);

}
