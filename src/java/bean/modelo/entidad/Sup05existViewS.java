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
public class Sup05existViewS {
 
    String codItem;  
    String descripcion; 
    String ubicacion;
    String um;  
    String existencia;  

    public Sup05existViewS() {
    }

    public Sup05existViewS(String codItem, String descripcion, String ubicacion, String um, String existencia) {
        this.codItem = codItem;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.um = um;
        this.existencia = existencia;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }

    
    
}
