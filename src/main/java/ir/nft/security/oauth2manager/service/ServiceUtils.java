package ir.nft.security.oauth2manager.service;

import ir.nft.security.oauth2manager.entity.OAuth2Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ServiceUtils {
  private ServiceUtils() {}

  public static <T extends OAuth2Entity> BatchLoadByIdSetResult<T> batchLoadByIdSet(
      Set<Integer> ids, JpaRepository<T, Integer> repository) {
    List<T> entitiesFromDB = repository.findAllById(ids);

    Set<T> foundEntities = new HashSet<>();
    Set<Integer> notFoundIds = new HashSet<>(ids);
    for (T entity : entitiesFromDB) {
      notFoundIds.remove(entity.getId());
      foundEntities.add(entity);
    }

    return new BatchLoadByIdSetResult<>(foundEntities, notFoundIds);
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class BatchLoadByIdSetResult<T extends OAuth2Entity> {
    private Set<T> foundEntities;
    private Set<Integer> notFoundIds;
  }
}
