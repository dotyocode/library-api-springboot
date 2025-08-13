package io.github.dotyocode.libraryApi.domain.livro.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.domain.livro.model.Livro;
import io.github.dotyocode.libraryApi.domain.livro.repository.LivroRepository;
import io.github.dotyocode.libraryApi.shared.exceptions.CampoInvalidoException;
import io.github.dotyocode.libraryApi.shared.exceptions.RegistroDuplicadoException;
import io.github.dotyocode.libraryApi.shared.exceptions.RegistroNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository livroRepository;
    private static final int ANO_EXIGENCIA_PRECO = 2020;

    public void validar(Livro livro) {
        if (isbnJaExiste(livro)) {
            throw new RegistroDuplicadoException("ISBN já cadastrado");
        }

        if (isPrecoObrigatorioNulo(livro)) {
            throw new CampoInvalidoException("preco", "Preço é obrigatório");
        }
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean isbnJaExiste(Livro livro) {
        Optional<Livro> livroEncontrado = livroRepository.findByIsbn(livro.getIsbn());

        if (livro.getId() == null) {
            return livroEncontrado.isPresent();
        }

        return livroEncontrado.map(Livro::getId).stream().anyMatch(id -> !id.equals(livro.getId()));

    }

    public void livroNaoEncontrado(Livro livro) {
        if (livro.getId() != null) {
            livroRepository.findById(livro.getId())
                    .orElseThrow(() -> new RegistroNaoEncontradoException("Livro não encontrado"));
        }
    }
}
