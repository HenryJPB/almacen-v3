/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.sup05;

import bean.modelo.entidad.Sup05DatPK;
import bean.modelo.entidad.Sup05View;
import bean.modelo.entidad.Sup05Vista;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author henrypb
 * 
 */
public class ControladorSup05 extends GenericForwardComposer {

    public Listbox lbxMovAlmacen;
    private Label lblRefrescar;
    private Image imgRefrescar;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnDel;
    private Button btnRegresar;
    private Button btnRfr;
    private Button btnIni;
    private Window winSup05DatEdit;    // Ver -> pagSup05Vista_v2.zul .  
    private Datebox dbxFecha1, dbxFecha2;  
    //  Create los getter y setter de los siguientes argumentos: Esto NO Funciono ????
    /*
     private String codItem; 
     private java.util.Date fecha; 
     private String tipoMov; 
     private String referencia; 
     */

    enum enumAtributos {

        CODIGO_ITEM, DESCRIPCION, FECHA, TIPO_MOV, COSTO, CANT_REQUERIDA, UNIDADES, REFERENCIA, CENTRO_COSTO, COD_PROCESO, COD_MAQUINA, ORIGEN, OBSERVACION
    }

    //--------------------------------------------------------------------------
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // << intrucciones del usuario >>
        //certificarUsuario(); 
        //checkEventQueves();  
        iniciar();
        //java.util.Date d = new java.text.SimpleDateFormat("dd/MM/yyyy").parse("01/06/2020");  
    }

    //--------------------------------------------------------------------------
    private void iniciar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //--------------------------------------------------------------------------
    // onSelect
    // Event: SelectEvent -> Notifies one that the user has selected a new item in the listbox. 
    // https://www.zkoss.org/wiki/ZK_Component_Reference/Data/Listbox
    //--------------------------------------------------------------------------
    public void onSelect$lbxMovAlmacen() {
        //System.out.println("******* onSelect$lbxMovAlmacen  ejecutado correctamente ✓***************");
        //Integer i = lbxMovAlmacen.getSelectedIndex(); //  .getItemCount(); //  .getSelectedCount();  // Cuantos seleccionados
        //EventQueues.lookup("evtQueOnSelect",EventQueues.APPLICATION, true).publish(new Event("onSelect",null,i) );
        //System.out.println("******* onSelect$lbxMovAlmacen  ejecutado correctamente ✓, Seleccionado el Item nro="+i+" enumerado desde 0 (zero)***************");
        setEventQueue(); 
    }

    //--------------------------------------------------------------------------
    public void onClick$btnAdd() {
        agregar();
    }

    //--------------------------------------------------------------------------
    public void onClick$btnEdit() {
        editar();
    } // onClick$btnEdit()

    //--------------------------------------------------------------------------
    public void onClick$btnDel() {
        eliminar();
    } // onClick$btnEdit()

    //--------------------------------------------------------------------------
    public void onClick$btnRfr() {
        imgRefrescar.setVisible(Boolean.FALSE);
        lblRefrescar.setVisible(Boolean.FALSE);
        //
        //System.out.println("--------------------Referscar listbox--------------------------");
        //lbxMovAlmacen.renderAll();
        //
    }  // onClick$btnRfr(). 

    //--------------------------------------------------------------------------
    private void setEventQueue() {
       Integer i = lbxMovAlmacen.getSelectedIndex(); //  .getSelectedCount(); ==> Cuantos seleccionados
       EventQueues.lookup("evtQueOnSelect",EventQueues.APPLICATION, true).publish(new Event("onSelect",null,i) ); 
    } // setEventQueue()
    
    //--------------------------------------------------------------------------
    private void signRefrescar() {
        imgRefrescar.setVisible(Boolean.TRUE);
        lblRefrescar.setVisible(Boolean.TRUE);
    }  // signRefrescar(). 

    //--------------------------------------------------------------------------
    // set Variables de Session para e)ditar(positivo) / e)liminar((En revision ?????????).
    //--------------------------------------------------------------------------
    private void loadAtributosItemSelected() {
        String codItem;
        java.util.Date fecha = null;
        String tipoMov;
        String referencia;
        Listitem listitem = lbxMovAlmacen.getSelectedItem();
        Sup05Vista sup05Vista = getLbxSup05VistaSeleccion(listitem);
        // Crear las variables de sesion para acceder al objeto Sup05Dat de la PopUp window pagSup05DatEdit:
        codItem = sup05Vista.getCodItem();
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(sup05Vista.getFechaS());
        } catch (ParseException ex) {
            Logger.getLogger(ControladorSup05.class.getName()).log(Level.SEVERE, null, ex);
        }
        tipoMov = sup05Vista.getTipoMov();
        referencia = sup05Vista.getReferencia();
        //
        Sessions.getCurrent().setAttribute("codItem", codItem);
        Sessions.getCurrent().setAttribute("fecha", fecha);
        Sessions.getCurrent().setAttribute("tipoMov", tipoMov);
        Sessions.getCurrent().setAttribute("referencia", referencia);
    }  // loadAtributosItemSelected().  

    //------------------------********()*********-------------------------------
    private void editar() {
        if (lbxMovAlmacen.isCheckmark() && lbxMovAlmacen.getSelectedCount() < 1) {
            Messagebox.show("DEBES SELECCIONAR UN REGISTRO.", "ATENCION", Messagebox.OK, Messagebox.INFORMATION);
        } else {
            loadAtributosItemSelected(); // set Variable de sessions. 
            // ----
            Map<String, Object> parametros = new HashMap<String, Object>();
            //parametros.put("VARIABLE_SELECTED",sup05Vista);    // Es requerido ..????? (no creo en este esquema)
            //parametros.put("lblRefrescar", lblRefrescar);    // Es requerido ..????? (no creo)
            //Window winEditDialog = ( Window ) Executions.createComponents("../VISTA_MOV_INV/pagSup05ViewEdit.zul", null, parametros);  
            //******************Eliminame: 
            //String c = (String)Sessions.getCurrent().getAttribute("codItem"); 
            //java.util.Date d = (java.util.Date)Sessions.getCurrent().getAttribute("fecha"); 
            //String t = (String)Sessions.getCurrent().getAttribute("tipoMov");  
            //String r = (String)Sessions.getCurrent().getAttribute("referencia");  
            //System.out.println("***Editar==>Cod="+c+"**d="+d+"**t="+t+"**r="+r+"***"); 
            //**************************************
            parametros.put("lbxMovAlmacen",lbxMovAlmacen);  
            parametros.put("fecha1",dbxFecha1.getValue()); 
            parametros.put("fecha2",dbxFecha2.getValue());  // Pase de parametros 
            Window winEditDialog = (Window) Executions.createComponents("../VISTA_MOV_INV/pagSup05DatEdit.zul", null, parametros);
            //Window winEditDialog = ( Window ) Executions.createComponents("../VISTA_MOV_INV/pagEliminar.zul", null, parametros);  
            Sessions.getCurrent().setAttribute("winSup05DatEdit", winEditDialog);
            winEditDialog.doModal();
            //**
            //Executions.sendRedirect("../VISTA_MOV_INV/pagEliminar.zul"); 
            //Executions.sendRedirect("../VISTA_MOV_INV/pagSup05DatEdit.zul"); 
            //**
            //winSup05DatEdit.doModal();
            signRefrescar();  
        }  // if-else.    
    }  //  editar().

    //--------------------------------------------------------------------------
    private void agregar() {
        // blanquear variables de sesion:  
        Sessions.getCurrent().setAttribute("codItem", null);
        Sessions.getCurrent().setAttribute("fecha", null);   // 
        Sessions.getCurrent().setAttribute("tipoMov", null);
        Sessions.getCurrent().setAttribute("referencia", null);
        // ----
        Map<String, Object> parametros = new HashMap<String, Object>();
        Window winEditDialog = (Window) Executions.createComponents("../VISTA_MOV_INV/pagSup05DatEdit.zul", null, parametros);
        winEditDialog.doModal();
        winEditDialog.setTitle("AGREGAR REGISTRO");
        signRefrescar();  
    }  // agregar().  

    //--------------------------------------------------------------------------
    private void eliminar() {
        if (lbxMovAlmacen.isCheckmark() && lbxMovAlmacen.getSelectedCount() < 1) {
            Messagebox.show("DEBES SELECCIONAR UN REGISTRO.", "ATENCION", Messagebox.OK, Messagebox.ERROR);
        } else {
            //--------------------*Load Atributos del Item to delete*-----------
            String codItem;
            java.util.Date fecha = null;
            String tipoMov;
            String referencia;
            Listitem listitem = lbxMovAlmacen.getSelectedItem();
            Sup05Vista sup05Vista = getLbxSup05VistaSeleccion(listitem);
            codItem = sup05Vista.getCodItem();  // (*)
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(sup05Vista.getFechaS());  // (*)
            } catch (ParseException ex) {
                Logger.getLogger(ControladorSup05.class.getName()).log(Level.SEVERE, null, ex);
            }
            tipoMov = sup05Vista.getTipoMov();   // (*)
            referencia = sup05Vista.getReferencia();   // (*)
            final Sup05DatPK sup05DatPK = new Sup05DatPK(codItem, fecha, tipoMov, referencia);
            final Boolean poseeRegistrosSucesores = new bean.controlador.sup05.Sup05DatJpaController().poseeRegSucesores(codItem, fecha);
            System.out.println("Posee registros q le suceden:" + poseeRegistrosSucesores + "****");
            //------------------------------------------------------------------
            Messagebox.show("DESEAS ELIMINAR ESTE REGISTRO?.", "CONFIRMACION", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
                // Load atributos de los campos claves !!!! -->

                public void onEvent(Event e) throws Exception {
                    //if (e.getName().equals("onYes") && !(new bean.controlador.sup05.Sup05DatJpaController().poseeRegSucesores(codItem, fecha))) {
                    if (e.getName().equals("onYes")) {
                        if (!poseeRegistrosSucesores) {
                            //new bean.controlador.sup05.Sup05DatJpaController().destroy(new Sup05DatPK(codItem, fecha, tipoMov, referencia));
                            new bean.controlador.sup05.Sup05DatJpaController().destroy(sup05DatPK);
                            System.out.println("**** <<TODO CONFORME:: REGISTRO ELIMINADO>> ********");
                            signRefrescar();  
                        } else { // if "onYes".
                            Messagebox.show("LA ELIMINACION NO PROCEDE PORQUE EL ITEM POSEE REGISTROS QUE LE SUCEDEN.","ATENCION", Messagebox.OK, Messagebox.ERROR);
                        }  // if-else.  
                    }  // if ( e.getName()
                } // onEvent

            });   // MessageBox()
        }  // if (lbxMovAlmacen.isCheckmark(),...
    } // eliminar()

//--------------------------------------------------------------------------
    private void eliminar_OLD() {
        Boolean okEliminar = Boolean.FALSE;
        if (lbxMovAlmacen.isCheckmark() && lbxMovAlmacen.getSelectedCount() < 1) {
            Messagebox.show("Debes seleccionar un registro", "ATENCION", Messagebox.OK, Messagebox.ERROR);
        } else {
            loadAtributosItemSelected(); // set Variable de sessions.
            okEliminar = Boolean.TRUE;
            // ****NOTA***********************************************************************
            // La operacion de eliminar se llevara a cabo en el metodo 
            // eliminarRegistro del ModelViewSup05Vista con el proposito de realizar 
            // la anotacion NotifyChange() del listBox despues de ejecutar la operacion ****** 
            // *******************************************************************************
        }  // if-else.  
    } // eliminar_OLD(). 

    //--------------------------------------------------------------------------
    private Sup05View getLbxSup05ViewSeleccion(Listitem itemSelected) {
        List celdas = itemSelected.getChildren();
        //
        Listcell listcell = (Listcell) celdas.get(enumAtributos.CODIGO_ITEM.ordinal());
        String codItem = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.DESCRIPCION.ordinal());
        String descripcion = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.FECHA.ordinal());
        Datebox dbFecha = (Datebox) listcell.getFirstChild();     // OJO: <= get a BigDecimal value
        java.util.Date fecha = dbFecha.getValue();
        //
        listcell = (Listcell) celdas.get(enumAtributos.TIPO_MOV.ordinal());
        String tipoMov = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.COSTO.ordinal());
        Decimalbox dbCosto = (Decimalbox) listcell.getFirstChild();
        BigDecimal costo = dbCosto.getValue();
        //
        listcell = (Listcell) celdas.get(enumAtributos.CANT_REQUERIDA.ordinal());
        BigDecimal cantRequerida = new BigDecimal(listcell.getLabel());
        //
        listcell = (Listcell) celdas.get(enumAtributos.UNIDADES.ordinal());
        BigDecimal unidades = new BigDecimal(listcell.getLabel());
        //
        listcell = (Listcell) celdas.get(enumAtributos.REFERENCIA.ordinal());
        String referencia = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.CENTRO_COSTO.ordinal());
        String centroCosto = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.COD_PROCESO.ordinal());
        String codProceso = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.COD_MAQUINA.ordinal());
        String codMaquina = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.ORIGEN.ordinal());
        String origen = listcell.getLabel();
        //
        // **
        // ***
        //Sup05View sup05View = new Sup05View(codItem,descripcion,fecha,tipoMov,costo,cantRequerida,unidades,referencia,centroCosto,codProceso,codMaquina,origen); 
        return (new Sup05View(codItem, descripcion, fecha, tipoMov, costo, cantRequerida, unidades, referencia, centroCosto, codProceso, codMaquina, origen));
    }  // getLbxSeleccion()

    //--------------------------------------------------------------------------
    // *REMIND*: Estamos usando Sup05Vista como una clase de datos tipo String
    //           integralmente para desplegar los datos en el Listbox ya formateados
    //           y facilitar los esquemas de busqueda del Usuario.  
    //--------------------------------------------------------------------------
    private Sup05Vista getLbxSup05VistaSeleccion(Listitem itemSelected) {
        List celdas = itemSelected.getChildren();
        //
        Listcell listcell = (Listcell) celdas.get(enumAtributos.CODIGO_ITEM.ordinal());
        String codItem = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.DESCRIPCION.ordinal());
        String descripcion = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.FECHA.ordinal());
        String fecha = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.TIPO_MOV.ordinal());
        String tipoMov = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.COSTO.ordinal());
        String costo = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.CANT_REQUERIDA.ordinal());
        String cantRequerida = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.UNIDADES.ordinal());
        String unidades = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.REFERENCIA.ordinal());
        String referencia = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.CENTRO_COSTO.ordinal());
        String centroCosto = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.COD_PROCESO.ordinal());
        String codProceso = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.COD_MAQUINA.ordinal());
        String codMaquina = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.ORIGEN.ordinal());
        String origen = listcell.getLabel();
        //
        listcell = (Listcell) celdas.get(enumAtributos.OBSERVACION.ordinal());  
        String observacion = listcell.getLabel();  
        // **
        // ***
        //Sup05View sup05View = new Sup05View(codItem,descripcion,fecha,tipoMov,costo,cantRequerida,unidades,referencia,centroCosto,codProceso,codMaquina,origen,observacion); 
        return (new Sup05Vista(codItem, descripcion, fecha, tipoMov, costo, cantRequerida, unidades, referencia, centroCosto, codProceso, codMaquina, origen,observacion));
    }  // getLbxSeleccion()

}
