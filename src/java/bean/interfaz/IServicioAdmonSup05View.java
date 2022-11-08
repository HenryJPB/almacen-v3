/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.interfaz;

import bean.modelo.entidad.Sup05View;
import bean.modelo.vista.Sup05ViewFilter;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdmonSup05View {
    
    // * Vista original *
    // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public' y  *
    // *                     y 'static' de los contrario se generaran salidas indeseables,.... =)           *
    // ******************************************************************************************************
    
    public abstract List<Sup05View> getListaSup05View(java.util.Date fecha1, java.util.Date fecha2 );  
    
    public abstract Sup05View[] getAllSup05ViewArray(); 
    
    public abstract List<Sup05View> getFilterSup05View(Sup05ViewFilter sup05ViewFilter);  
    
}
