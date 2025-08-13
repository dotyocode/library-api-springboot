package io.github.dotyocode.libraryApi.services.autor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.dotyocode.libraryApi.common.exceptions.OperacaoNaoPermitidaException;
import io.github.dotyocode.libraryApi.model.entities.autor.Autor;
import io.github.dotyocode.libraryApi.repository.autor.AutorRepository;
import io.github.dotyocode.libraryApi.repository.livro.LivroRepository;
import io.github.dotyocode.libraryApi.validators.autor.AutorValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public Autor obterPorId(UUID id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }

    public void deletar(Autor autor) {
        if (possuiLivros(autor)) {
            throw new OperacaoNaoPermitidaException(
                    "Não é permitido excluir um Autor que possui livros vinculados");
        }

        if (!autorRepository.existsById(autor.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }
        autorRepository.deleteById(autor.getId());
    }

    public List<Autor> pesquisarAutores(String nome, String nascionalidade) {
        if (nome == null && nascionalidade == null) {
            return autorRepository.findAll();
        }

        if (nome != null && nascionalidade != null) {
            return autorRepository.findByNomeOrNascionalidade(nome, nascionalidade);
        }

        if (nome != null) {
            return autorRepository.findByNomeContainingIgnoreCase(nome);
        }

        return autorRepository.findByNascionalidadeContainingIgnoreCase(nascionalidade);
    }

    public List<Autor> pesquisaByExample(String nome, String nascionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNascionalidade(nascionalidade);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        var autorExample = Example.of(autor, matcher);
        return autorRepository.findAll(autorExample);
    }

    public boolean existsById(UUID id) {
        return autorRepository.existsById(id);
    }

    public Autor atualizar(UUID id, Autor autor) {
        autorValidator.validar(autor);

        if (!autorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }

        Autor autorExistente = autorRepository.findById(id).get();

        if (autor.getNome() != null) {
            autorExistente.setNome(autor.getNome());
        }
        if (autor.getDataNascimento() != null) {
            autorExistente.setDataNascimento(autor.getDataNascimento());
        }
        if (autor.getNascionalidade() != null) {
            autorExistente.setNascionalidade(autor.getNascionalidade());
        }

        return autorRepository.save(autorExistente);
    }

    public boolean possuiLivros(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }

}
