package ir.nft.security.oauth2manager.entity;

import ir.nft.security.oauth2manager.validation.annotation.EnglishName;
import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "oauth2_user")
public class OAuth2User extends OAuth2Entity {

  @Column(nullable = false, unique = true)
  @UserName
  @NotNull
  private String username;

  @Column(nullable = false)
  @NotNull
  private String password;

  @Column(nullable = false)
  @EnglishName
  @NotNull
  private String firstName;

  @Column(nullable = false)
  @EnglishName
  @NotNull
  private String lastName;

  @OneToMany
  @JoinTable(
      name = "oauth2_user_group",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id"))
  @EqualsAndHashCode.Exclude
  private Set<OAuth2Group> groups;

  public OAuth2User(
      String username,
      String password,
      String firstName,
      String lastName,
      Set<OAuth2Group> groups) {
    super();
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.groups = groups;
  }

  /**
   * Retrieves all {@link OAuth2Activity} objects associated with the current User.
   *
   * <p>This method iterates through each {@link OAuth2Group} associated with this {@link
   * OAuth2User} instance and adds each {@link OAuth2Activity} that has not been already added to
   * the to-be-returned collection. It does not use {@link Set#addAll(Collection)} due to the
   * potential for failure if some activities are acquired multiple times from different groups.
   *
   * @return a {@link Set} containing all the {@link OAuth2Activity} objects from all groups.
   */
  public Set<OAuth2Activity> getActivities() {
    Set<OAuth2Activity> activities = new HashSet<>();
    for (OAuth2Group group : groups) {
      for (OAuth2Activity activity : group.getActivities()) {
        activities.add(activity);
      }
    }
    return activities;
  }

  public boolean assignGroupToUser(OAuth2Group group) {
    return groups.add(group);
  }
}
