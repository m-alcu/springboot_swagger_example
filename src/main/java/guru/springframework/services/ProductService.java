package guru.springframework.services;


import guru.springframework.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    /**
     * Find by id.
     *
     * @param id the id
     * @return the product
     */
    ProductEntity findById(Integer id);

    /**
     * Find all.
     *
     * @return the list
     */
    List<ProductEntity> findAll();


    /**
     * Insert.
     *
     * @param product the markup
     * @return rows affected
     */
    int insert(ProductEntity product);

    /**
     * Update.
     *
     * @param product the markup
     * @return rows affected
     */
    int update(ProductEntity product);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Exist.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean exist(Integer id);


}
