package guru.springframework.repositories;

import guru.springframework.domain.ClaseInmueble;
import guru.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClaseInmuebleRepository extends CrudRepository<ClaseInmueble, Integer>{
}
