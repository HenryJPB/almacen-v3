/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfaz;

import bean.modelo.entidad.Sup08Dat;
import org.zkoss.zul.ListModel;

/**
 *
 * @author henrypb
 * NOTA: referencia al objeto vista: ops$dessup03.SUP05_VIEW. 
 * 
 */
public interface IModelViewSup08Dat {

    // ** Definir atributos de ambito global para esta clase:   **
    
    // public Sup08Dat selectedSup08Dat;                                          *(1)*
    
    // public ...ViewFilter => * No aplican filtros para esta clase ***
    
    // public List<Sup08Dat> listaSup08Dat = new bean.controlador.... []
    
    //@Init
    public abstract void init();  
    
    //--------------------------------------------------------------------------
    public abstract ListModel<Sup08Dat> getViewSup08DatModel();  

    //--------------** Para esta clase no aplican los filtros **----------------
    //public abstract Mant01ViewFilter getView Mant01VistaFilter();
    
     //@Command
    //@NotifyChange({"viewSup05VistaModel", "footer"})  // Anotacion mandatoria <-> get'ViewSup05VistaModel
    //public abstract void changeVistaFilter();  
    
    // . ***********************************************************************
    // ..    ** IMPORTANTE **
    // generar los getter y los setter del atributo:
    // ... *********************************************************************
    
}
