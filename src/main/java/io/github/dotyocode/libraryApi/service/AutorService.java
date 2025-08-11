package io.github.dotyocode.libraryApi.service;

import org.springframework.stereotype.Service;

import io.github.dotyocode.libraryApi.model.Autor;
import io.github.dotyocode.libraryApi.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }
}
