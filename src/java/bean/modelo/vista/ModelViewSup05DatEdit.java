/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.controlador.sup05.Sup05DatJpaController;
import bean.controlador.sup05.Sup05ViewJpaController;
import bean.interfaz.IModelViewSup05DatEdit;
import bean.modelo.entidad.Sup05Dat;
import bean.modelo.entidad.Sup05DatPK;
import bean.utilitario.libreria.LibreriaHP;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author henrypb
 */
public class ModelViewSup05DatEdit implements IModelViewSup05DatEdit {

    /*  *** Esto *NO FUNCIONO **
     ControladorSup05 ctlrSup05 = new ControladorSup05();  
     private String codItem = ctlrSup05.getCodItem();  
     private java.util.Date fecha = ctlrSup05.getFecha();  
     private String tipoMov = ctlrSup05.getTipoMov();  
     private String referencia =  ctlrSup05.getReferencia();  
     */
    // get los atributos de la clave primaria,.... 
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
        if ( !registroNuevo ) {   // Editar registro con valores => Actuakizar ...
            //(ini)-------------------------------------------------------------
            // *NOTA* (Bqto, 20/11/2019 09:24 )las siguiente lineas fueron escritas para probar otro 
            //  metodo de interaccion entre controladores utilizando EventQueues:
            //  "evtQueOnSelect" es publicado en 'ControladorSup05'.  
            EventQueues.lookup("evtQueOnSelect",EventQueues.APPLICATION, true).subscribe( new EventListener() {

                @Override
                public void onEvent(Event e) throws Exception {
                     System.out.println("******Event 'e' en 'ModelViewSup05DatEdit'. Index del item en el lbx="+e.getData()+"********");
                }
            } );
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
        // Initialize();  
    }

    //--------------------------------------------------------------------------
    //!  *NOTA IMPORTANTE* bbxProceso y bbxMaquina no hacen 'bind' con los atributos 
    //    sup05Dat.c5CodProceso y sup05Dat.c5CodMaq respectivamente por lo cual me vi 
    //    obligado a pasarlo como parametro en 'btnEdit' de la pag y ajustarlo aqui. 
    //   *OBSERVACION1* -> tampoco se activa para el widGet rdgTipoMov.getSelectedItem(). 
    //   *OBSERVACION2* -> Estos widgets en cuestion solo se activan cuando ocurre
    //    un Evento onChange dirigido x el Usuario en la forma ( pag.zul ). 
    //
    //    NOTA2: 
    ///--------------------------------------------------------------------------
    
    @Command("btnEditWinClicked")
    public void btnEditWinClicked(@BindingParam("fxWinSup05DatEdit") Window fxWinSup05DatEdit, @BindingParam("codProceso") String codProceso, @BindingParam("codMaquina") String codMaquina, @BindingParam("existencia") BigDecimal existencia) {
        //System.out.println("***************[btnEditClicked] presionado*********************");
        actualizar(fxWinSup05DatEdit, codProceso, codMaquina, existencia);
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

    //--------------------------------------------------------------------------
    private Boolean registroExiste_OLD(Sup05Dat sup05Dat) {
        //System.out.println("*******metdodo registroExiste*******"); 
        Boolean ok = Boolean.FALSE;
        String codItem = sup05Dat.getSup05DatPK().getC5Codigo();
        java.util.Date fecha = sup05Dat.getSup05DatPK().getC5Fecha();
        java.util.Date fechaPK = null;
        try {   /* Truco con piquete, de lo contrario el gestor de BD no lo accede ??? REVISAR ??? */

            String fechaS = new LibreriaHP().formatoFecha.format(fecha);
            fechaPK = new LibreriaHP().formatoFecha.parse(fechaS);
        } catch (ParseException ex) {
            Logger.getLogger(ModelViewSup05DatEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tipoMov = sup05Dat.getSup05DatPK().getC5TipoMov();
        String referencia = sup05Dat.getSup05DatPK().getC5Referencia();
        Sup05Dat registro = new bean.controlador.sup05.Sup05DatJpaController().findSup05Dat(new Sup05DatPK(codItem, fechaPK, tipoMov, referencia));
        if (registro != null) {
            //System.out.println("(ModelView)Registro existe: ()-> CodItem=" + kk.getSup05DatPK().getC5Codigo() + "***Fecha=" + kk.getSup05DatPK().getC5Fecha() + "****TipoMov=" + kk.getSup05DatPK().getC5TipoMov() + "*****Referencia=" + kk.getSup05DatPK().getC5Referencia() + "*****");
            ok = Boolean.TRUE;
        }
        return (ok);
    }  // registroExiste_OLD().  
    
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
