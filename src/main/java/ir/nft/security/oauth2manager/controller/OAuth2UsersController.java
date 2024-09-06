package ir.nft.security.oauth2manager.controller;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2UserDTO;
import ir.nft.security.oauth2manager.dto.idcombination.OAuth2UserGroupIdCombinationDTO;
import ir.nft.security.oauth2manager.dto.idcombination.OAuth2UserIdDTO;
import ir.nft.security.oauth2manager.service.OAuth2UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(APISpecifications.USERS_API_V1_URL)
public class OAuth2UsersController {

  private final OAuth2UserService userService;

  public OAuth2UsersController(OAuth2UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<OAuth2UserDTO> registerOAuth2User(
      @Valid @RequestBody OAuth2UserDTO userDTO) {
    OAuth2UserDTO registeredUsers = userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUsers);
  }

  @PostMapping("/groups")
  public ResponseEntity<String> assignGroupToUser(
      @Valid @RequestBody OAuth2UserGroupIdCombinationDTO userGroupIdCombinationDTO) {
    userService.assignGroupToUser(userGroupIdCombinationDTO);
    return ResponseEntity.ok("The group has been successfully added to the user.");
  }

  @PostMapping
  public ResponseEntity<OAuth2UserDTO> getUserById(@Valid @RequestBody OAuth2UserIdDTO userIdDTO) {
    return ResponseEntity.ok(userService.getUserDTOById(userIdDTO.getUserId()));
  }

  @PostMapping(value = "/activities")
  public ResponseEntity<Set<OAuth2ActivityDTO>> getActivities(
      @Valid @RequestBody OAuth2UserIdDTO userIdDTO) {
    return ResponseEntity.ok(userService.getUserActivities(userIdDTO.getUserId()));
  }
}
