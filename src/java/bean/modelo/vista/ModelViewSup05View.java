/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.interfaz.IModelViewSup05View;
import bean.modelo.entidad.Sup05View;
import bean.servicio.ServicioAdmonSup05View;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author henrypb
 */
public class ModelViewSup05View implements IModelViewSup05View {

    // * Definir atributos de ambito global para esta clase:  
    private java.util.Date fecha1;

    private java.util.Date fecha2;

    private Sup05View selectedSup05View;

    private Sup05ViewFilter sup05ViewFilter = new Sup05ViewFilter();

    {
        Calendar fecha = Calendar.getInstance();    // SysDate. 
        fecha1 = fecha.getTime();
        fecha.add(Calendar.DATE, -2);                // Restar 2 dia,... 
        fecha2 = fecha.getTime();
        try {
            //java.util.Date fecha = (java.util.Date) ( new SimpleDateFormat("dd/MM/yyyy") ).format("01/04/2018");
            fecha2 = fecha1;
            fecha1 = (new SimpleDateFormat("dd/MM/yyyy")).parse("01/04/2018");
        } catch (ParseException ex) {
            Logger.getLogger(ModelViewSup05View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Sup05View> listaSup05View = new ServicioAdmonSup05View().getListaSup05View(fecha1, fecha2);
    

    //******************************Sup05View***********************************
    
    //--------------------------------------------------------------------------
    @Init
    public void init() {
        //selected = currentTrabajadores.get(0); // Selected First One  ( Example )
    }

    //--------------------------------------------------------------------------
    public ListModel<Sup05View> getViewSup05ViewModel() {
        return (new ListModelList<Sup05View>(listaSup05View));
    }  // getViewSup05ViewModel(). 

    //--------------------------------------------------------------------------
    public Sup05ViewFilter getViewSup05ViewFilter() {
        return (sup05ViewFilter);
    }  // getViewSup05ViewFilter().

    //--------------------------------------------------------------------------
    @Command
    @NotifyChange({"viewSup05ViewModel", "footer"})    // // Anotacion Mandatoria <-> get'ViewSup05ViewModel
    public void changeViewFilter() {
        listaSup05View = new ServicioAdmonSup05View().getFilterSup05View(sup05ViewFilter);
    }  // changeFilter()

    //--------------------------------------------------------------------------
    public Sup05View getSelectedSup05View() {
        return selectedSup05View;
    }

    //--------------------------------------------------------------------------
    public void setSelectedSup05View(Sup05View selectedSup05View) {
        this.selectedSup05View = selectedSup05View;
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
