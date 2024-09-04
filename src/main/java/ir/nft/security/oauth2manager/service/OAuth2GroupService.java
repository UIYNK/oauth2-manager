package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.dto.domain.OAuth2GroupDTO;
import ir.nft.security.oauth2manager.entity.OAuth2Activity;
import ir.nft.security.oauth2manager.entity.OAuth2Group;
import ir.nft.security.oauth2manager.exception.DomainException;
import ir.nft.security.oauth2manager.mapper.OAuth2EntityDTOMapper;
import ir.nft.security.oauth2manager.repository.OAuth2ActivityRepository;
import ir.nft.security.oauth2manager.repository.OAuth2GroupRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OAuth2GroupService {
  private final OAuth2GroupRepository groupRepository;
  private final OAuth2ActivityRepository activityRepository;
  private final OAuth2EntityDTOMapper oAuth2EntityDTOMapper;

  public OAuth2GroupService(
      OAuth2GroupRepository groupRepository,
      OAuth2ActivityRepository activityRepository,
      OAuth2EntityDTOMapper oAuth2EntityDTOMapper) {
    this.groupRepository = groupRepository;
    this.activityRepository = activityRepository;
    this.oAuth2EntityDTOMapper = oAuth2EntityDTOMapper;
  }

  public OAuth2Group loadGroupByIdOrNull(int id) {
    return groupRepository.findById(id).orElse(null);
  }

  public OAuth2Group loadGroupByIdOrThrow(int id) {
    return groupRepository
        .findById(id)
        .orElseThrow(
            () ->
                new DomainException(
                    "Group not found",
                    "No Group with the requested ID was found",
                    HttpStatus.BAD_REQUEST));
  }

  public OAuth2GroupDTO getGroupDTOById(int id) {
    return oAuth2EntityDTOMapper.mapToOAuth2GroupDTO(loadGroupByIdOrThrow(id));
  }

  public boolean groupExists(int id) {
    return groupRepository.existsById(id);
  }

  public boolean groupExists(String title) {
    return groupRepository.existsByTitle(title);
  }

  public OAuth2Group saveGroup(@Valid OAuth2Group group) {
    return groupRepository.save(group);
  }

  @Transactional
  public OAuth2GroupDTO createGroup(OAuth2GroupDTO groupDTO) {
    if (groupExists(groupDTO.getTitle())) {
      throw new DomainException(
          "Already Registered Group Title",
          "A group with this title has already been registered",
          HttpStatus.CONFLICT);
    }

    Set<Integer> subGroupsIdSet =
        null != groupDTO.getSubGroupsIdSet() ? groupDTO.getSubGroupsIdSet() : new HashSet<>();
    Set<Integer> activitiesIdSet =
        null != groupDTO.getActivitiesIdSet() ? groupDTO.getActivitiesIdSet() : new HashSet<>();

    ServiceUtils.BatchLoadByIdSetResult<OAuth2Group> subGroupsBatchLoadResult =
        ServiceUtils.batchLoadByIdSet(subGroupsIdSet, groupRepository);
    ServiceUtils.BatchLoadByIdSetResult<OAuth2Activity> activitiesBatchLoadResult =
        ServiceUtils.batchLoadByIdSet(activitiesIdSet, activityRepository);

    if (!subGroupsBatchLoadResult.getNotFoundIds().isEmpty()
        || !activitiesBatchLoadResult.getNotFoundIds().isEmpty()) {
      DomainException exception =
          new DomainException(
              "Not-Found Entities",
              "Some of the IDs in the subGroupsIds or activitiesIds do not correspond to a registered entity",
              HttpStatus.BAD_REQUEST);
      if (!subGroupsBatchLoadResult.getNotFoundIds().isEmpty()) {
        exception.addProperty("notFoundSubGroupIds", subGroupsBatchLoadResult.getNotFoundIds());
      }
      if (!activitiesBatchLoadResult.getNotFoundIds().isEmpty()) {
        exception.addProperty("notFoundActivityIds", activitiesBatchLoadResult.getNotFoundIds());
      }

      throw exception;
    }

    OAuth2Group newGroup =
        OAuth2Group.builder()
            .title(groupDTO.getTitle())
            .subGroups(subGroupsBatchLoadResult.getFoundEntities())
            .activities(activitiesBatchLoadResult.getFoundEntities())
            .build();
    newGroup = saveGroup(newGroup);
    return oAuth2EntityDTOMapper.mapToOAuth2GroupDTO(newGroup);
  }
}
