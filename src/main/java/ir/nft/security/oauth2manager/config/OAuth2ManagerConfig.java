package ir.nft.security.oauth2manager.config;

import ir.nft.security.oauth2manager.mapper.OAuth2EntityDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class OAuth2ManagerConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public OAuth2EntityDTOMapper oAuth2EntityDTOMapper() {
    return new OAuth2EntityDTOMapper();
  }
}
