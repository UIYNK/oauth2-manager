package ir.nft.security.oauth2manager.controller;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2UserDTO;
import ir.nft.security.oauth2manager.service.OAuth2UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("${app.base-api-url}" + "/users")
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

  @PostMapping("/{user-id}/groups/{group-id}")
  public ResponseEntity<String> assignGroupToUser(
      @PathVariable(name = "user-id") UUID userId, @PathVariable(name = "group-id") UUID groupId) {
    userService.assignGroupToUser(userId, groupId);
    return ResponseEntity.ok("The group has been successfully added to the user.");
  }

  @GetMapping("/{user-id}")
  public ResponseEntity<OAuth2UserDTO> getUserById(@PathVariable(name = "user-id") UUID userId) {
    return ResponseEntity.ok(userService.getUserDTOById(userId));
  }

  @GetMapping("/{user-id}/activities")
  public ResponseEntity<Set<OAuth2ActivityDTO>> getActivities(
      @PathVariable(name = "user-id") UUID userId) {
    return ResponseEntity.ok(userService.getUserActivities(userId));
  }
}
