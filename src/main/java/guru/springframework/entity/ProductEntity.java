package guru.springframework.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductEntity extends AuditableEntity {
    private static final long serialVersionUID = 7851156574517743145L;
    private Integer id;
    private Integer version;
    private String productId;
    private String description;
    private String imageUrl;
    private BigDecimal price;

}
