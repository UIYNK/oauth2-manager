package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ClientDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2ResourceServerDTO;
import ir.nft.security.oauth2manager.entity.OAuth2Client;
import ir.nft.security.oauth2manager.entity.OAuth2ResourceServer;
import ir.nft.security.oauth2manager.exception.DomainException;
import ir.nft.security.oauth2manager.repository.OAuth2ClientRepository;
import ir.nft.security.oauth2manager.repository.OAuth2ResourceServerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OAuth2RegisteredClientService {
  private final OAuth2ClientRepository clientRepository;
  private final OAuth2ResourceServerRepository resourceServerRepository;
  private final PasswordEncoder passwordEncoder;

  public OAuth2RegisteredClientService(
      OAuth2ClientRepository clientRepository,
      OAuth2ResourceServerRepository resourceServerRepository,
      PasswordEncoder passwordEncoder) {
    this.clientRepository = clientRepository;
    this.resourceServerRepository = resourceServerRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean resourceServerExist(String clientId) {
    return resourceServerRepository.existsByClientId(clientId);
  }

  public boolean clientExist(String clientId) {
    return clientRepository.existsByClientId(clientId);
  }

  @Transactional
  public OAuth2ResourceServerDTO createResourceServer(OAuth2ResourceServerDTO resourceServerDTO) {
    if (resourceServerExist(resourceServerDTO.getClientId())) {
      throw new DomainException(
          "Already Registered Resource Server",
          "A resource server with this client ID has already been registered",
          HttpStatus.CONFLICT);
    }
    OAuth2ResourceServer resourceServer = new OAuth2ResourceServer();
    resourceServer.setClientId(resourceServerDTO.getClientId());
    resourceServer.setClientSecret(passwordEncoder.encode(resourceServerDTO.getClientSecret()));
    resourceServer = resourceServerRepository.save(resourceServer);

    return OAuth2ResourceServerDTO.builder()
        .id(resourceServer.getId())
        .clientId(resourceServer.getClientId())
        .build();
  }

  @Transactional
  public OAuth2ClientDTO createClient(OAuth2ClientDTO clientDTO) {
    if (clientExist(clientDTO.getClientId())) {
      throw new DomainException(
          "Already Registered Client",
          "A client with this client ID has already been registered",
          HttpStatus.CONFLICT);
    }
    OAuth2Client client = new OAuth2Client();
    client.setClientId(clientDTO.getClientId());
    client.setClientSecret(passwordEncoder.encode(clientDTO.getClientSecret()));
    client.setRedirectUri(clientDTO.getRedirectUri());
    client = clientRepository.save(client);

    return OAuth2ClientDTO.builder()
        .id(client.getId())
        .clientId(client.getClientId())
        .redirectUri(client.getRedirectUri())
        .build();
  }
}
