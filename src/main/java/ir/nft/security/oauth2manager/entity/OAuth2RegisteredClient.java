package ir.nft.security.oauth2manager.entity;

import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@MappedSuperclass
public abstract class OAuth2RegisteredClient extends OAuth2Entity {
  @NotNull
  @UserName
  @Column(nullable = false)
  private String clientId;

  @NotNull
  @Column(nullable = false)
  private String clientSecret;

  protected OAuth2RegisteredClient(String clientId, String clientSecret) {
    super();
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }
}
