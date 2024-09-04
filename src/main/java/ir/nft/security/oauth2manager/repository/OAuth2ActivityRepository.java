package ir.nft.security.oauth2manager.repository;

import ir.nft.security.oauth2manager.entity.OAuth2Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2ActivityRepository extends JpaRepository<OAuth2Activity, Integer> {}
