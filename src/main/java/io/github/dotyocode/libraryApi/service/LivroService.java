package io.github.dotyocode.libraryApi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.dotyocode.libraryApi.enums.GenerosLivros;
import io.github.dotyocode.libraryApi.model.Livro;
import io.github.dotyocode.libraryApi.repository.LivroRepository;
import io.github.dotyocode.libraryApi.repository.LivroSpecs;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro salvaLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public List<Livro> obterLivros(String isbn, String autor, Integer anoPublicacao, GenerosLivros genero) {
        Specification<Livro> specs = Specification.allOf(
                LivroSpecs.isbnEqual(isbn),
                LivroSpecs.autorLike(autor),
                LivroSpecs.anoPublicacaoEqual(anoPublicacao),
                LivroSpecs.generoEqual(genero));

        return livroRepository.findAll(specs);
    }

}
