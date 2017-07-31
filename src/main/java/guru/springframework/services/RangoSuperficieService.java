package guru.springframework.services;

import guru.springframework.domain.RangoSuperficie;

public interface RangoSuperficieService {
    Iterable<RangoSuperficie> listAll();

    RangoSuperficie getById(Integer id);

    RangoSuperficie save(RangoSuperficie rangoSuperficie);

    void delete(Integer id);
}
