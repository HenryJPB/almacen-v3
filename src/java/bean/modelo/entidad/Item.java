/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.entidad;

/**
 *
 * @author henrypb
 */
public class Item {

    private String codigo;
    private String grupo;
    private String descripcion;
    private String codUbic;
    private String um;
    private String nivelControl;
    private Float stockMin;
    private Float stockMax;
    private Float ptoReorden;
    private String indicador;

    public Item(String codigo, String grupo, String descripcion, String codUbic, String um, String nivelControl, Float stockMin, Float stockMax, Float ptoReorden, String indicador) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.codUbic = codUbic;
        this.um = um;
        this.nivelControl = nivelControl;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.ptoReorden = ptoReorden;
        this.indicador = indicador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodUbic() {
        return codUbic;
    }

    public void setCodUbic(String codUbic) {
        this.codUbic = codUbic;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getNivelControl() {
        return nivelControl;
    }

    public void setNivelControl(String nivelControl) {
        this.nivelControl = nivelControl;
    }

    public Float getStockMin() {
        return stockMin;
    }

    public void setStockMin(Float stockMin) {
        this.stockMin = stockMin;
    }

    public Float getStockMax() {
        return stockMax;
    }

    public void setStockMax(Float stockMax) {
        this.stockMax = stockMax;
    }

    public Float getPtoReorden() {
        return ptoReorden;
    }

    public void setPtoReorden(Float ptoReorden) {
        this.ptoReorden = ptoReorden;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

}
