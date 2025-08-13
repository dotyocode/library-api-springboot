package io.github.dotyocode.libraryApi.services.livro;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.dotyocode.libraryApi.enums.livros.GenerosLivros;
import io.github.dotyocode.libraryApi.model.entities.livros.Livro;
import io.github.dotyocode.libraryApi.model.entities.usuario.Usuario;
import io.github.dotyocode.libraryApi.repository.livro.LivroRepository;
import io.github.dotyocode.libraryApi.repository.livro.LivroSpecs;
import io.github.dotyocode.libraryApi.security.SecurityService;
import io.github.dotyocode.libraryApi.validators.livro.LivroValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroValidator livroValidator;
    private final SecurityService securityService;

    public Livro salvaLivro(Livro livro) {
        livroValidator.validar(livro);
        Usuario usuario = securityService.obterUsuarioLogado();
        livro.setUsuario(usuario);
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public Page<Livro> obterLivros(String isbn, String titulo, String autor, Integer anoPublicacao,
            GenerosLivros genero,
            Integer pagina, Integer tamanhoPagina) {
        Specification<Livro> specs = Specification.allOf(
                LivroSpecs.isbnEqual(isbn),
                LivroSpecs.tituloLike(titulo),
                LivroSpecs.autorLike(autor),
                LivroSpecs.anoPublicacaoEqual(anoPublicacao),
                LivroSpecs.generoEqual(genero));

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return livroRepository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro) {
        livroValidator.livroNaoEncontrado(livro);
        livroValidator.validar(livro);
        livroRepository.save(livro);
    }

}
