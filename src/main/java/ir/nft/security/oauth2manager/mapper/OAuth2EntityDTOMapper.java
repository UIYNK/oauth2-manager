package ir.nft.security.oauth2manager.mapper;

import ir.nft.security.oauth2manager.dto.domain.*;
import ir.nft.security.oauth2manager.entity.*;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OAuth2EntityDTOMapper {

  public OAuth2UserDTO mapToOAuth2UserDTO(OAuth2User oAuth2User) {

    return OAuth2UserDTO.builder()
        .id(oAuth2User.getId())
        .firstName(oAuth2User.getFirstName())
        .lastName(oAuth2User.getLastName())
        .username(oAuth2User.getUsername())
        .groupsIdSet(mapToIdSet(oAuth2User.getGroups()))
        .build();
  }

  public OAuth2GroupDTO mapToOAuth2GroupDTO(OAuth2Group oAuth2Group) {

    return OAuth2GroupDTO.builder()
        .title(oAuth2Group.getTitle())
        .id(oAuth2Group.getId())
        .subGroupsIdSet(mapToIdSet(oAuth2Group.getSubGroups()))
        .activitiesIdSet(mapToIdSet(oAuth2Group.getActivities()))
        .build();
  }

  public OAuth2ActivityDTO mapToOAuth2ActivityDTO(OAuth2Activity oAuth2Activity) {
    return OAuth2ActivityDTO.builder()
        .id(oAuth2Activity.getId())
        .path(oAuth2Activity.getPath())
        .cumulativePath(oAuth2Activity.getCumulativePath())
        .parentId(null != oAuth2Activity.getParent() ? oAuth2Activity.getParent().getId() : null)
        .resourceServerClientId(oAuth2Activity.getResourceServerClientId())
        .build();
  }

  public OAuth2ResourceServer mapToOAuth2ResourceServer(
      OAuth2ResourceServerDTO oAuth2ResourceServerDTO) {
    return new OAuth2ResourceServer(
        oAuth2ResourceServerDTO.getClientId(), oAuth2ResourceServerDTO.getClientSecret());
  }

  public OAuth2Client mapToOAuth2Client(OAuth2ClientDTO oAuth2ClientDTO) {
    return new OAuth2Client(
        oAuth2ClientDTO.getClientId(),
        oAuth2ClientDTO.getClientSecret(),
        oAuth2ClientDTO.getRedirectUri());
  }

  public OAuth2ResourceServerDTO mapToOAuth2ResourceServerDTO(
      OAuth2ResourceServer oAuth2ResourceServer) {
    return OAuth2ResourceServerDTO.builder()
        .id(oAuth2ResourceServer.getId())
        .clientId(oAuth2ResourceServer.getClientId())
        .build();
  }

  public OAuth2ClientDTO mapToOAuth2ClientDTO(OAuth2Client oAuth2Client) {
    return OAuth2ClientDTO.builder()
        .id(oAuth2Client.getId())
        .clientId(oAuth2Client.getClientId())
        .redirectUri(oAuth2Client.getRedirectUri())
        .build();
  }

  public <T extends OAuth2Entity> Set<UUID> mapToIdSet(Set<T> entitySet) {
    Assert.notNull(
        entitySet,
        "An entitySet must not be null. Provide an empty set if it is intended to not contain any entities");

    Set<UUID> idSet;
    idSet = entitySet.stream().map(entity -> entity.getId()).collect(Collectors.toSet());

    return idSet;
  }
}
