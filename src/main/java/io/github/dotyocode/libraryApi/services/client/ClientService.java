package io.github.dotyocode.libraryApi.services.client;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.dotyocode.libraryApi.model.entities.client.Client;
import io.github.dotyocode.libraryApi.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public Client createClient(Client client) {
        var senhaCriptografada = passwordEncoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCriptografada);
        return clientRepository.save(client);
    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }

}
