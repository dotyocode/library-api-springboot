package io.github.dotyocode.libraryApi.model.entities.client;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false, length = 200)
    private String clientId;

    @Column(name = "client_secret", nullable = false, length = 400)
    private String clientSecret;

    @Column(name = "redirect_uri", nullable = false, length = 200)
    private String redirectUri;

    @Column(name = "scope", nullable = true, length = 50)
    private String scope;

}
