/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.vista;

import bean.interfaz.IModelViewSup05existZoom;
import bean.modelo.entidad.Sup05existView;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

/**
 *
 * @author henrypb
 */
public class ModelViewSup05existZoom implements IModelViewSup05existZoom {

    String codigo = (String) Sessions.getCurrent().getAttribute("existCod"); 
    
    private Sup05existView sup05existView = null;  
    
    {
       sup05existView = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existView(codigo);  
    }
    
    @Override
    @Init
    public void init() {
        // null. 
    }

    public Sup05existView getSup05existView() {
        return sup05existView;
    }

    public void setSup05existView(Sup05existView sup05existView) {
        this.sup05existView = sup05existView;
    }
    
    
}
