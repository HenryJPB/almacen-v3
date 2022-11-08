/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.interfaz;

import bean.modelo.entidad.Sup05Vista;
import bean.modelo.vista.Sup05ViewFilter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdmonSup05Vista {
    
    //-----------------------------------------------------------------------------------------------------------
    // * Vista alterna para gestionar los datos sobre el Listbox e independizar los atributos tipo Date->Datebox 
    // * y/o (Valores numericos ) --> Decimalbox ... =)
    //-----------------------------------------------------------------------------------------------------------
    
    // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public' y  *
    // *                     y 'static' de los contrario se generaran salidas indeseables,.... =)           *
    // ******************************************************************************************************
    
    // --------------------
    // * Declarativas *
    //---------------------
    // public static List<Sup05Vista> listaSup05Vista = new ArrayList<Sup05Vista>();
    
     //-------------
    // * Metodos *
    //-------------
    public abstract List<Sup05Vista> getListaSup05Vista(Date fecha1, Date fecha2);  

    //--------------------------------------------------------------------------
    public Sup05Vista[] getAllSup05VistaArray();  

    //--------------------------------------------------------------------------
    // NOTA: es indistinto manejar Sup05ViewFilter o un supuesto Sup05VistaFilter
    //       proque ambos tratan los atributos como valores tipo String. 
    //--------------------------------------------------------------------------
    public abstract List<Sup05Vista> getFilterSup05Vista(Sup05ViewFilter sup05VistaFilter);  
        
    
}
