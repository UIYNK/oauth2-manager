package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ClientDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2ResourceServerDTO;
import ir.nft.security.oauth2manager.entity.OAuth2Client;
import ir.nft.security.oauth2manager.entity.OAuth2ResourceServer;
import ir.nft.security.oauth2manager.exception.DomainException;
import ir.nft.security.oauth2manager.repository.OAuth2RegisteredClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuth2RegisteredClientService {
  private final OAuth2RegisteredClientRepository registeredClientRepository;
  private final PasswordEncoder passwordEncoder;

  public boolean registeredClientExist(String clientId) {
    return registeredClientRepository.existsByClientId(clientId);
  }

  @Transactional
  public OAuth2ResourceServerDTO createResourceServer(OAuth2ResourceServerDTO resourceServerDTO) {
    if (registeredClientExist(resourceServerDTO.getClientId())) {
      throw new DomainException(
          "Already Registered Resource Server",
          "A resource server with this client ID has already been registered",
          HttpStatus.CONFLICT);
    }
    OAuth2ResourceServer resourceServer =
        new OAuth2ResourceServer(
            resourceServerDTO.getClientId(),
            passwordEncoder.encode(resourceServerDTO.getClientSecret()));
    resourceServer = registeredClientRepository.save(resourceServer);

    return OAuth2ResourceServerDTO.builder()
        .id(resourceServer.getId().toString())
        .clientId(resourceServer.getClientId())
        .build();
  }

  @Transactional
  public OAuth2ClientDTO createClient(OAuth2ClientDTO clientDTO) {
    if (registeredClientExist(clientDTO.getClientId())) {
      throw new DomainException(
          "Already Registered Client",
          "A client with this client ID has already been registered",
          HttpStatus.CONFLICT);
    }
    OAuth2Client client =
        new OAuth2Client(
            clientDTO.getClientId(),
            passwordEncoder.encode(clientDTO.getClientSecret()),
            clientDTO.getRedirectUri());
    client = registeredClientRepository.save(client);

    return OAuth2ClientDTO.builder()
        .id(client.getId().toString())
        .clientId(client.getClientId())
        .redirectUri(client.getRedirectUri())
        .build();
  }
}
