package io.github.dotyocode.libraryApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dotyocode.libraryApi.dto.CadastroLivroDto;
import io.github.dotyocode.libraryApi.dto.ErroResposta;
import io.github.dotyocode.libraryApi.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> cadastrarLivro(@RequestBody @Valid CadastroLivroDto dto) {
        try {
            // var livro = livroService.cadastrarLivro(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        } catch (Exception e) {
            var erroDto = ErroResposta.conflitos(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

}
