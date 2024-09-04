package ir.nft.security.oauth2manager.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.nft.security.oauth2manager.entity.OAuth2Activity;
import ir.nft.security.oauth2manager.entity.OAuth2Group;
import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.GroupTitle;
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
public class OAuth2GroupDTO {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;

  @NotNull(message = "The title of the group must be included")
  @GroupTitle(
      message = "Group titles must follow this pattern: " + ValidationPolicy.GROUP_TITLE_REGEX)
  private String title;

  @Nullable private Set<UUID> subGroupsIdSet;
  @Nullable private Set<UUID> activitiesIdSet;

  @JsonIgnore @Nullable private Set<OAuth2Group> subGroups;
  @JsonIgnore @Nullable private Set<OAuth2Activity> activities;
}
