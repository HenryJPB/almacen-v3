/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfaz;

import bean.modelo.entidad.Item;
import bean.modelo.vista.ItemViewFilter;
import org.zkoss.zul.ListModel;

/**
 *
 * @author henrypb
 * NOTA: referencia al objeto tabla: ops$dessup03.sup01_dat.
 */
public interface IModelViewItem {

    // * Definir atributos de ambito global para esta clase:  
    
    // private Item selectedItem;
    
    // private ItemViewFilter itemViewFilter = new ItemViewFilter();  *(2)*  
    
    // private List<Item> listaItems = new ArrayList<Item>();         *(3)*  
    
    //--------------------------------------------------------------------------
    //@Init Initialize
    //--------------------------------------------------------------------------
    //@Init
    public abstract void init();  
   
    //--------------------------------------------------------------------------
    public abstract ListModel<Item> getItemViewModel();  

    //--------------------------------------------------------------------------
    public abstract ItemViewFilter getItemViewFilter();
    
    //--------------------------------------------------------------------------
    //@Command
    //@NotifyChange({"itemViewModel", "footer"})   // Anotacion Mandatorio <-> 'get'ItemViewModel
    public abstract void changeFilter();  
    
    // ..   ** IMPORTANTE **
    // generar los getter y los setter del atributo: selectedItem
    // ...

}
