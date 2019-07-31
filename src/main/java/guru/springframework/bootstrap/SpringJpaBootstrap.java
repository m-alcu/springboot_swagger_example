package guru.springframework.bootstrap;

import guru.springframework.entity.ProductEntity;
import guru.springframework.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log4j2
@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
    }


    /** The service. */
    @Autowired
    private ProductService productService;

    private void loadProducts() {
        ProductEntity shirt = new ProductEntity();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        shirt.setProductId("235268845711068308");
        shirt.setCreatedBy("user");
        productService.insert(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        ProductEntity mug = new ProductEntity();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setProductId("168639393495335947");
        mug.setPrice(new BigDecimal("11.95"));
        mug.setCreatedBy("user");
        productService.insert(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }


    }



