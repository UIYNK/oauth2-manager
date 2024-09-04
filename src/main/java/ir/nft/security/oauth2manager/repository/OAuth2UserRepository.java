package ir.nft.security.oauth2manager.repository;

import ir.nft.security.oauth2manager.entity.OAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuth2UserRepository extends JpaRepository<OAuth2User, Integer> {
  Optional<OAuth2User> findByUsername(String username);

  boolean existsByUsername(String username);
}
