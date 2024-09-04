package ir.nft.security.oauth2manager.entity;

import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "oauth2_activity")
public class OAuth2Activity extends OAuth2Entity {

  /** A validator should be provided for this field later on */
  @Column(nullable = false)
  @NotNull
  private String path;

  /** A validator should be provided for this field later on */
  @Column(nullable = false)
  @NotNull
  private String cumulativePath;

  @Nullable
  @OneToOne
  @JoinColumn(name = "parent_id")
  private OAuth2Activity parent;

  @Column(nullable = false)
  @NotNull
  @UserName
  private String resourceServerClientId;

  public OAuth2Activity(
      String path,
      String cumulativePath,
      @Nullable OAuth2Activity parent,
      String resourceServerClientId) {
    super();
    this.path = path;
    this.cumulativePath = cumulativePath;
    this.parent = parent;
    this.resourceServerClientId = resourceServerClientId;
  }
}
