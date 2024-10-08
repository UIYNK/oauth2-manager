package ir.nft.security.oauth2manager.controller;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.service.OAuth2ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APISpecifications.ACTIVITIES_API_V1_URL)
@RequiredArgsConstructor
public class OAuth2ActivitiesController {

  private final OAuth2ActivityService activityService;

  @PostMapping("/register-activity")
  public ResponseEntity<OAuth2ActivityDTO> registerActivity(
      @Valid @RequestBody OAuth2ActivityDTO activityDTO) {
    OAuth2ActivityDTO registeredActivities = activityService.createActivity(activityDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredActivities);
  }
}
