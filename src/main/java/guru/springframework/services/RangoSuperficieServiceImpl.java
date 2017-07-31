package guru.springframework.services;

import guru.springframework.domain.RangoSuperficie;
import guru.springframework.domain.TipoConstruccion;
import guru.springframework.repositories.RangoSuperficieRepository;
import guru.springframework.repositories.TipoConstruccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RangoSuperficieServiceImpl implements RangoSuperficieService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RangoSuperficieRepository rangoSuperficieRepository;

    @Autowired
    public void setRangoSuperficieRepository(RangoSuperficieRepository rangoSuperficieRepository) {
        this.rangoSuperficieRepository = rangoSuperficieRepository;
    }

    @Override
    public Iterable<RangoSuperficie> listAll() {
        logger.debug("listAll called");
        return rangoSuperficieRepository.findAll();
    }

    @Override
    public RangoSuperficie getById(Integer id) {
        logger.debug("getById called");
        return rangoSuperficieRepository.findOne(id);
    }

    @Override
    public RangoSuperficie save(RangoSuperficie rangoSuperficie) {
        logger.debug("saveProduct called");
        return rangoSuperficieRepository.save(rangoSuperficie);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        rangoSuperficieRepository.delete(id);
    }
}
