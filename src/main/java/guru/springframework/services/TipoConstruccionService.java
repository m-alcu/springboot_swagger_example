package guru.springframework.services;

import guru.springframework.domain.TipoConstruccion;

public interface TipoConstruccionService {
    Iterable<TipoConstruccion> listAll();

    TipoConstruccion getById(Integer id);

    TipoConstruccion save(TipoConstruccion tipoConstruccion);

    void delete(Integer id);
}
