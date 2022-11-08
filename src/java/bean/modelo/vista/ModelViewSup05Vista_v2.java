/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.controlador.sup05.Sup05DatJpaController;
import bean.controlador.sup05.Sup05ViewJpaController;
import bean.interfaz.IModelViewSup05Vista;
import bean.modelo.entidad.Sup05Dat;
import bean.modelo.entidad.Sup05DatPK;
import bean.modelo.entidad.Sup05Vista;
import bean.servicio.ServicioAdmonSup05Vista;
import bean.utilitario.libreria.LibreriaHP;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author henrypb
 * NOTA *  
 * Bqto, 25 de agosto 2020. Esta clase se creo de la fusion de ModelViewSup05Dat + ModelViewSup05DatEdit, 
 * con el proposito de reenderizar / refrescar el Listbox con una opcion fuera del ambito de la pagSup05Dat.zul ---
 * ---> sin embargo NO FUNCIONA ????????????????????????????????????????????????????
 * 
 */
public class ModelViewSup05Vista_v2 implements IModelViewSup05Vista {

    // * Definir atributos de ambito global para esta clase:  
    //--------------------------------------------------------------------------
    // get los atributos de la clave primaria ( pagSup05Vista )
    //--------------------------------------------------------------------------
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
    // get los atributos de la clave primaria ( pagSup05DatEdit )
    //--------------------------------------------------------------------------
    private String codItem = (String) Sessions.getCurrent().getAttribute("codItem");  // **
    private Boolean registroNuevo = ((codItem == null || codItem.isEmpty()) ? Boolean.TRUE : Boolean.FALSE);
    private java.util.Date fecha = null;
    private String tipoMov = null;
    private String referencia = null;
    // (1) **
    private String descripItem = null;
    // (2) **  
    //private Sup05View sup05View = new Sup05View();  
    private Sup05Dat sup05Dat = null;

    {   // Begin block1

        //if (codItem != null && !codItem.isEmpty()) {   // Editar registro con valores => Actuakizar ...
        if (!registroNuevo) {   // Editar registro con valores => Actuakizar ...
            //(ini)-------------------------------------------------------------
            // *NOTA* (Bqto, 20/11/2019 09:24 )las siguiente lineas fueron escritas para probar otro 
            //  metodo de interaccion entre controladores utilizando EventQueues:
            //  "evtQueOnSelect" es publicado en 'ControladorSup05'.  
            EventQueues.lookup("evtQueOnSelect", EventQueues.APPLICATION, true).subscribe(new EventListener() {

                @Override
                public void onEvent(Event e) throws Exception {
                    System.out.println("******Event 'e' en 'ModelViewSup05DatEdit'. Index del item en el lbx=" + e.getData() + "********");
                }
            });
            //--------------------------------------------------------------(fin)
            fecha = (java.util.Date) Sessions.getCurrent().getAttribute("fecha");
            tipoMov = (String) Sessions.getCurrent().getAttribute("tipoMov");
            referencia = (String) Sessions.getCurrent().getAttribute("referencia");
            // (1) **
            descripItem = (new Sup05ViewJpaController().findSup05ViewEntitie(codItem, fecha, tipoMov, referencia)).getDescripcion();       // *NOTA* -> SUP05_VIEW contiene el atributo 'descripcion del Item' / la tabla SUP05_DAT *no*  
            // (2) **  
            //private Sup05View sup05View = new Sup05View();  
            //
            sup05Dat = new Sup05DatJpaController().findSup05Dat(new Sup05DatPK(codItem, fecha, tipoMov, referencia));
        } else {     // ** Registro Nuevo **
            Sup05DatPK pk = new Sup05DatPK("", new Date(), "", "");
            sup05Dat = new Sup05Dat(pk, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, "", "", "", "", new Date(),"");
        }  // if-else. 

    }  // // End block1

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
        System.out.println("***Command set RfrLbx -- fecha1="+fecha1+"****fecha2="+fecha2+"****");
        listaSup05Vista = new ServicioAdmonSup05Vista().getListaSup05Vista(fecha1, fecha2);
        System.out.println("Por aquica .. set Refrescar  ????????????????????"); 
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

    // *////////////////////////////////////////////////////////////////////////
    // Rutinas de ModelViewSup05DatEdit: 
    // *////////////////////////////////////////////////////////////////////////
    
    @Command("btnEditWinClicked")
    public void btnEditWinClicked(@BindingParam("fxWinSup05DatEdit") Window fxWinSup05DatEdit, @BindingParam("codProceso") String codProceso, @BindingParam("codMaquina") String codMaquina, @BindingParam("existencia") BigDecimal existencia) {
        //System.out.println("***************[btnEditClicked] presionado*********************");
        actualizar(fxWinSup05DatEdit, codProceso, codMaquina, existencia);
        //listaSup05Vista = new ServicioAdmonSup05Vista().getListaSup05Vista(fecha1, fecha2);  // NOTA (Bqto. 25/08/2020. Intento de refrescar el Listbox automaticamente ::: Did NOT work ??????)
        //System.out.println("Aquica-----Aquica----Aquica-----"); 
    }  // btnEditWinClicked()

    //--------------------------------------------------------------------------
    //@NotifyChange({"viewSup05VistaModel", "footer"})   // ?????  Naim ... either work!!!
    // .....Que debo hacer para refrescar el Listbox 'LbxMovAlmacen' de forma automatica..????
    private void actualizar(final Window fxWinSup05DatEdit, final String codProceso, final String codMaquina, final BigDecimal existencia) {
        //System.out.println("***************[btnEditClicked] presionado*********************");
        Messagebox.show("CONFORME ?", "CONFIRMACION", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            public void onEvent(Event evento) throws Exception {
                //if (evento.equals("onYes") && datosValido()) {
                if ( evento.getName().equals("onYes") ) {
                    ajustarDatos();
                    //System.out.println("Save? CodItem=" + sup05Dat.getSup05DatPK().getC5Codigo() + "***Fecha=" + sup05Dat.getSup05DatPK().getC5Fecha() + "****TipoMov=" + sup05Dat.getSup05DatPK().getC5TipoMov() + "*****Unidades=" + sup05Dat.getC5Unidades() + "***Proceso="+sup05Dat.getC5CodProceso()+"***CodMaquina="+sup05Dat.getC5CodMaq()+"**Origen="+sup05Dat.getC5Origen()+"***");
                    if (datosValido()) {
                        if (sup05Dat.getSup05DatPK().getC5TipoMov().equals("E") || (sup05Dat.getSup05DatPK().getC5TipoMov().equals("S") && sup05Dat.getC5Unidades().compareTo(existencia) <= 0)) {
                            if ( !registroNuevo ) {
                                new Sup05DatJpaController().edit(sup05Dat);
                                fxWinSup05DatEdit.detach();
                                // ***eliminame**//Probando!! - simular refrescamiento de la pagina ???
                                //String pagInclude = "../VISTA_MOV_INV/pagSup05Vista.zul";  
                                //Sessions.getCurrent().setAttribute("pagInclude",pagInclude);
                                //Include pagActiva = (Include) Sessions.getCurrent().getAttribute("incPag");  
                                //pagActiva.setSrc("../VISTA_MOV_INV/pagSup05Vista.zul");
                                //pagActiva.setSrc("../VISTA_PRINCIPAL/pagAcceso.zul");
                                //Executions.sendRedirect("../VISTA_PRINCIPAL/pagPrincipal.zul");
                                // **************
                                
                            } else if (!registroExiste(sup05Dat)) {
                                //System.out.println("**TODO EN ORDEN !!! -> procede la actualizacion de BD****");
                                new Sup05DatJpaController().create(sup05Dat);
                                fxWinSup05DatEdit.detach();
                            } else {
                                Messagebox.show("REGISTRO DE CLAVE DUPLICADA.", "ERROR", Messagebox.OK, Messagebox.ERROR);
                            }  // if-else : if ( new bean.controlador.sup05.Sup05DatJpaController(),...  
                        } else {
                            Messagebox.show("EL CONSUMO DE UNIDADES SUPERA LA EXISTENCIA.", "ERROR", Messagebox.OK, Messagebox.ERROR);
                        }  // if-else : if (sup05Dat.getSup05DatPK().getC5TipoMov().equals("E") ||,...
                    } else {
                        Messagebox.show("NO ESTA PERMITIDA LA INSERCION DE CAMPOS CLAVES CON VALORES NULOS.", "ERROR", Messagebox.OK, Messagebox.ERROR);
                    }  // if-else : if (datosValido()),...
                    //System.out.println("Registro Agregado!!!!!");
                }
            } // onEvent()

            //------------------------------------------------------------------
            private void ajustarDatos() {
                //**Precision de los atributos: 
                final int PRECI_CENTRO_COSTO = 9;  
                //System.out.println("****TipoMov="+tipoMov+"**CodProceso="+codProceso+"****CodMaquina="+codMaquina+"****");  
                //------Adjust fecha -------- 
                java.util.Date fecha = sup05Dat.getSup05DatPK().getC5Fecha();
                java.util.Date fechaPK = null;
                try {   /* Truco con piquete, de lo contrario el gestor de BD no lo accede ??? REVISAR ??? */
                    String fechaS = new LibreriaHP().formatoFecha.format(fecha);
                    fechaPK = new LibreriaHP().formatoFecha.parse(fechaS);
                    sup05Dat.getSup05DatPK().setC5Fecha(fechaPK);
                } catch (ParseException ex) {
                    Logger.getLogger(ModelViewSup05DatEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
                //-------Adjust tipoMov-----
                if (sup05Dat.getSup05DatPK().getC5TipoMov() == null || sup05Dat.getSup05DatPK().getC5TipoMov().isEmpty()) {
                    sup05Dat.getSup05DatPK().setC5TipoMov("E");  // Ajustado Just by Default. Ver nota de observacion en la pagSup05DatEdit.zul.  
                }
                //------Adjust depart/centro de costo 
                String centroCosto = sup05Dat.getC5Depart();  
                if ( centroCosto != null && !centroCosto.isEmpty() && centroCosto.length()>PRECI_CENTRO_COSTO ) { 
                     sup05Dat.setC5Depart( centroCosto.substring(0, PRECI_CENTRO_COSTO) );
                } // if 
                //------codProceso------
                sup05Dat.setC5CodProceso(codProceso);
                //------Maquina----------
                sup05Dat.setC5CodMaq(codMaquina);
            }  // ajustarDatos(). 

            //------------------------------------------------------------------
            private boolean datosValido() {
                Boolean ok = Boolean.FALSE;
                ok = (sup05Dat.getSup05DatPK().getC5Codigo() == null || sup05Dat.getSup05DatPK().getC5Codigo().isEmpty());
                ok = (ok || sup05Dat.getSup05DatPK().getC5Fecha() == null);
                ok = (ok || sup05Dat.getSup05DatPK().getC5TipoMov() == null || sup05Dat.getSup05DatPK().getC5TipoMov().isEmpty());
                ok = (ok || sup05Dat.getSup05DatPK().getC5Referencia() == null || sup05Dat.getSup05DatPK().getC5Referencia().isEmpty());
                return (!ok);
            }  // datosValido()

        });  // MessageBox(). **************************************************
    }  // actualizar().  -------------------------------------------------------

    //--------------------------------------------------------------------------
    private Boolean registroExiste(Sup05Dat sup05Dat) {
        Boolean ok = Boolean.FALSE;  
        //Sup05Dat registro = new bean.controlador.sup05.Sup05DatJpaController().findSup05Dat(new Sup05DatPK(codItem, fechaPK, tipoMov, referencia));
        Sup05Dat registro = new bean.controlador.sup05.Sup05DatJpaController().findSup05Dat(sup05Dat.getSup05DatPK());
        if (registro != null) {
            //System.out.println("(ModelView)Registro existe: ()-> CodItem=" + kk.getSup05DatPK().getC5Codigo() + "***Fecha=" + kk.getSup05DatPK().getC5Fecha() + "****TipoMov=" + kk.getSup05DatPK().getC5TipoMov() + "*****Referencia=" + kk.getSup05DatPK().getC5Referencia() + "*****");
            ok = Boolean.TRUE;
        }
        return (ok);
    }  // registroExiste().  
    
    
    // *///////////////////////////////////////////////////////////////////////*
    // **********stetter / gettter's  ******************************************
    // *///////////////////////////////////////////////////////////////////////*
    
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
    public String getDescripItem() {
        return descripItem;
    }

    public void setDescripItem(String descripItem) {
        this.descripItem = descripItem;
    }

    public Sup05Dat getSup05Dat() {
        return sup05Dat;
    }

    public void setSup05Dat(Sup05Dat sup05Dat) {
        this.sup05Dat = sup05Dat;
    }
    
}
