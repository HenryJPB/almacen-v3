/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.interfaz;

import bean.modelo.entidad.Sup05existViewS;
import bean.modelo.vista.ItemExistViewFilter;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdmonSup05existView {
    
    //-----------------------------------------------------------------------------------------------------------
    // * Vista alterna para gestionar los datos sobre el Listbox e independizar los atributos tipo Date->Datebox 
    // * y/o (Valores numericos ) --> Decimalbox ... =)
    //-----------------------------------------------------------------------------------------------------------
    
       // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public' y  *
    // *                     y 'static' de los contrario se generaran salidas indeseables,.... =)           *
    // ******************************************************************************************************
    //
    // ----------------
    // * Declarativas *
    // ----------------
    //public static List<Sup05existViewS> listaSup05existViewS = new ArrayList<Sup05existViewS>();
    
    
    //-------------
    // * Metodos *
    //-------------
    public abstract List<Sup05existViewS> getListaSup05existViewS();  

    //--------------------------------------------------------------------------
    public abstract Sup05existViewS[] getAllSup05existViewArray();  

    //--------------------------------------------------------------------------
    // NOTA: es indistinto manejar Sup05ViewFilter o un supuesto Sup05VistaFilter
    //       proque ambos tratan los atributos como valores tipo String. 
    //--------------------------------------------------------------------------
    public abstract List<Sup05existViewS> getFilterSup05existView( ItemExistViewFilter itemExistViewFilter );  
        
    
}
