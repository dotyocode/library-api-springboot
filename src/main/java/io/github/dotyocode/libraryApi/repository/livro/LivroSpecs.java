package io.github.dotyocode.libraryApi.repository.livro;

import org.springframework.data.jpa.domain.Specification;

import io.github.dotyocode.libraryApi.enums.livros.GenerosLivros;
import io.github.dotyocode.libraryApi.model.entities.livros.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn) {
        return (root, query, criteriaBuilder) -> isbn == null ? null : criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> autorLike(String autor) {
        return (root, query, criteriaBuilder) -> {
            if (autor == null) {
                return null;
            }
            Join<Object, Object> joinAutor = root.join("autor", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.upper(joinAutor.get("nome")), "%" + autor.toUpperCase() + "%");
        };
    }

    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao) {
        return (root, query, criteriaBuilder) -> anoPublicacao == null ? null
                : criteriaBuilder.equal(
                        criteriaBuilder.function("to_char", String.class, root.get("dataPublicacao"),
                                criteriaBuilder.literal("YYYY")),
                        anoPublicacao);
    }

    public static Specification<Livro> generoEqual(GenerosLivros genero) {
        return (root, query, criteriaBuilder) -> genero == null ? null
                : criteriaBuilder.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> tituloLike(String titulo) {
        return (root, query, criteriaBuilder) -> titulo == null ? null
                : criteriaBuilder
                        .like(criteriaBuilder.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }
}
