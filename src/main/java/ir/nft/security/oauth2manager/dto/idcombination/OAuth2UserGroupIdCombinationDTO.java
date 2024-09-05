package ir.nft.security.oauth2manager.dto.idcombination;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OAuth2UserGroupIdCombinationDTO {
    @NotNull(message = "The userId field must be included")
    private UUID userId;
    @NotNull(message = "The groupId field must be included")
    private UUID groupId;
}
