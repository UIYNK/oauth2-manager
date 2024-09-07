package ir.nft.security.oauth2manager.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class OAuth2Entity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  protected OAuth2Entity() {}
}
