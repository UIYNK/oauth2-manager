package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2UserDTO;
import ir.nft.security.oauth2manager.entity.OAuth2Group;
import ir.nft.security.oauth2manager.entity.OAuth2User;
import ir.nft.security.oauth2manager.exception.DomainException;
import ir.nft.security.oauth2manager.mapper.OAuth2EntityDTOMapper;
import ir.nft.security.oauth2manager.repository.OAuth2GroupRepository;
import ir.nft.security.oauth2manager.repository.OAuth2UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OAuth2UserService {
  private final OAuth2UserRepository userRepository;
  private final OAuth2EntityDTOMapper oAuth2EntityDTOMapper;
  private final PasswordEncoder passwordEncoder;
  private final OAuth2GroupRepository groupRepository;
  private final OAuth2GroupService groupService;

  public OAuth2UserService(
      OAuth2UserRepository userRepository,
      OAuth2EntityDTOMapper oAuth2EntityDTOMapper,
      PasswordEncoder passwordEncoder,
      OAuth2GroupRepository groupRepository,
      OAuth2GroupService groupService) {
    this.userRepository = userRepository;
    this.oAuth2EntityDTOMapper = oAuth2EntityDTOMapper;
    this.passwordEncoder = passwordEncoder;
    this.groupRepository = groupRepository;
    this.groupService = groupService;
  }

  public OAuth2User loadUserByUsernameOrNull(String userName) {
    return userRepository.findByUsername(userName).orElse(null);
  }

  public OAuth2User loadUserByUsernameOrThrow(String userName) {
    return userRepository
        .findByUsername(userName)
        .orElseThrow(
            () ->
                new DomainException(
                    "User not found",
                    "No User with the requested username was found",
                    HttpStatus.BAD_REQUEST));
  }

  public OAuth2User loadUserByIdOrThrow(UUID id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () ->
                new DomainException(
                    "User not found",
                    "No User with the requested ID was found",
                    HttpStatus.BAD_REQUEST));
  }

  public OAuth2UserDTO getUserDTOById(UUID id) {
    return oAuth2EntityDTOMapper.mapToOAuth2UserDTO(loadUserByIdOrThrow(id));
  }

  public boolean userExists(String userName) {
    return userRepository.existsByUsername(userName);
  }

  public boolean userExists(UUID userId) {
    return userRepository.existsById(userId);
  }

  @Transactional
  public OAuth2UserDTO createUser(OAuth2UserDTO userDTO) {
    if (userExists(userDTO.getUsername())) {
      throw new DomainException(
          "Already Registered Username",
          "A user with this username has already been registered",
          HttpStatus.CONFLICT);
    }

    Set<UUID> groupsIdSet =
        null != userDTO.getGroupsIdSet() ? userDTO.getGroupsIdSet() : new HashSet<>();
    ServiceUtils.BatchLoadByIdSetResult<OAuth2Group> groupsBatchLoadResult =
        ServiceUtils.batchLoadByIdSet(groupsIdSet, groupRepository);

    if (!groupsBatchLoadResult.getNotFoundIds().isEmpty()) {
      DomainException exception =
          new DomainException(
              "Not-Found Entities",
              "Some of the IDs in the groupsIds do not correspond to a registered entity",
              HttpStatus.BAD_REQUEST);

      exception.addProperty("notFoundGroupIds", groupsBatchLoadResult.getNotFoundIds());

      throw exception;
    }

    OAuth2User newUser =
        new OAuth2User(
            userDTO.getUsername(),
            passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getFirstName(),
            userDTO.getLastName(),
            groupsBatchLoadResult.getFoundEntities());
    newUser = saveUser(newUser);
    return oAuth2EntityDTOMapper.mapToOAuth2UserDTO(newUser);
  }

  @Transactional
  public OAuth2User saveUser(@Valid OAuth2User user) {
    return userRepository.save(user);
  }

  @Transactional
  public void assignGroupToUser(UUID userId, UUID groupId) {
    OAuth2User user = loadUserByIdOrThrow(userId);
    OAuth2Group group = groupService.loadGroupByIdOrThrow(groupId);
    if (!user.assignGroupToUser(group)) {
      throw new DomainException(
          "Already a Member of This Group",
          "This user already has the group with ID " + groupId + " in its groups list",
          HttpStatus.CONFLICT);
    }
    saveUser(user);
  }

  public Set<OAuth2ActivityDTO> getUserActivities(UUID userId) {
    OAuth2User user = loadUserByIdOrThrow(userId);
    return user.getActivities().stream()
        .map(activity -> oAuth2EntityDTOMapper.mapToOAuth2ActivityDTO(activity))
        .collect(Collectors.toSet());
  }
}
