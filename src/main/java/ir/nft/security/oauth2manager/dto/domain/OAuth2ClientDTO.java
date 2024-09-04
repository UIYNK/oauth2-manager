package ir.nft.security.oauth2manager.dto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.Password;
import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2ClientDTO {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;

  @UserName(message = "Client IDs must follow this pattern: " + ValidationPolicy.USERNAME_REGEX)
  @NotNull(message = "The clientId field must be included")
  private String clientId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Password(message = "Client secrets must follow this pattern: " + ValidationPolicy.PASSWORD_REGEX)
  @NotNull(message = "The clientSecret field must be included")
  private String clientSecret;

  @NotNull(message = "A valid URL must be provided for redirection purposes")
  @URL(message = "The redirectUri field must contain a valid URL")
  private String redirectUri;
}
