package ir.nft.security.oauth2manager.dto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.Password;
import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2ResourceServerDTO {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;

  @UserName(message = "Client IDs must follow this pattern: " + ValidationPolicy.USERNAME_REGEX)
  @NotNull(message = "The clientId field must be included")
  private String clientId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Password(message = "Client secrets must follow this pattern: " + ValidationPolicy.PASSWORD_REGEX)
  @NotNull(message = "The clientSecret field must be included")
  private String clientSecret;
}
