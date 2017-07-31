package guru.springframework.services;

import guru.springframework.domain.TipoConstruccion;
import guru.springframework.repositories.TipoConstruccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoConstruccionServiceImpl implements TipoConstruccionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TipoConstruccionRepository tipoConstruccionRepository;

    @Autowired
    public void setTipoConstruccionRepository(TipoConstruccionRepository tipoConstruccionRepository) {
        this.tipoConstruccionRepository = tipoConstruccionRepository;
    }

    @Override
    public Iterable<TipoConstruccion> listAll() {
        logger.debug("listAll called");
        return tipoConstruccionRepository.findAll();
    }

    @Override
    public TipoConstruccion getById(Integer id) {
        logger.debug("getById called");
        return tipoConstruccionRepository.findOne(id);
    }

    @Override
    public TipoConstruccion save(TipoConstruccion tipoConstruccion) {
        logger.debug("saveProduct called");
        return tipoConstruccionRepository.save(tipoConstruccion);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        tipoConstruccionRepository.delete(id);
    }
}
