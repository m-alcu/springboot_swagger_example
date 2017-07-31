package guru.springframework.repositories;

import guru.springframework.domain.RangoSuperficie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RangoSuperficieRepository extends CrudRepository<RangoSuperficie, Integer>{
}
