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
@EqualsAndHashCode(callSuper = true)
@Table(name = "oauth2_group")
public class OAuth2Group extends OAuth2Entity {

  @Column(unique = true, nullable = false)
  @GroupTitle
  @NotNull
  private String title;

  @OneToMany
  @JoinTable(
      name = "oauth2_group_group",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "sub_group_id"))
  @Nullable
  @EqualsAndHashCode.Exclude
  private Set<OAuth2Group> subGroups;

  @OneToMany
  @JoinTable(
      name = "oauth2_group_activity",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "activity_id"))
  @Nullable
  @EqualsAndHashCode.Exclude
  private Set<OAuth2Activity> activities;

  public OAuth2Group(
      String title,
      @Nullable Set<OAuth2Group> subGroups,
      @Nullable Set<OAuth2Activity> activities) {
    super();
    this.title = title;
    this.subGroups = subGroups;
    this.activities = activities;
  }

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
