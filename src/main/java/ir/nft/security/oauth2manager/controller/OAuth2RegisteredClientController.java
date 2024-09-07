package ir.nft.security.oauth2manager.controller;

import ir.nft.security.oauth2manager.dto.domain.OAuth2ClientDTO;
import ir.nft.security.oauth2manager.dto.domain.OAuth2ResourceServerDTO;
import ir.nft.security.oauth2manager.service.OAuth2RegisteredClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APISpecifications.REGISTERED_CLIENTS_API_V1_URL)
@RequiredArgsConstructor
public class OAuth2RegisteredClientController {

  private final OAuth2RegisteredClientService registeredClientService;

  @PostMapping("/register-resource-server")
  public ResponseEntity<OAuth2ResourceServerDTO> registerResourceServer(
      @RequestBody @Valid OAuth2ResourceServerDTO resourceServerDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(registeredClientService.createResourceServer(resourceServerDTO));
  }

  @PostMapping("/register-client")
  public ResponseEntity<OAuth2ClientDTO> registerClient(
      @RequestBody @Valid OAuth2ClientDTO clientDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(registeredClientService.createClient(clientDTO));
  }
}
