/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfaz;

import bean.modelo.entidad.Sup05View;
import bean.modelo.entidad.Sup05Vista;
import bean.modelo.vista.Sup05ViewFilter;
import org.zkoss.zul.ListModel;

/**
 *
 * @author henrypb
 * NOTA: referencia al objeto vista: ops$dessup03.SUP05_VIEW. 
 * 
 */
public interface IModelViewSup05View {

    // Definir atributos de ambito global para esta clase:  
    
    // private ItemVista selectedItemVista;                                          *(1)*
    
    // private VistaItemViewFilter vistaItemViewFilter = new VistaItemViewFilter();  *(2)*  
    
    //************************SUP05_VIEW ==> Sup05View *************************
    
    //@Init Initialize
    //@Init
    public abstract void init();  
    
    //--------------------------------------------------------------------------
    public abstract ListModel<Sup05View> getViewSup05ViewModel();  

    //--------------------------------------------------------------------------
    public abstract Sup05ViewFilter getViewSup05ViewFilter();
    
    //--------------------------------------------------------------------------
    //@Command
    //@NotifyChange({"variablesViewModel", "footer"})
    public abstract void changeViewFilter();  
    
    // .
    // ..    ** IMPORTANTE **
    // generar los getter y los setter del atributo: selectedItem
    // ...

}
