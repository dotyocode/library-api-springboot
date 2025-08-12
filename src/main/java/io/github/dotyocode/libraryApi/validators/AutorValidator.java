package io.github.dotyocode.libraryApi.validators;

import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.exceptions.RegistroDuplicadoException;
import io.github.dotyocode.libraryApi.model.Autor;
import io.github.dotyocode.libraryApi.repository.AutorRepository;

@Component
public class AutorValidator {

    private final AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor) {
        if (existeAutorComNome(autor.getNome())) {
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }

    private boolean existeAutorComNome(String nome) {
        return autorRepository.findByNome(nome).isPresent();
    }

}
