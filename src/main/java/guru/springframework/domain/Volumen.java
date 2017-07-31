package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

public class Volumen {

    @ApiModelProperty(notes = "volumen")
    private List<ItemVolumen> volumen;

    public List<ItemVolumen> getVolumen() {
        return volumen;
    }

    public void setVolumen(List<ItemVolumen> volumen) {
        this.volumen = volumen;
    }

}
