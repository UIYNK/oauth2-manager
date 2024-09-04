package ir.nft.security.oauth2manager.dto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2ActivityDTO {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;

  /** A validator should be provided for this field later on */
  @NotNull(message = "The path field must be included")
  private String path;

  /** A validator should be provided for this field later on */
  @NotNull(message = "The cumulativePath field must be included")
  private String cumulativePath;

  @Nullable private UUID parentId;

  @NotNull(message = "The resourceServerClientId field must be included")
  private String resourceServerClientId;
}
