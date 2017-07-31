package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

public class Zona {

    @ApiModelProperty(notes = "lista puntos")
    private List<PuntoEspacial> puntos;

    public List<PuntoEspacial> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoEspacial> puntos) {
        this.puntos = puntos;
    }

}
