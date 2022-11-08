/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfaz;

import bean.modelo.entidad.Sup05Vista;
import bean.modelo.vista.Sup05ViewFilter;
import org.zkoss.zul.ListModel;

/**
 *
 * @author henrypb
 * NOTA: referencia al objeto vista: ops$dessup03.SUP05_VIEW. 
 * 
 */
public interface IModelViewSup05Vista {

    // Definir atributos de ambito global para esta clase:  
    
    // private ItemVista selectedItemVista;                                          *(1)*
    
    // private VistaItemViewFilter vistaItemViewFilter = new VistaItemViewFilter();  *(2)*  
    
    // private List<ItemVista> VistaListaItems = new ArrayList<ItemVista>();                        *(3)*  
    
    //************************SUP05_VIEW ==> Sup05Vista ************************
    // NOTA: todos los atributos de esta clase son tratados tipo String para 
    //       gestionar el Listbox y saltarce el paso de atributos con Date y/o Decimal (box ).  
    //**************************************************************************
    
    
    //@Init Initialize
    //@Init
    public abstract void init();  
    
    //--------------------------------------------------------------------------
    public abstract ListModel<Sup05Vista> getViewSup05VistaModel();  

    //--------------------------------------------------------------------------
    // NOTA: es indistinto manejar Sup05ViewFilter o un supuesto Sup05VistaFilter
    //       proque ambos tratan los atributos como valores tipo String. 
    //--------------------------------------------------------------------------
    public abstract Sup05ViewFilter getViewSup05VistaFilter();
    
     //@Command
    //@NotifyChange({"viewSup05VistaModel", "footer"})  // Anotacion mandatoria <-> get'ViewSup05VistaModel
    public abstract void changeVistaFilter();  
    
    // .
    // ..    ** IMPORTANTE **
    // generar los getter y los setter del atributo: selectedItem
    // ...
    
}
