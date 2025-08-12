package io.github.dotyocode.libraryApi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.dotyocode.libraryApi.model.Autor;
import io.github.dotyocode.libraryApi.repository.AutorRepository;
import io.github.dotyocode.libraryApi.validators.AutorValidator;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;

    public AutorService(AutorRepository autorRepository, AutorValidator autorValidator) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
    }

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public Autor obterPorId(UUID id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }

    public void deletar(UUID id) {
        if (!autorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }
        autorRepository.deleteById(id);
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

}
