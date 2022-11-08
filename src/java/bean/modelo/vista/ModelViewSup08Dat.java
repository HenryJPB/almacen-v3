/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.interfaz.IModelViewSup08Dat;
import bean.modelo.entidad.Sup08Dat;
import java.util.List;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author henrypb
 */
public class ModelViewSup08Dat implements IModelViewSup08Dat {

     // ** Definir atributos de ambito global para esta clase:   **
    public Sup08Dat selectedProceso;
    
    // public ...ViewFilter => * No aplican filtros para esta clase ***
    
    public List<Sup08Dat> listaProcesos = new bean.controlador.sup08.Sup08DatJpaController().findSup08DatEntities();  
    
    //--------------------------------------------------------------------------
    @Init
    public void init() {
        // ;=) 
    }

    //--------------------------------------------------------------------------
    public ListModel<Sup08Dat> getViewSup08DatModel() {
        return ( new ListModelList<Sup08Dat>(listaProcesos) ); 
    }

    //--------------------------------------------------------------------------
    public Sup08Dat getSelectedProceso() {
        return selectedProceso;
    }

    //--------------------------------------------------------------------------
    public void setSelectedProceso(Sup08Dat selectedProceso) {
        this.selectedProceso = selectedProceso;
    }
    
}
