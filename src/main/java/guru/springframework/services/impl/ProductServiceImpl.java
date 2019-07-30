package guru.springframework.services.impl;

import guru.springframework.dao.ProductDao;
import guru.springframework.entity.ProductEntity;
import guru.springframework.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * The dao product.
     */
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductEntity> findAll() {
        log.debug("findAll called");
        return productDao.findAll();
    }

    @Override
    public ProductEntity findById(Integer id) {
        log.debug("findById called");
        return productDao.findById(id);
    }

    @Override
    public int insert(ProductEntity product) {
        log.debug("insert called");
        return productDao.insert(product);
    }

    @Override
    public int update(ProductEntity product) {
        log.debug("update called");
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        log.debug("delete called");
        productDao.delete(id);
  }

    @Override
    public Boolean exist(Integer id) {
        return productDao.exists(id);
    }

}
