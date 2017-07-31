package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

public class OutputTasacion {

    @ApiModelProperty(notes = "Lista de ventas")
    private List<Venta> ventas;
    @ApiModelProperty(notes = "Voumen")
    private Volumen volumen;
    @ApiModelProperty(notes = "Zona")
    private Zona zona;

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Volumen getVolumen() {
        return volumen;
    }

    public void setVolumen(Volumen volumen) {
        this.volumen = volumen;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
