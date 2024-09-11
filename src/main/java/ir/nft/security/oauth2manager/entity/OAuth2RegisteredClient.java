package ir.nft.security.oauth2manager.entity;

import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "oauth2_registered_client")
@DiscriminatorColumn(name = "registered_client_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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
