package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ActivityDTO;
import ir.nft.security.oauth2manager.entity.OAuth2Activity;
import ir.nft.security.oauth2manager.mapper.OAuth2EntityDTOMapper;
import ir.nft.security.oauth2manager.repository.OAuth2ActivityRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ActivityService {
  private final OAuth2ActivityRepository activityRepository;
  private final OAuth2EntityDTOMapper oAuth2EntityDTOMapper;

  public OAuth2ActivityService(
      OAuth2ActivityRepository activityRepository, OAuth2EntityDTOMapper oAuth2EntityDTOMapper) {
    this.activityRepository = activityRepository;
    this.oAuth2EntityDTOMapper = oAuth2EntityDTOMapper;
  }

  public OAuth2Activity saveActivity(@Valid OAuth2Activity activity) {
    return activityRepository.save(activity);
  }

  @Transactional
  public OAuth2ActivityDTO createActivity(OAuth2ActivityDTO activityDTO) {
    return oAuth2EntityDTOMapper.mapToOAuth2ActivityDTO(
        saveActivity(oAuth2EntityDTOMapper.mapToOAuth2Activity(activityDTO)));
  }
}
