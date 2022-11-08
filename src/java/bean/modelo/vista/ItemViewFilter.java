/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

/**
 *
 * @author henrypb
 * referencia al objeto tabla : Item ( SUP01_DAT ) 
 */
public class ItemViewFilter {

    private String codigo="";
    private String grupo="";
    private String descripcion="";     
    private String codUbic=""; 
    private String um=""; 
    private String nivelControl=""; 
    private String stockMin=""; 
    private String stockMax=""; 
    private String ptoReorden=""; 
    private String indicador=""; 

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo==null?"":codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo==null?"":grupo;
    }

    public String getDescripcion() {
        return descripcion; 
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion==null?"":descripcion;
    }

    public String getCodUbic() {
        return codUbic;
    }

    public void setCodUbic(String codUbic) {
        this.codUbic = codUbic==null?"":codUbic;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um==null?"":um;
    }

    public String getNivelControl() {
        return nivelControl;
    }

    public void setNivelControl(String nivelControl) {
        this.nivelControl = nivelControl==null?"":nivelControl;
    }

    public String getStockMin() {
        return stockMin;
    }

    public void setStockMin(String stockMin) {
        this.stockMin = stockMin==null?"":stockMin;
    }

    public String getStockMax() {
        return stockMax;
    }

    public void setStockMax(String stockMax) {
        this.stockMax = stockMax==null?"":stockMax;
    }

    public String getPtoReorden() {
        return ptoReorden;
    }

    public void setPtoReorden(String ptoReorden) {
        this.ptoReorden = ptoReorden==null?"":ptoReorden;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador==null?"":indicador;
    }
    
    
    
}
