package io.github.dotyocode.libraryApi.service;

import org.springframework.stereotype.Service;

import io.github.dotyocode.libraryApi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

}
