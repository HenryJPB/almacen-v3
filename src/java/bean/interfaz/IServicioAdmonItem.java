/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.interfaz;

import bean.modelo.entidad.Item;
import bean.modelo.vista.ItemViewFilter;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdmonItem {
    
    // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public'  --> 
    // *                     de los contrario se generaran salidas indeseables,.... =)
    // ******************************************************************************************************
    
    public abstract List<Item> getListaItems();
     
    public abstract Item[] getAllListaItemsArray(); 
    
    public abstract List<Item> getFilterItem(ItemViewFilter itemViewFilter);  
    
}
