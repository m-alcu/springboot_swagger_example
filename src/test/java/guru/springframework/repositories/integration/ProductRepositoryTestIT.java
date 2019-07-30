package guru.springframework.repositories.integration;

import guru.springframework.configuration.RepositoryConfiguration;
import guru.springframework.dao.ProductDao;
import guru.springframework.entity.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTestIT {
    private ProductDao productDao;

    @Test
    public void testSaveProduct(){
        //setup product
        ProductEntity product = new ProductEntity();
        product.setDescription("Spring Framework Guru Shirt");
        product.setPrice(new BigDecimal("18.95"));
        product.setProductId("1234");
        //save product, verify has ID value after save
        assertNull(product.getId()); //null before save
        productDao.insert(product);
        assertNotNull(product.getId()); //not null after save
        //fetch from DB
        ProductEntity fetchedProduct = productDao.findById(product.getId());
        //should not be null
        assertNotNull(fetchedProduct);
        //should equal
        assertEquals(product.getId(), fetchedProduct.getId());
        assertEquals(product.getDescription(), fetchedProduct.getDescription());
        //update description and save
        fetchedProduct.setDescription("New Description");
        productDao.insert(fetchedProduct);
        //get from DB, should be updated
        ProductEntity fetchedUpdatedProduct = productDao.findById(fetchedProduct.getId());
        assertEquals(fetchedProduct.getDescription(), fetchedUpdatedProduct.getDescription());
        //verify count of products in DB
        long productCount = productDao.findAll().size();
        assertEquals(2, productCount);
        //get all products, list should only have one
        Iterable<ProductEntity> products = productDao.findAll();
        int count = 0;
        for(ProductEntity p : products){
            count++;
        }
        assertEquals(count, 2);
    }
}
