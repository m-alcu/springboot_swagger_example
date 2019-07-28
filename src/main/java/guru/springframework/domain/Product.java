package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    private String productId;
    private String description;
    private String imageUrl;
    private BigDecimal price;

}
