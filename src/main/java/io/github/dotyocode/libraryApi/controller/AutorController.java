package io.github.dotyocode.libraryApi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.dotyocode.libraryApi.dto.AutorDto;
import io.github.dotyocode.libraryApi.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorDto> salvar(@RequestBody AutorDto autor) {
        var autorEntidade = autor.mapearParaAutor();
        var autorSalvo = autorService.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
