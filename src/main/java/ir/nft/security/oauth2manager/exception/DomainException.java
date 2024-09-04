package ir.nft.security.oauth2manager.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DomainException extends RuntimeException {
  @Nullable private Class<? extends Exception> wrappedException;
  private final String title;
  private final HttpStatus status;
  @Nullable private Map<String, Object> properties;

  public DomainException(String title, String message, HttpStatus status) {
    super(message);
    this.title = title;
    this.status = status;
  }

  public DomainException(
      String title,
      String message,
      @Nullable Class<? extends Exception> wrappedException,
      HttpStatus status) {
    super(message);
    this.title = title;
    this.wrappedException = wrappedException;
    this.status = status;
  }

  public void addProperty(String key, Object value) {
    if (properties == null) {
      properties = new HashMap<>();
    }
    properties.put(key, value);
  }
}
