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
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
public abstract class OAuth2RegisteredClient implements OAuth2Entity {
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "oauth2_registered_client_seq_gen")
  @SequenceGenerator(
      name = "oauth2_registered_client_seq_gen",
      sequenceName = "oauth2_registered_client_seq",
      allocationSize = 1)
  @EqualsAndHashCode.Exclude
  private Integer id;

  @NotNull
  @UserName
  @Column(nullable = false)
  private String clientId;

  @NotNull
  @Column(nullable = false)
  private String clientSecret;

  public OAuth2RegisteredClient(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }
}
