package ir.nft.security.oauth2manager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "oauth2_resource_server")
public class OAuth2ResourceServer extends OAuth2RegisteredClient {
  public OAuth2ResourceServer(String clientId, String clientSecret) {
    super(clientId, clientSecret);
  }
}
