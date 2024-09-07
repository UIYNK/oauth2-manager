package ir.nft.security.oauth2manager.controller;

import ir.nft.security.oauth2manager.dto.domain.OAuth2GroupDTO;
import ir.nft.security.oauth2manager.dto.idcombination.OAuth2GroupIdDTO;
import ir.nft.security.oauth2manager.service.OAuth2GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APISpecifications.GROUPS_API_V1_URL)
@RequiredArgsConstructor
public class OAuth2GroupsController {

  private final OAuth2GroupService groupService;

  @PostMapping("/register-group")
  public ResponseEntity<OAuth2GroupDTO> registerGroup(@Valid @RequestBody OAuth2GroupDTO groupDTO) {
    OAuth2GroupDTO registeredGroups = groupService.createGroup(groupDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredGroups);
  }

  @PostMapping("/get-group-by-id")
  public ResponseEntity<OAuth2GroupDTO> getGroupById(
      @Valid @RequestBody OAuth2GroupIdDTO groupIdDTO) {
    return ResponseEntity.ok(groupService.getGroupDTOById(groupIdDTO.getGroupId()));
  }
}
