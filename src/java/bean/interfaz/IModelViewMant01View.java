/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfaz;

import bean.modelo.entidad.Mant01View;
import org.zkoss.zul.ListModel;

/**
 *
 * @author henrypb
 * NOTA: referencia al objeto vista: ops$dessup03.SUP05_VIEW. 
 * 
 */
public interface IModelViewMant01View {

    // Definir atributos de ambito global para esta clase:  
    
    // private ItemVista selectedItemVista;                                          *(1)*
    
    // private VistaItemViewFilter vistaItemViewFilter = new VistaItemViewFilter();  *(2)*  
    
    // private List<ItemVista> VistaListaItems = new ArrayList<ItemVista>();                        *(3)*  
    
    //@Init Initialize
    public abstract void init();  
    
    //--------------------------------------------------------------------------
    public abstract ListModel<Mant01View> getViewMant01ViewModel();  

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
