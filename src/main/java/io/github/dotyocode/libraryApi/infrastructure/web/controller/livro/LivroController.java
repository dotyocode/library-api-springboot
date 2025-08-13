package io.github.dotyocode.libraryApi.infrastructure.web.controller.livro;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.dotyocode.libraryApi.application.dto.CadastroLivroDto;
import io.github.dotyocode.libraryApi.application.dto.PesquisaLivroDto;
import io.github.dotyocode.libraryApi.application.mappers.LivroMapper;
import io.github.dotyocode.libraryApi.domain.livro.enums.GenerosLivros;
import io.github.dotyocode.libraryApi.domain.livro.model.Livro;
import io.github.dotyocode.libraryApi.domain.livro.service.LivroService;
import io.github.dotyocode.libraryApi.infrastructure.web.controller.generics.GenericController;
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
    public ResponseEntity<Page<PesquisaLivroDto>> pesquisar(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "nome-autor", required = false) String nomeAutor,
            @RequestParam(value = "genero", required = false) GenerosLivros genero,
            @RequestParam(value = "ano-publicacao", required = false) Integer anoPublicacao,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina) {

        Page<Livro> paginaResultado = livroService.obterLivros(isbn, titulo, nomeAutor, anoPublicacao, genero, pagina,
                tamanhoPagina);
        Page<PesquisaLivroDto> resultado = paginaResultado.map(livroMapper::toPesquisaDto);

        return ResponseEntity.ok(resultado);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody @Valid CadastroLivroDto dto) {
        Livro entidadeAuxiliar = livroMapper.toEntity(dto);
        entidadeAuxiliar.setId(UUID.fromString(id));

        livroService.atualizar(entidadeAuxiliar);
        return ResponseEntity.noContent().build();
    }

}
