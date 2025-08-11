package io.github.dotyocode.libraryApi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import io.github.dotyocode.libraryApi.enums.GenerosLivros;
import io.github.dotyocode.libraryApi.model.Autor;
import io.github.dotyocode.libraryApi.model.Livro;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void testeSalvarLivroMockado_semAlterarBanco() {
        Autor autorMock = new Autor();
        autorMock.setId(UUID.randomUUID());
        autorMock.setNome("J.R.R. Tolkien");
        autorMock.setNascionalidade("Britânico");
        autorMock.setDataNascimento(java.time.LocalDate.of(1892, 1, 3));

        Livro livroMock = new Livro();
        livroMock.setTitulo("O Senhor dos Anéis");
        livroMock.setIsbn("978-0261102361");
        livroMock.setGenero(GenerosLivros.FICCAO);
        livroMock.setPreco(new BigDecimal("29.99"));
        livroMock.setAutor(autorMock);
        livroMock.setDataPublicacao(java.time.LocalDate.of(1954, 11, 11));

        livroMock.setId(UUID.randomUUID());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(livroMock.getId()),
                () -> Assertions.assertEquals("O Senhor dos Anéis", livroMock.getTitulo()),
                () -> Assertions.assertEquals("978-0261102361", livroMock.getIsbn()),
                () -> Assertions.assertEquals(GenerosLivros.FICCAO, livroMock.getGenero()),
                () -> Assertions.assertEquals(new BigDecimal("29.99"), livroMock.getPreco()),
                () -> Assertions.assertEquals(autorMock, livroMock.getAutor()));
    }

    @Test
    public void testeAtualizarLivroMockado_semAlterarBanco() {
        Autor autorMock = new Autor();
        autorMock.setId(UUID.randomUUID());
        autorMock.setNome("J.R.R. Tolkien");
        autorMock.setNascionalidade("Britânico");
        autorMock.setDataNascimento(java.time.LocalDate.of(1892, 1, 3));

        Livro livroMock = new Livro();
        livroMock.setId(UUID.randomUUID());
        livroMock.setTitulo("O Senhor dos Anéis");
        livroMock.setIsbn("978-0261102361");
        livroMock.setGenero(GenerosLivros.FICCAO);
        livroMock.setPreco(new BigDecimal("29.99"));
        livroMock.setAutor(autorMock);
        livroMock.setDataPublicacao(java.time.LocalDate.of(1954, 11, 11));

        livroMock.setTitulo("O Senhor dos Cabritos");
        livroMock.setIsbn("978-0261102361");
        livroMock.setGenero(GenerosLivros.FICCAO);
        livroMock.setPreco(new BigDecimal("49.99"));
        livroMock.setAutor(autorMock);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(livroMock.getId()),
                () -> Assertions.assertEquals("O Senhor dos Cabritos", livroMock.getTitulo()),
                () -> Assertions.assertEquals("978-0261102361", livroMock.getIsbn()),
                () -> Assertions.assertEquals(GenerosLivros.FICCAO, livroMock.getGenero()),
                () -> Assertions.assertEquals(new BigDecimal("49.99"), livroMock.getPreco()));
    }

    @Test
    public void testeListarLivros() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
        Assertions.assertNotNull(livros);
        Assertions.assertFalse(livros.isEmpty());
    }

    @Test
    public void testeListarLivrosPorAutor() {
        UUID autorId = UUID.fromString("90c72f45-aabe-480f-a160-2424361decda");
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        List<Livro> livros = livroRepository.findByAutor(autor);

        livros.forEach(System.out::println);
        Assertions.assertNotNull(livros);
        Assertions.assertFalse(livros.isEmpty());
    }

    @Test
    public void testeCountLivros() {
        long count = livroRepository.count();
        System.out.println("Total de livros: " + count);
        Assertions.assertTrue(count > 0);
    }

    @Test
    @Transactional
    @Rollback
    public void testeDeletarLivroExistente() {
        List<Livro> livros = livroRepository.findAll();
        Assertions.assertFalse(livros.isEmpty(), "Deve haver pelo menos um livro no banco para testar.");

        Livro livroParaDeletar = livros.get(0);
        UUID livroId = livroParaDeletar.getId();

        Assertions.assertTrue(livroRepository.existsById(livroId), "O livro deveria existir antes da deleção.");

        livroRepository.deleteById(livroId);

        Assertions.assertFalse(livroRepository.existsById(livroId), "O livro não deveria existir após a deleção.");
    }

    @Test
    public void atualizarAutorDoLivroMockado_semAlterarBanco() {
        Autor autorMock = new Autor();
        autorMock.setId(UUID.randomUUID());
        autorMock.setNome("Autor Mockado");
        autorMock.setNascionalidade("Brasileira");
        autorMock.setDataNascimento(LocalDate.of(1980, 5, 20));

        Livro livroMock = new Livro();
        livroMock.setId(UUID.randomUUID());
        livroMock.setTitulo("Livro Mockado");
        livroMock.setIsbn("123-4567890123");
        livroMock.setGenero(GenerosLivros.FICCAO);
        livroMock.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livroMock.setPreco(new BigDecimal("39.90"));
        livroMock.setAutor(null);

        livroMock.setAutor(autorMock);

        Assertions.assertNotNull(livroMock.getAutor());
        Assertions.assertEquals(autorMock, livroMock.getAutor());
        Assertions.assertEquals("Autor Mockado", livroMock.getAutor().getNome());
        Assertions.assertEquals("Brasileira", livroMock.getAutor().getNascionalidade());
        Assertions.assertEquals(LocalDate.of(1980, 5, 20), livroMock.getAutor().getDataNascimento());
    }
}