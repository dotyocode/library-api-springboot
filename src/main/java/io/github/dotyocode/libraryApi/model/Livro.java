package io.github.dotyocode.libraryApi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.dotyocode.libraryApi.enums.GenerosLivros;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "livro", schema = "public")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "genero", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private GenerosLivros genero;

    @Column(name = "preco", precision = 18, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne(
            // cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

}
