package guru.springframework.services.integration;

import guru.springframework.configuration.TestConfiguration;
import guru.springframework.entity.ProductEntity;
import guru.springframework.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("guru.springframework")
@SpringBootTest(classes = {TestConfiguration.class})
public class ProductServiceTestIT {


    @Autowired
    private ProductService productService;

    @Test
    public void testSaveProduct(){
        //setup product
        ProductEntity product = new ProductEntity();
        product.setDescription("Spring Framework Guru Shirt");
        product.setPrice(new BigDecimal("18.95"));
        product.setProductId("1234");
        product.setCreatedBy("user");
        //save product, verify has ID value after save
        assertNull(product.getId()); //null before save
        productService.insert(product);
        assertNotNull(product.getId()); //not null after save
        //fetch from DB
        ProductEntity fetchedProduct = productService.findById(product.getId());
        //should not be null
        assertNotNull(fetchedProduct);
        //should equal
        assertEquals(product.getId(), fetchedProduct.getId());
        assertEquals(product.getDescription(), fetchedProduct.getDescription());
        //update description and save
        fetchedProduct.setDescription("New Description");
        productService.insert(fetchedProduct);
        //get from DB, should be updated
        ProductEntity fetchedUpdatedProduct = productService.findById(fetchedProduct.getId());
        assertEquals(fetchedProduct.getDescription(), fetchedUpdatedProduct.getDescription());
        //verify count of products in DB
        long productCount = productService.findAll().size();
        assertEquals(2, productCount);
        //get all products, list should only have one
        Iterable<ProductEntity> products = productService.findAll();
        int count = 0;
        for(ProductEntity p : products){
            count++;
        }
        assertEquals(count, 2);
    }
}
