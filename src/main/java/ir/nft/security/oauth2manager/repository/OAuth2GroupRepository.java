package ir.nft.security.oauth2manager.repository;

import ir.nft.security.oauth2manager.entity.OAuth2Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2GroupRepository extends JpaRepository<OAuth2Group, Integer> {
  boolean existsByTitle(String title);
}
