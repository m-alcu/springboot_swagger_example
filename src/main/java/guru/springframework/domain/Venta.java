package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

public class Venta {

    @ApiModelProperty(notes = "trimestre venta")
    private Integer trimestre;
    @ApiModelProperty(notes = "The price")
    private BigDecimal precio;
    @ApiModelProperty(notes = "superficie m2")
    private Integer superficie;
    @ApiModelProperty(notes = "direccion")
    private String direccion;
    @ApiModelProperty(notes = "poblacion")
    private String poblacion;
    @ApiModelProperty(notes = "ubicacion")
    private PuntoEspacial ubicacion;

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public PuntoEspacial getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(PuntoEspacial ubicacion) {
        this.ubicacion = ubicacion;
    }
}
