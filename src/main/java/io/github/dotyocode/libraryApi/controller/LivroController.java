package io.github.dotyocode.libraryApi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.dotyocode.libraryApi.dto.CadastroLivroDto;
import io.github.dotyocode.libraryApi.dto.PesquisaLivroDto;
import io.github.dotyocode.libraryApi.enums.GenerosLivros;
import io.github.dotyocode.libraryApi.mappers.LivroMapper;
import io.github.dotyocode.libraryApi.model.Livro;
import io.github.dotyocode.libraryApi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    @Autowired
    LivroService livroService;

    @Autowired
    LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Object> cadastrarLivro(@RequestBody @Valid CadastroLivroDto dto) {
        Livro livro = livroMapper.toEntity(dto);
        livroService.salvaLivro(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PesquisaLivroDto> obterDetalhes(
            @PathVariable("id") String id) {
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = livroMapper.toPesquisaDto(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    livroService.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PesquisaLivroDto>> pesquisar(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "nome-autor", required = false) String nomeAutor,
            @RequestParam(value = "genero", required = false) GenerosLivros genero,
            @RequestParam(value = "ano-publicacao", required = false) Integer anoPublicacao) {

        List<Livro> resultado = livroService.obterLivros(isbn, nomeAutor, anoPublicacao, genero);
        List<PesquisaLivroDto> dtos = resultado.stream()
                .map(livroMapper::toPesquisaDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

}
