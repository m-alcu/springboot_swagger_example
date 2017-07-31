package guru.springframework.services;

import guru.springframework.domain.ClaseInmueble;
import guru.springframework.repositories.ClaseInmuebleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaseInmuebleServiceImpl implements ClaseInmuebleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ClaseInmuebleRepository claseInmuebleRepository;

    @Autowired
    public void setClaseInmuebleRepository(ClaseInmuebleRepository claseInmuebleRepository) {
        this.claseInmuebleRepository = claseInmuebleRepository;
    }

    @Override
    public Iterable<ClaseInmueble> listAll() {
        logger.debug("listAll called");
        return claseInmuebleRepository.findAll();
    }

    @Override
    public ClaseInmueble getById(Integer id) {
        logger.debug("getById called");
        return claseInmuebleRepository.findOne(id);
    }

    @Override
    public ClaseInmueble save(ClaseInmueble claseInmueble) {
        logger.debug("saveProduct called");
        return claseInmuebleRepository.save(claseInmueble);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        claseInmuebleRepository.delete(id);
    }
}
