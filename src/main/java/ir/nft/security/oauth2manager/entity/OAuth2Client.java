package ir.nft.security.oauth2manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "oauth2_client")
public class OAuth2Client extends OAuth2RegisteredClient {

  @NotNull
  @URL
  @Column(nullable = false)
  private String redirectUri;

  public OAuth2Client(String clientId, String clientSecret, String redirectUri) {
    super(clientId, clientSecret);
    this.redirectUri = redirectUri;
  }
}
