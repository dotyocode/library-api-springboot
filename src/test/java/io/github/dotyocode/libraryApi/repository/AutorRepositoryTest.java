package io.github.dotyocode.libraryApi.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.dotyocode.libraryApi.domain.autor.model.Autor;
import io.github.dotyocode.libraryApi.domain.autor.repository.AutorRepository;

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

        Assertions.assertEquals("Maria da Silva", autor.getNome());
        Assertions.assertEquals("Brasileira", autor.getNascionalidade());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), autor.getDataNascimento());
    }

    @Test
    public void testeAtualizarAutorMockado_semAlterarBanco() {
        Autor autorMock = new Autor();
        UUID autorId = UUID.randomUUID();
        autorMock.setId(autorId);
        autorMock.setNome("Nome Original");
        autorMock.setNascionalidade("Portuguesa");
        autorMock.setDataNascimento(LocalDate.of(1985, 2, 15));

        autorMock.setNome("Dotyo da Silva");
        autorMock.setNascionalidade("Brasileira");
        autorMock.setDataNascimento(LocalDate.of(1990, 1, 1));

        Assertions.assertEquals("Dotyo da Silva", autorMock.getNome());
        Assertions.assertEquals("Brasileira", autorMock.getNascionalidade());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), autorMock.getDataNascimento());
    }

    @Test
    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
        Assertions.assertNotNull(autores);
        Assertions.assertFalse(autores.isEmpty());
    }

    @Test
    public void countAutores() {
        long count = autorRepository.count();
        System.out.println("Total de autores: " + count);
        Assertions.assertTrue(count > 0);
    }

    @Test
    public void testeDeletarAutorMockado_semAlterarBanco() {
        Autor autorMock = new Autor();
        autorMock.setId(UUID.randomUUID());
        autorMock.setNome("Autor Mockado");
        autorMock.setNascionalidade("Brasileira");
        autorMock.setDataNascimento(LocalDate.of(1980, 5, 20));

        List<Autor> autoresMockados = new ArrayList<>();
        autoresMockados.add(autorMock);

        Assertions.assertTrue(
                autoresMockados.contains(autorMock),
                "O autor mockado deveria estar presente antes da deleção.");

        autoresMockados.remove(autorMock);

        Assertions.assertFalse(
                autoresMockados.contains(autorMock),
                "O autor mockado não deveria estar presente após a deleção simulada.");
    }

}