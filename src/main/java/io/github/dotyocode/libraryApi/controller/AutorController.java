package io.github.dotyocode.libraryApi.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.dotyocode.libraryApi.dto.AutorDto;
import io.github.dotyocode.libraryApi.dto.ErroResposta;
import io.github.dotyocode.libraryApi.service.AutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDto autor) {
        try {
            var autorEntidade = autor.mapearParaAutor();
            var autorSalvo = autorService.salvar(autorEntidade);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(autorSalvo.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            var erroDto = ErroResposta.conflitos(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDto> obterDetalheAutor(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        var autor = autorService.obterPorId(idAutor);
        return ResponseEntity.ok(new AutorDto(autor.getId(), autor.getNome(), autor.getDataNascimento(),
                autor.getNascionalidade()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletarAutor(@PathVariable("id") String id) {
        try {
            var idAutor = UUID.fromString(id);
            var autor = autorService.obterPorId(idAutor);
            autorService.deletar(autor);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            var erroDto = ErroResposta.conflitos(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDto>> pesquisarAutores(@RequestParam(required = false, value = "nome") String nome,
            @RequestParam(required = false, value = "nascionalidade") String nascionalidade) {
        var autores = autorService.pesquisarAutores(nome, nascionalidade);
        return ResponseEntity.ok(autores.stream()
                .map(autor -> new AutorDto(autor.getId(), autor.getNome(), autor.getDataNascimento(),
                        autor.getNascionalidade()))
                .collect(Collectors.toList()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarAutor(@PathVariable("id") String id, @RequestBody @Valid AutorDto autor) {
        try {
            var idAutor = UUID.fromString(id);
            var autorEntidade = autor.mapearParaAutor();
            var autorAtualizado = autorService.atualizar(idAutor, autorEntidade);
            return ResponseEntity.ok(new AutorDto(autorAtualizado.getId(), autorAtualizado.getNome(),
                    autorAtualizado.getDataNascimento(), autorAtualizado.getNascionalidade()));
        } catch (Exception e) {
            var erroDto = ErroResposta.conflitos(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

}
