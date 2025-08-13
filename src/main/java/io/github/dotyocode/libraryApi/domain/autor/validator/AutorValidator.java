package io.github.dotyocode.libraryApi.domain.autor.validator;

import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.domain.autor.model.Autor;
import io.github.dotyocode.libraryApi.domain.autor.repository.AutorRepository;
import io.github.dotyocode.libraryApi.shared.exceptions.RegistroDuplicadoException;
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
