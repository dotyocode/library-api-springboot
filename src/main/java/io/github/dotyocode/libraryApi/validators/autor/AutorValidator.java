package io.github.dotyocode.libraryApi.validators.autor;

import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.common.exceptions.RegistroDuplicadoException;
import io.github.dotyocode.libraryApi.model.entities.autor.Autor;
import io.github.dotyocode.libraryApi.repository.autor.AutorRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutorValidator {

    private final AutorRepository autorRepository;

    public void validar(Autor autor) {
        if (existeAutorComNome(autor.getNome())) {
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }

    private boolean existeAutorComNome(String nome) {
        return autorRepository.findByNome(nome).isPresent();
    }

    private boolean autorExiste(Autor autor) {
        return autorRepository.existsById(autor.getId());
    }

}
