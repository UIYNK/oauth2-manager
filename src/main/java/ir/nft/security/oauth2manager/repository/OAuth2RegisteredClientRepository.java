package ir.nft.security.oauth2manager.repository;

import ir.nft.security.oauth2manager.entity.OAuth2RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface OAuth2RegisteredClientRepository<T extends OAuth2RegisteredClient>
    extends JpaRepository<T, Integer> {
  Optional<T> findByClientId(String clientId);

  boolean existsByClientId(String clientId);
}
