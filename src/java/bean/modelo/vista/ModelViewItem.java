/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.interfaz.IModelViewItem;
import bean.modelo.entidad.Item;
import bean.servicio.ServicioAdmonItem;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author henrypb
 */
public class ModelViewItem implements IModelViewItem {

    // * Definir atributos de ambito global para esta clase:  
    private Item selectedItem;

    private ItemViewFilter itemViewFilter = new ItemViewFilter();

    private List<Item> listaItems = new ServicioAdmonItem().getListaItems();

    //--------------------------------------------------------------------------
    @Init
    public void init() {
        //listaVariables.clear();
        //selected = currentTrabajadores.get(0); // Selected First One
    }

    //--------------------------------------------------------------------------
    public ListModel<Item> getItemViewModel() {
        return (new ListModelList<Item>(listaItems));
    }

    //--------------------------------------------------------------------------
    public ItemViewFilter getItemViewFilter() {
        return (itemViewFilter);
    }

    //--------------------------------------------------------------------------
    @Command
    @NotifyChange({"itemViewModel", "footer"})    // Anotacion Mandatoria <-> get'ItemViewModel 
    public void changeFilter() {
        listaItems = new ServicioAdmonItem().getFilterItem(itemViewFilter);
    }

    //--------------------------------------------------------------------------
    @Command
    @NotifyChange({"itemViewModel", "footer"})   // Anotacion Mandatorio <-> get'ItemViewModel(
    public void cleanFilter() {
        itemViewFilter.setCodigo("");
        itemViewFilter.setGrupo("");
        itemViewFilter.setDescripcion("");
        itemViewFilter.setCodUbic("");
        itemViewFilter.setUm("");
        itemViewFilter.setNivelControl("");
        itemViewFilter.setStockMin("");    
        itemViewFilter.setStockMax("");
        itemViewFilter.setPtoReorden("");
        itemViewFilter.setIndicador("");
        listaItems = new ServicioAdmonItem().getFilterItem(itemViewFilter);
    }  // cleanFilter()
    
    //--------------------------------------------------------------------------
    public Item getSelectedItem() {
        return selectedItem;
    }

    //--------------------------------------------------------------------------
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    // *************************( Valor agregado )******************************
    //--------------------------------------------------------------------------
    // Bqto: 24 enero 2019 
    // (( Correcto )) 
    // NOTA *importante*  
    // El Item del Listbox debe ser (mandatorio) seleccionado previamente
    //--------------------------------------------------------------------------
    /*
     @Command("btnEliminar")
     public void btnEliminar(@BindingParam("selectedVariable") Variable selectedVariable) {
     if (selectedVariable != null) {
     System.out.println("****selectVar.Codigo=AQUICAAAAAAAAAAAAAAAAAAAAAAAAA***CodVar=" + selectedVariable.getCodVar() + "********");
     } else {
     System.out.println("****selectVar.Codigo=AQUICAAAAAAAAAAAAAAAAAAAAAAAAA***SelectedVariabler=es NULA??????");  // Chequear que el item haya sido seleccionado previamente 
     }
     } // btnEliminar

     //--------------------------------------------------------------------------
     // Bqto: 14 febrero 2019   (( Correcto )) 
     //--------------------------------------------------------------------------
     @Command("refrescarListbox")
     @NotifyChange({"variablesViewModel", "footer"})
     //public void btnRfr() {
     public void refrescarListbox() {
     System.out.println("....Inside refrescarListbox..???????????????*****************");  // Correctisimo 
     listaVariables = new ServicioAdmonFormulaConcepto().getListaVariables();  // ((( Variable de ambito global )))
     } // btnRfr()
     */
}
