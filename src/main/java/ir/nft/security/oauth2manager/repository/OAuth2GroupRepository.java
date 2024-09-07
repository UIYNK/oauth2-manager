package ir.nft.security.oauth2manager.repository;

import ir.nft.security.oauth2manager.entity.OAuth2Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OAuth2GroupRepository extends JpaRepository<OAuth2Group, UUID> {
  boolean existsByTitle(String title);
}
