/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.item;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author henrypb
 */
public class ControladorSup01 extends GenericForwardComposer {
    
    private Button btnAdd;    
    private Button btnEdit;    
    private Button btnDel;    
    private Button btnClr;    
    private Textbox txtFiltroCod;    
    private Textbox txtFiltroGroup;
    private Textbox txtFiltroDescrip;    
    private Textbox txtFiltroCodUbic;    
    private Textbox txtFiltroUm;    
    private Textbox txtFiltroNivelCtlr;    
    private Textbox txtFiltroSMin;    
    private Textbox txtFiltroSMax;    
    private Textbox txtFiltroPtoReo;    
    private Textbox txtFiltroIndica;    

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // << intrucciones del usuario >>
        //certificarUsuario(); 
        //checkEventQueves();  
        //iniciar();
    }

    //--------------------------------------------------------------------------
    public void onClick$btnAdd() {
        Messagebox.show("NO ESTAS AUTORIZADO PARA REALIZAR ESTA OPERACION. CONSULTA TU DBA.", "ATENCION", Messagebox.OK, Messagebox.EXCLAMATION);
    }  // onClick$btnAdd()

    //--------------------------------------------------------------------------
    public void onClick$btnEdit() {
        Messagebox.show("NO ESTAS AUTORIZADO PARA REALIZAR ESTA OPERACION. CONSULTA TU DBA.", "ATENCION", Messagebox.OK, Messagebox.EXCLAMATION);
    }  // onClick$btnEdit()

    //--------------------------------------------------------------------------
    public void onClick$btnDel() {
        Messagebox.show("NO ESTAS AUTORIZADO PARA REALIZAR ESTA OPERACION. CONSULTA TU DBA.", "ATENCION", Messagebox.OK, Messagebox.EXCLAMATION);
    }  // onClick$btnDel()

    //--------------------------------------------------------------------------
    public void onClick$btnClr() {
        clearFiltros();        
    }  // onClick$btnClr()

    //--------------------------------------------------------------------------
    private void clearFiltros() {
        txtFiltroCod.setText("");        
        txtFiltroGroup.setText("");
        txtFiltroDescrip.setText("");        
        txtFiltroCodUbic.setText("");        
        txtFiltroUm.setText("");        
        txtFiltroNivelCtlr.setText("");        
        txtFiltroSMin.setText("");        
        txtFiltroSMax.setText("");        
        txtFiltroPtoReo.setText("");        
        txtFiltroIndica.setText("");        
    }  // clearFiltros(). 
    
}
