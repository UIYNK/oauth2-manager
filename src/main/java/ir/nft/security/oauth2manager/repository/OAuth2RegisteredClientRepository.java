package ir.nft.security.oauth2manager.repository;

import ir.nft.security.oauth2manager.entity.OAuth2RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OAuth2RegisteredClientRepository
    extends JpaRepository<OAuth2RegisteredClient, UUID> {
  Optional<OAuth2RegisteredClient> findByClientId(String clientId);

  boolean existsByClientId(String clientId);
}
