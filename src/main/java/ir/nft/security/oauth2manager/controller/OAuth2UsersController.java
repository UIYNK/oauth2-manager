package ir.nft.security.oauth2manager.controller;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2UserDTO;
import ir.nft.security.oauth2manager.dto.idcombination.OAuth2UserGroupIdCombinationDTO;
import ir.nft.security.oauth2manager.dto.idcombination.OAuth2UserIdDTO;
import ir.nft.security.oauth2manager.service.OAuth2UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(APISpecifications.USERS_API_V1_URL)
@RequiredArgsConstructor
public class OAuth2UsersController {

  private final OAuth2UserService userService;

  @PostMapping("/register-user")
  public ResponseEntity<OAuth2UserDTO> registerUser(@Valid @RequestBody OAuth2UserDTO userDTO) {
    OAuth2UserDTO registeredUsers = userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUsers);
  }

  @PostMapping("/assign-group-to-user")
  public ResponseEntity<String> assignGroupToUser(
      @Valid @RequestBody OAuth2UserGroupIdCombinationDTO userGroupIdCombinationDTO) {
    userService.assignGroupToUser(userGroupIdCombinationDTO);
    return ResponseEntity.ok("The group has been successfully added to the user.");
  }

  @PostMapping("/get-user-by-id")
  public ResponseEntity<OAuth2UserDTO> getUserById(@Valid @RequestBody OAuth2UserIdDTO userIdDTO) {
    return ResponseEntity.ok(userService.getUserDTOById(userIdDTO.getUserId()));
  }

  @PostMapping(value = "/get-activities")
  public ResponseEntity<Set<OAuth2ActivityDTO>> getActivities(
      @Valid @RequestBody OAuth2UserIdDTO userIdDTO) {
    return ResponseEntity.ok(userService.getUserActivities(userIdDTO.getUserId()));
  }
}
