/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.interfaz;

import bean.modelo.entidad.Sup05existViewS;
import bean.modelo.vista.ItemExistViewFilter;
import org.zkoss.zul.ListModel;

/**
 *
 * @author henrypb
 */
public interface IModelViewSup05existView {
    
    // ---------------
    // *Declarativas:  
    // ---------------
    // 
    //  private Item selectedItem;    // --> *Obligatorio*      (1)
    //
    //  *NOTA* : Al siguiente atributo es de tratamiento tipo Cadena/String:   
    //  private ItemExistViewFilter itemExistViewFilter = new ItemExistViewFilter():  
    // 
    //  private List<Sup05existView> listaExistItems = new bean.controlador..[]
    //
    // ---------
    // *Metodos:
    // ---------
    //--------------------------------------------------------------------------
    //@Init Initialize
    //--------------------------------------------------------------------------
    //@Init
    public abstract void init();  
    
    //--------------------------------------------------------------------------
    public abstract ListModel<Sup05existViewS> getSup05existViewModel();
    
    //--------------------------------------------------------------------------
    public abstract ItemExistViewFilter getItemExistViewFilter();   
    
    //--------------------------------------------------------------------------
    //@Command
    //@NotifyChange({"sup05existViewModel", "footer"})   // Anotacion Mandatorio <-> get'Sup05existViewModel
    public abstract void changeFilter();  
    
    // ..   ** IMPORTANTE **
    // generar los getter y los setter del atributo: selectedItem
    // ...
    
}
