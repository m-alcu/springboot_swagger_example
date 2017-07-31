package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

public class InputTasacion {

    @ApiModelProperty(notes = "tipo Finca", required = true)
    private String tipoFinca;
    @ApiModelProperty(notes = "clase Inmueble", required = true)
    private String claseInmuebleId;
    @ApiModelProperty(notes = "superficie m2", required = true)
    private Integer superficie;
    @ApiModelProperty(notes = "codigo postal", required = true)
    private Integer codigoPostal;
    @ApiModelProperty(notes = "tipo construccion", required = true)
    private String tipoConstruccionId;
    @ApiModelProperty(notes = "indicador trastero anejo")
    private Boolean trasteroAnejo;
    @ApiModelProperty(notes = "indicador aparcamiento anejo")
    private Boolean aparcamientoAnejo;
    @ApiModelProperty(notes = "Naturaleza")
    private String naturaleza;
    @ApiModelProperty(notes = "Provincia")
    private String provincia;
    @ApiModelProperty(notes = "Municipio")
    private String municipio;

    public String getTipoFinca() {
        return tipoFinca;
    }

    public void setTipoFinca(String tipoFinca) {
        this.tipoFinca = tipoFinca;
    }

    public String getClaseInmuebleId() {
        return claseInmuebleId;
    }

    public void setClaseInmuebleId(String claseInmuebleId) {
        this.claseInmuebleId = claseInmuebleId;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTipoConstruccionId() {
        return tipoConstruccionId;
    }

    public void setTipoConstruccionId(String tipoConstruccionId) {
        this.tipoConstruccionId = tipoConstruccionId;
    }

    public Boolean getTrasteroAnejo() {
        return trasteroAnejo;
    }

    public void setTrasteroAnejo(Boolean trasteroAnejo) {
        this.trasteroAnejo = trasteroAnejo;
    }

    public Boolean getAparcamientoAnejo() {
        return aparcamientoAnejo;
    }

    public void setAparcamientoAnejo(Boolean aparcamientoAnejo) {
        this.aparcamientoAnejo = aparcamientoAnejo;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
