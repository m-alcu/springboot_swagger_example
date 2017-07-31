package guru.springframework.services;


import guru.springframework.domain.ClaseInmueble;

public interface ClaseInmuebleService {
    Iterable<ClaseInmueble> listAll();

    ClaseInmueble getById(Integer id);

    ClaseInmueble save(ClaseInmueble claseInmueble);

    void delete(Integer id);
}
