/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.interfaz.IModelViewSup05Vista;
import bean.modelo.entidad.Sup05Vista;
import bean.servicio.ServicioAdmonSup05Vista;
import java.util.Calendar;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author henrypb
 */
public class ModelViewSup05Vista_1 implements IModelViewSup05Vista {

    // * Definir atributos de ambito global para esta clase:  
    private java.util.Date fecha1;

    private java.util.Date fecha2;

    private Sup05ViewFilter sup05VistaFilter = new Sup05ViewFilter();

    {
        Calendar fecha = Calendar.getInstance();    // SysDate. 
        fecha1 = fecha.getTime();
        //fecha.add(Calendar.DATE, -1);             // Restar 1 dia,... 
        //fecha2 = fecha.getTime();
        fecha2 = fecha1;

        //*  Eliminame.begin *********************************************************
        /*
         fecha2 = fecha1;
         try {
         fecha1 = (new SimpleDateFormat("dd/MM/yyyy")).parse("14/05/2018");
            
         } catch (java.text.ParseException ex) {
         Logger.getLogger(ModelViewSup05Vista.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        //*  Eliminame.end *********************************************************
    }

    //*****************************Sup05Vista***********************************
    private Sup05Vista selectedSup05Vista;

    public List<Sup05Vista> listaSup05Vista = new ServicioAdmonSup05Vista().getListaSup05Vista(fecha1, fecha2);

    //--------------------------------------------------------------------------
    @Init
    public void init() {
        //selected = currentTrabajadores.get(0); // Selected First One  ( Example )
    }

    //--------------------------------------------------------------------------
    @Override
    public ListModel<Sup05Vista> getViewSup05VistaModel() {
        return (new ListModelList<Sup05Vista>(listaSup05Vista));
    }

    //--------------------------------------------------------------------------
    @Override
    public Sup05ViewFilter getViewSup05VistaFilter() {
        return (sup05VistaFilter);
    }

    //--------------------------------------------------------------------------
    // NOTA: es indiferente manejar Sup05ViewFilter o un supuesto Sup05VistaFilter
    //       proque ambos tratan los atributos como valores tipo String. 
    //--------------------------------------------------------------------------
    @Command
    @NotifyChange({"viewSup05VistaModel", "footer"})    // Anotacion Mandatoria <-> get'ViewSup05VistaModel de esta clase 
    @Override
    public void changeVistaFilter() {
        listaSup05Vista = new ServicioAdmonSup05Vista().getFilterSup05Vista(sup05VistaFilter);
    }

    //--------------------------------------------------------------------------
    // (Bqto: 17 cotubre 2019 )Eeeeeeeeexiiiiiitooooo =)
    //--------------------------------------------------------------------------
    @Command("setRefrescarLbx")
    @NotifyChange({"viewSup05VistaModel", "footer"})   // hace referencia a: get'ViewSup05VistaModel() de esta clase
    public void setRefrescarLbx(@BindingParam("fecha1") java.util.Date fecha1, @BindingParam("fecha2") java.util.Date fecha2) {
        //System.out.println("***Command set RfrLbx -- fecha1="+fecha1+"****fecha2="+fecha2+"****");
        listaSup05Vista = new ServicioAdmonSup05Vista().getListaSup05Vista(fecha1, fecha2);
    }  // setRefrescarLbx().

    //--------------------------------------------------------------------------
    // *NOTA*: (1) No se activó '@NotifyChange({"viewSup05VistaModel", "footer"})'
    //         ** Sujeto a Revision **  
    //         (2) Se coloco el siguiente metodo en Stand by, debido a que se estaba 
    //             generando Salidas Indeseables  ( Bqto. 01/11/2019 09:37:00    
    //--------------------------------------------------------------------------
    @Command("eliminarRegistro")
    @NotifyChange({"viewSup05VistaModel", "footer"})   // hace referencia a: get'ViewSup05VistaModel() de esta clase. 
    public void eliminarRegistro() {
        eliminar();
    }  // eliminarRegistro(). 

    //--------------------------------------------------------------------------
    // NOTA:El siguiente metodo en Stand by, debido a que se estaba 
    //      generando Salidas Indeseables  ( Bqto. 01/11/2019 09:37:00    
    //--------------------------------------------------------------------------
    private void eliminar() {
        //Sup05DatJpaController sup05DatJpaController = new Sup05DatJpaController();  
        //System.out.println("****ESTOY EN EL METODO elminarRegistro del ModelView ********");
        final String codItemToDel = (String) Sessions.getCurrent().getAttribute("codItem");
        final java.util.Date fecha = (java.util.Date) Sessions.getCurrent().getAttribute("fecha");
        final String tipoMov = (String) Sessions.getCurrent().getAttribute("tipoMov");
        final String referencia = (String) Sessions.getCurrent().getAttribute("referencia");
        Boolean okEliminar = (Boolean) Sessions.getCurrent().getAttribute("okEliminar");
        //System.out.println("****Cod Item to delete=" + codItemToDel + "***fecha=" + fecha + "****tipoMov=" + tipoMov + "****ref=" + referencia + "*****kill out este reg?:" + okEliminar + "**");
        if (okEliminar) {
            Messagebox.show("Deseas eliminar este registro?", "CONFIRMACION", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {

                public void onEvent(Event e) throws Exception {
                    if (e.getName().equals("onYes") && !(new bean.controlador.sup05.Sup05DatJpaController().poseeRegSucesores(codItemToDel, fecha))) {
                        //try {
                        //    new bean.controlador.sup05.Sup05DatJpaController().destroy(new Sup05DatPK(codItemToDel, fecha, tipoMov, referencia));
                        //} catch (NonexistentEntityException ex) {
                        //    Logger.getLogger(ModelViewSup05Vista.class.getName()).log(Level.SEVERE, null, ex);
                        //}
                        System.out.println("****REGISTRO ELIMINADO en elminarRegistro del ModelView ********");
                    } else { // if "onYes".  
                        Messagebox.show("NO procede la eliminacion porque el Item posee registros Sucesores.", "ATENCION", Messagebox.OK, Messagebox.ERROR);
                    }
                }  // onEvent
                
            });   // MessageBox()
            //Sessions.getCurrent().setAttribute("okEliminar", Boolean.FALSE);
        }  // if ( okEliminar )
    }  // eliminar().    

    //--------------------------------------------------------------------------
    public Sup05Vista getSelectedSup05Vista() {
        return selectedSup05Vista;
    }

    //--------------------------------------------------------------------------
    public void setSelectedSup05Vista(Sup05Vista selectedSup05Vista) {
        this.selectedSup05Vista = selectedSup05Vista;
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
     @NotifyChange({"variablesViewModel", "footer"})  // Imprescindible  
     //public void btnRfr() {
     public void refrescarListbox() {
     System.out.println("....Inside refrescarListbox..???????????????*****************");  // Correctisimo 
     listaVariables = new ServicioAdmonFormulaConcepto().getListaVariables();  // ((( Variable de ambito global )))
     } // btnRfr()
     */
}
