package ir.nft.security.oauth2manager.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {
  private String title;
  private String status;
  @Nullable private String detail;
  @Nullable @JsonIgnore private Map<String, Object> properties;

  @JsonAnyGetter
  @Nullable
  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setInvalidParameters(Collection<InvalidParameter> invalidParameters) {
    if (null == this.properties) {
      this.properties = new HashMap<>();
    }
    this.properties.put("invalidParameters", invalidParameters);
  }

  @Builder
  @AllArgsConstructor
  @Getter
  @Setter
  public static class InvalidParameter {
    private String field;
    private String cause;
  }
}
