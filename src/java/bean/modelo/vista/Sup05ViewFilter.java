/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

/**
 *
 * @author henrypb
 * * referencia al objeto Vista: Item ( SUP05_VIEW ) 
 */
public class Sup05ViewFilter {

   private String codItem="";   
   private String descripcion="";  
   private String fecha="";  
   private String tipoMov="";   
   private String costo="";   
   private String cantRequerida="";  
   private String unidades="";   
   private String referencia="";   
   private String centroCosto="";   
   private String codProceso="";  
   private String codMaquina="";  
   private String origen="";  
   private String observacion="";  

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem =( codItem==null?"":codItem );
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = ( descripcion==null?"":descripcion );
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = ( fecha==null?"":fecha );
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = ( tipoMov==null?"":tipoMov );
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = ( costo==null?"":costo );
    }

    public String getCantRequerida() {
        return cantRequerida;
    }

    public void setCantRequerida(String cantRequerida) {
        this.cantRequerida = ( cantRequerida==null?"":cantRequerida );
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = ( unidades==null?"":unidades );
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = ( referencia==null?"":referencia );
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = ( centroCosto==null?"":centroCosto );
    }

    public String getCodProceso() {
        return codProceso;
    }

    public void setCodProceso(String codProceso) {
        this.codProceso = ( codProceso==null?"":codProceso );
    }

    public String getCodMaquina() {
        return codMaquina;
    }

    public void setCodMaquina(String codMaquina) {
        this.codMaquina = ( codMaquina==null?"":codMaquina );
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = ( origen==null?"":origen );
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = (observacion==null ? "" : observacion);
    }
   
}
