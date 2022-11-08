/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.vista;

import bean.interfaz.IModelViewSup05existView;
import bean.modelo.entidad.Sup05existViewS;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

/**
 *
 * @author henrypb
 */
public class ModelViewSup05existView implements IModelViewSup05existView {
    
    // ---------------
    // *Declarativas:  
    // ---------------
    // 
    private Sup05existViewS selectedItem;    // --> *No Aplica*  // (falso->Obligatorio || con su getter/setter)  ?????  (1)
    //
    //  *NOTA* : Al siguiente atributo es de tratamiento tipo Cadena/String:   
    private ItemExistViewFilter itemExistViewFilter = new ItemExistViewFilter();    
    //
    // private List<Sup05existView> listaExistItems = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existViewEntities();  
    //  
    //-------------------------------*IMPORTANTE*-------------------------------
    // NOTA: a este tipo de vistas de datos selectivos filtrados para Consultas, 
    //       le vamos a dar el tratamiento de valores tipo String ****
    //--------------------------------------------------------------------------
    //private List<Sup05existViewS> listaExistItems = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existViewSentidades(); 
    private List<Sup05existViewS> listaExistItems = new bean.servicio.ServicioAdmonSup05existView().getListaSup05existViewS();  
    
    // ---------
    // *Metodos:
    // ---------
    @Init
    @Override
    public void init() {
        // <<*Initialize*>>  
    }  // init()

    //--------------------------------------------------------------------------
    @Override
    public ListModel<Sup05existViewS> getSup05existViewModel() {
        return( new ListModelList<Sup05existViewS>(listaExistItems) ); 
    }  // getSup05existViewModel(). 

    //--------------------------------------------------------------------------
    @Override
    public ItemExistViewFilter getItemExistViewFilter() {
        return (itemExistViewFilter); 
    }  // getItemExistViewFilter().  

    //--------------------------------------------------------------------------
    @Command
    @NotifyChange({"sup05existViewModel", "footer"})   // Anotacion Mandatorio <-> get'Sup05existViewModel
    @Override
    public void changeFilter() {
        //System.out.println("*Antes***lista.size="+listaExistItems.size()+"*****");
        listaExistItems = new bean.servicio.ServicioAdmonSup05existView().getFilterSup05existView(itemExistViewFilter); 
        //System.out.println("*despues***lista.size="+listaExistItems.size()+"*****");    
    }  // changeFilter().  
    
    
    //--------------------------------------------------------------------------
    @Command
    @NotifyChange({"sup05existViewModel", "footer"})   // Anotacion Mandatorio <-> get'Sup05existViewModel
    public void cleanFilter() {
        itemExistViewFilter.setCodItem("");
        itemExistViewFilter.setDescripcion("");
        itemExistViewFilter.setUbicacion("");
        itemExistViewFilter.setUm("");
        itemExistViewFilter.setExistencia("");
        listaExistItems = new bean.servicio.ServicioAdmonSup05existView().getFilterSup05existView(itemExistViewFilter); 
    }  // cleanFilter()

    
    //--------------------------------------------------------------------------
    @Command("zoomRegSelected")
    public void zoomRegSelected( @BindingParam("varLbxExistencia") Listbox lbxExistencia )   {
        System.out.println("Usuario a presionado for Zooming,.... Nro Item selected="); 
    }  // zoomRegSelected().  
    
    public Sup05existViewS getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Sup05existViewS selectedItem) {
        this.selectedItem = selectedItem;
    }
    
}
