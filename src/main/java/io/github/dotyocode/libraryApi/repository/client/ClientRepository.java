package io.github.dotyocode.libraryApi.repository.client;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dotyocode.libraryApi.model.entities.client.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByClientId(String clientId);

}
