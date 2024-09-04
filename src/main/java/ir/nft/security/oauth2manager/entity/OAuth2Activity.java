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
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "oauth2_activity")
public class OAuth2Activity implements OAuth2Entity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oauth2_activity_seq_gen")
  @SequenceGenerator(
      name = "oauth2_activity_seq_gen",
      sequenceName = "oauth2_activity_seq",
      allocationSize = 1)
  @EqualsAndHashCode.Exclude
  private Integer id;

  /** A validator should be provided for this field later on */
  @Column(nullable = false)
  @NotNull
  private String path;

  /** A validator should be provided for this field later on */
  @Column(nullable = false)
  @NotNull
  private String cumulativePath;

  @Nullable private Integer parentId;

  @Column(nullable = false)
  @NotNull
  @UserName
  private String resourceServerClientId;
}
