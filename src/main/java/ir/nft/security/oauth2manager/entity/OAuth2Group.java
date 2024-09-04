package ir.nft.security.oauth2manager.entity;

import ir.nft.security.oauth2manager.validation.annotation.GroupTitle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "oauth2_group")
public class OAuth2Group implements OAuth2Entity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oauth2_group_seq_gen")
  @SequenceGenerator(
      name = "oauth2_group_seq_gen",
      sequenceName = "oauth2_group_seq",
      allocationSize = 1)
  @EqualsAndHashCode.Exclude
  private Integer id;

  @Column(unique = true, nullable = false)
  @GroupTitle
  @NotNull
  private String title;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "oauth2_group_group",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "sub_group_id"))
  @Nullable
  @EqualsAndHashCode.Exclude
  private Set<OAuth2Group> subGroups;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "oauth2_group_activity",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "activity_id"))
  @Nullable
  @EqualsAndHashCode.Exclude
  private Set<OAuth2Activity> activities;

  public boolean addSubgroup(OAuth2Group subGroup) {
    if (null == this.subGroups) {
      this.subGroups = new HashSet<>();
    }
    return this.subGroups.add(subGroup);
  }

  public boolean addActivity(OAuth2Activity activity) {
    if (null == this.activities) {
      this.activities = new HashSet<>();
    }
    return this.activities.add(activity);
  }
}
