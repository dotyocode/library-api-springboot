package io.github.dotyocode.libraryApi.security;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import io.github.dotyocode.libraryApi.services.client.ClientService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientService clientService;
    private final ClientSettings clientSettings;
    private final TokenSettings tokenSettings;

    @Override
    public void save(RegisteredClient registeredClient) {
        // TODO: Implementar salvamento
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public RegisteredClient findById(String id) {
        // TODO: Implementar busca por ID
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client = clientService.getClientByClientId(clientId);

        if (client == null) {
            return null;
        }

        return RegisteredClient
                .withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret()) // Já está criptografado no banco
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope())
                .clientAuthenticationMethods(methods -> {
                    methods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
                    methods.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
                })
                .authorizationGrantTypes(grantTypes -> {
                    grantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    grantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS); // Adicione esta linha
                    grantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
                })
                .tokenSettings(tokenSettings)
                .clientSettings(clientSettings)
                .build();
    }
}
