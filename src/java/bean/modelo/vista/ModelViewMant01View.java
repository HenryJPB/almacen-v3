/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.vista;

import bean.interfaz.IModelViewMant01View;
import bean.modelo.entidad.Mant01View;
import java.util.List;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author henrypb
 */
public class ModelViewMant01View implements IModelViewMant01View {

    public Mant01View selectedMaquina;  
    
    public List<Mant01View>  listaMaquinas = new bean.controlador.mantenimiento.Mant01ViewJpaController().findMant01ViewEntities();  
    
    //--------------------------------------------------------------------------
    @Init 
    public void init() {
        // ;=)
    }

    //--------------------------------------------------------------------------
    public ListModel<Mant01View> getViewMant01ViewModel() {
        return ( new ListModelList<Mant01View>(listaMaquinas) );  
    }

    //--------------------------------------------------------------------------
    public Mant01View getSelectedMaquina() {
        return selectedMaquina;
    }

    //--------------------------------------------------------------------------
    public void setSelectedMaquina(Mant01View selectedMaquina) {
        this.selectedMaquina = selectedMaquina;
    }

}
