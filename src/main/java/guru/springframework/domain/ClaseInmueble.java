package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ClaseInmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID")
    private Integer id;
    @ApiModelProperty(notes = "claseInmueble Id")
    private String claseInmuebleId;
    @ApiModelProperty(notes = "The product description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaseInmuebleId() {
        return claseInmuebleId;
    }

    public void setClaseInmuebleId(String claseInmuebleId) {
        this.claseInmuebleId = claseInmuebleId;
    }
}
