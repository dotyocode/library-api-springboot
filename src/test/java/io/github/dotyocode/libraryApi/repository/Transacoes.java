package io.github.dotyocode.libraryApi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.dotyocode.libraryApi.services.autor.TransacaoService;

@SpringBootTest
public class Transacoes {

    @Autowired
    private TransacaoService transacaoService;

    @Test
    public void testeTransacao() {
        transacaoService.realizarTransacao();
    }

    @Test
    public void transacaoEstadoManaged() {
        transacaoService.atualizandoSemAtualizar();
    }

}
