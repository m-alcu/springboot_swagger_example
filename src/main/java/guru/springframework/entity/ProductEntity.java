package guru.springframework.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductEntity {
    private Integer id;
    private Integer version;
    private String productId;
    private String description;
    private String imageUrl;
    private BigDecimal price;

}
