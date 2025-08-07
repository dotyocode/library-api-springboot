package io.github.dotyocode.libraryApi.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.dotyocode.libraryApi.model.Autor;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void testeSalvarAutor() {
        Autor autor = new Autor();
        autor.setNome("Maria da Silva");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1990, 1, 1));
        autorRepository.save(autor);
    }

    @Test
    public void testeAtualizarAutor() {
        UUID autorId = UUID.fromString("7f6184c3-ae99-4443-bc0e-9156699fc7af");

        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado para atualização"));

        autor.setNome("Dotyo da Silva");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1990, 1, 1));
        autorRepository.save(autor);

        Autor autorAtualizado = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado após atualização"));

        org.junit.jupiter.api.Assertions.assertEquals("Dotyo da Silva", autorAtualizado.getNome());
        org.junit.jupiter.api.Assertions.assertEquals("Brasileira", autorAtualizado.getNascionalidade());
        org.junit.jupiter.api.Assertions.assertEquals(LocalDate.of(1990, 1, 1), autorAtualizado.getDataNascimento());
    }

}
