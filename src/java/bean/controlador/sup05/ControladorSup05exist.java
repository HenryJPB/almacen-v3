/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.sup05;

import bean.modelo.entidad.Sup05Vista;
import bean.modelo.entidad.Sup05existViewS;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author henrypb
 */
public class ControladorSup05exist extends GenericForwardComposer {
    private Button  btnZoom;  
    private Button  btnClr; 
    private Textbox txtFiltroCod; 
    private Textbox txtFiltroDescrip; 
    private Textbox txtFiltroUbic; 
    private Textbox txtFiltroUm; 
    private Textbox txtFiltroExist;  
    private Listbox lbxExistencia;  
    enum enumAtributos {CODIGO,DESCRIPCION,UBICACION,UM,EXISTENCIA}
    
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // << intrucciones del usuario >>
        //certificarUsuario(); 
        //checkEventQueves();  
        //iniciar();
    }
    
    //--------------------------------------------------------------------------
    // NOTA: Esta accion esta supereditado al View Model del Listbox. 
    //       ( ModelViewSup05exist )  
    //--------------------------------------------------------------------------
    public void onClick$btnClr() {
        clearFiltros();  
    }

    //--------------------------------------------------------------------------
    public void onClick$btnZoom() {
        zoomRegSelected();   
    }
    
    //--------------------------------------------------------------------------
    private void clearFiltros() {
        txtFiltroCod.setText("");
        txtFiltroDescrip.setText("");
        txtFiltroUbic.setText(""); 
        txtFiltroUm.setText("");
        txtFiltroExist.setText("");
    }

    //--------------------------------------------------------------------------
    private void loadAtributosItemSelected() {
        Listitem listitem = lbxExistencia.getSelectedItem();  
        Sup05existViewS sup05existViewS = getRegistroSelected(listitem);  
        Sessions.getCurrent().setAttribute("existCod",sup05existViewS.getCodItem()); 
        Sessions.getCurrent().setAttribute("existDescrip",sup05existViewS.getDescripcion());  
        Sessions.getCurrent().setAttribute("existUbic",sup05existViewS.getUbicacion());  
        Sessions.getCurrent().setAttribute("existUm", sup05existViewS.getUm()); 
        Sessions.getCurrent().setAttribute("existS", sup05existViewS.getExistencia());  
    }  // loadAtributosItemSelected()
    
    //--------------------------------------------------------------------------
    private Sup05existViewS getRegistroSelected(Listitem listitem) {
        List celdas = listitem.getChildren(); 
        // Codigo
        Listcell listcell = (Listcell) celdas.get(enumAtributos.CODIGO.ordinal());
        String codigo = listcell.getLabel();  
        // Descripcion
        listcell = (Listcell) celdas.get(enumAtributos.DESCRIPCION.ordinal());  
        String descripcion = listcell.getLabel();  
        // Ubicacion
        listcell = (Listcell) celdas.get(enumAtributos.UBICACION.ordinal());  
        String ubicacion = listcell.getLabel();  
        // Unidad Metrica
        listcell = (Listcell) celdas.get(enumAtributos.UM.ordinal());  
        String um = listcell.getLabel();  
        // Existencia
        listcell = (Listcell) celdas.get(enumAtributos.EXISTENCIA.ordinal());  
        String existenciaS = listcell.getLabel();  
        //
        Sup05existViewS sup05existViewS = new Sup05existViewS(codigo,descripcion,ubicacion,um,existenciaS); 
        return (sup05existViewS);
    }  // loadAtributosItemSelected()
    
    //--------------------------------------------------------------------------
    private void zoomRegSelected() {
        if ( lbxExistencia.isCheckmark() && lbxExistencia.getSelectedCount()>0 ) {    // OJO>>> lbxExistencia.getItemCount()>0 ) es diferente,... 
             loadAtributosItemSelected(); 
             Map<String, Object> parametros = new HashMap<String, Object>();
             Window winEditDialog = (Window) Executions.createComponents("../VISTA_MOV_INV/pagSup05existZoom.zul", null, parametros);
             Sessions.getCurrent().setAttribute("winZoomExist", winEditDialog); 
             winEditDialog.doModal();
        }  else {
            Messagebox.show("ATREVETE A SELECCIONAR UN REGISTRO.", "ATENCION", Messagebox.OK, Messagebox.INFORMATION);
        }    // if-else ( lbxExistencia.isCheckmark()
    }  // zoomRegSelected().  
    
}
