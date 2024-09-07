package ir.nft.security.oauth2manager.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.nft.security.oauth2manager.entity.OAuth2Group;
import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.EnglishName;
import ir.nft.security.oauth2manager.validation.annotation.Password;
import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2UserDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;

  @UserName(message = "Usernames must follow this pattern: " + ValidationPolicy.USERNAME_REGEX)
  @NotNull(message = "The username field must be included")
  private String username;

  @EnglishName(
      message = "English names must follow this pattern: " + ValidationPolicy.ENGLISH_NAME_REGEX)
  @NotNull(message = "The firstName field must be included")
  private String firstName;

  @EnglishName(
      message = "English names must follow this pattern: " + ValidationPolicy.ENGLISH_NAME_REGEX)
  @NotNull(message = "The lastName field must be included")
  private String lastName;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Password(message = "Passwords must follow this pattern: " + ValidationPolicy.PASSWORD_REGEX)
  @NotNull(message = "The password field must be included")
  private String password;

  @Nullable private Set<UUID> groupsIdSet;
  @JsonIgnore @Nullable private Set<OAuth2Group> groups;
}
