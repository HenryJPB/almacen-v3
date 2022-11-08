/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.entidad;

/**
 *
 * @author henrypb
 * 
 * ** NOTA IMPORTANTE **  // Atencion:  //  ( Bqto, 14 de octubre 2019 ).  
 * Esta clase entidad fue creada con el proposito de gestionar los atributos 
 * de la vista SUP05_VIEW como valores tipo String del listbox --> con ello, 
 * los valores tipo Date() y o numericos insertalos en el Listbox ya formateados
 * y no insertar las widget Datebox  y/o Decimalbox, ....
 * 
 */
public class Sup05Vista {
    
    private String codItem;
    private String descripcion;
    private String fechaS;
    private String tipoMov;
    private String costoS;
    private String cantRequeridaS;
    private String unidadesS;
    private String referencia;
    private String centroCosto;
    private String codProceso;
    private String codMaquina;
    private String origen;
    private String observacion; 

    public Sup05Vista(String codItem, String descripcion, String fechaS, String tipoMov, String costoS, String cantRequeridaS, String unidadesS, String referencia, String centroCosto, String codProceso, String codMaquina, String origen, String observacion) {
        this.codItem = codItem;
        this.descripcion = descripcion;
        this.fechaS = fechaS;
        this.tipoMov = tipoMov;
        this.costoS = costoS;
        this.cantRequeridaS = cantRequeridaS;
        this.unidadesS = unidadesS;
        this.referencia = referencia;
        this.centroCosto = centroCosto;
        this.codProceso = codProceso;
        this.codMaquina = codMaquina;
        this.origen = origen;
        this.observacion = observacion;
    }
    
    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaS() {
        return fechaS;
    }

    public void setFechaS(String fechaS) {
        this.fechaS = fechaS;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public String getCostoS() {
        return costoS;
    }

    public void setCostoS(String costoS) {
        this.costoS = costoS;
    }

    public String getCantRequeridaS() {
        return cantRequeridaS;
    }

    public void setCantRequeridaS(String cantRequeridaS) {
        this.cantRequeridaS = cantRequeridaS;
    }

    public String getUnidadesS() {
        return unidadesS;
    }

    public void setUnidadesS(String unidadesS) {
        this.unidadesS = unidadesS;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCodProceso() {
        return codProceso;
    }

    public void setCodProceso(String codProceso) {
        this.codProceso = codProceso;
    }

    public String getCodMaquina() {
        return codMaquina;
    }

    public void setCodMaquina(String codMaquina) {
        this.codMaquina = codMaquina;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
}
