package io.github.dotyocode.libraryApi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dotyocode.libraryApi.enums.GenerosLivros;
import io.github.dotyocode.libraryApi.model.Autor;
import io.github.dotyocode.libraryApi.model.Livro;
import io.github.dotyocode.libraryApi.repository.AutorRepository;
import io.github.dotyocode.libraryApi.repository.LivroRepository;
import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void atualizandoSemAtualizar() {
        Autor autor = autorRepository.findById(UUID.fromString("90c72f45-aabe-480f-a160-2424361decda"))
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        autor.setNome("Autor 3");
    }

    @Transactional
    public void realizarTransacao() {
        Autor autor = new Autor();
        autor.setNome("Autor 2");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.now());
        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo("Livro 1");
        livro.setIsbn("1234567890");
        livro.setGenero(GenerosLivros.FICCAO);
        livro.setPreco(new BigDecimal("10.00"));
        livro.setDataPublicacao(LocalDate.now());
        livro.setAutor(autor);
        livroRepository.save(livro);

        if (autor.getNome().equals("Autor 2")) {
            throw new RuntimeException("Roolback");
        }

    }

}
