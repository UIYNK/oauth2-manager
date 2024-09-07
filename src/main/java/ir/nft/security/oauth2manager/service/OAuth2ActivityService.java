package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.entity.OAuth2Activity;
import ir.nft.security.oauth2manager.exception.DomainException;
import ir.nft.security.oauth2manager.mapper.OAuth2EntityDTOMapper;
import ir.nft.security.oauth2manager.repository.OAuth2ActivityRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OAuth2ActivityService {
  private final OAuth2ActivityRepository activityRepository;
  private final OAuth2EntityDTOMapper oAuth2EntityDTOMapper;

  public OAuth2ActivityService(
      OAuth2ActivityRepository activityRepository, OAuth2EntityDTOMapper oAuth2EntityDTOMapper) {
    this.activityRepository = activityRepository;
    this.oAuth2EntityDTOMapper = oAuth2EntityDTOMapper;
  }

  public OAuth2Activity loadActivityByIdOrThrow(UUID id) {
    return activityRepository
        .findById(id)
        .orElseThrow(
            () ->
                new DomainException(
                    "Activity not found",
                    "No Activity with the requested ID was found",
                    HttpStatus.BAD_REQUEST));
  }

  public OAuth2Activity saveActivity(@Valid OAuth2Activity activity) {
    return activityRepository.save(activity);
  }

  @Transactional
  public OAuth2ActivityDTO createActivity(OAuth2ActivityDTO activityDTO) {

    OAuth2Activity parent =
        null != activityDTO.getParentId()
            ? loadActivityByIdOrThrow(activityDTO.getParentId())
            : null;
    OAuth2Activity newActivity =
        new OAuth2Activity(
            activityDTO.getPath(),
            activityDTO.getCumulativePath(),
            parent,
            activityDTO.getResourceServerClientId());
    return oAuth2EntityDTOMapper.mapToOAuth2ActivityDTO(saveActivity(newActivity));
  }
}
