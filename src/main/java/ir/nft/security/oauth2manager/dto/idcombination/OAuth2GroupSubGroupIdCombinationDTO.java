package ir.nft.security.oauth2manager.dto.idcombination;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OAuth2GroupSubGroupIdCombinationDTO {
    @NotNull(message = "The groupId field must be included")
    private UUID groupId;
    @NotNull(message = "The subGroupId field must be included")
    private UUID subGroupId;
}
