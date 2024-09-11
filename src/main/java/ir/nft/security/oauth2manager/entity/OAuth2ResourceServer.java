package ir.nft.security.oauth2manager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("RESOURCE_SERVER")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OAuth2ResourceServer extends OAuth2RegisteredClient {
  public OAuth2ResourceServer(String clientId, String clientSecret) {
    super(clientId, clientSecret);
  }
}
