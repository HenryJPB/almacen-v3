/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.vista;

/**
 *
 * @author henrypb
 */
public class ItemExistViewFilter {
    
    String codItem = ""; 
    String descripcion = "";
    String ubicacion = "";
    String um = ""; 
    String existencia = ""; 

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem==null?"":codItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion==null?"":descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion==null?"":ubicacion;  
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um==null?"":um;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia==null?"":existencia;
    }
    
}
