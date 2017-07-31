package guru.springframework.repositories;

import guru.springframework.domain.ClaseInmueble;
import guru.springframework.domain.TipoConstruccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TipoConstruccionRepository extends CrudRepository<TipoConstruccion, Integer>{
}
