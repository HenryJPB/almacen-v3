/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.sup05.Sup05ViewJpaController;
import bean.interfaz.IServicioAdmonSup05Vista;
import bean.modelo.entidad.Sup05Vista;
import bean.modelo.vista.Sup05ViewFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author henrypb NOTA: referencia al objeto vista: ops$dessup03.SUP05_VIEW.
 *
 */

public class ServicioAdmonSup05Vista implements IServicioAdmonSup05Vista {
    
    //******************************************************************************
    //****************** Metodos q aplican a la clase Supu05Vista ******************
    //******************************************************************************
    
    // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public' y  *
    // *                     y 'static' de los contrario se generaran salidas indeseables,.... =)           *
    // ******************************************************************************************************
    
    public static List<Sup05Vista> listaSup05Vista = new ArrayList<Sup05Vista>();
    
        //--------------------------------------------------------------------------
    public List<Sup05Vista> getListaSup05Vista(Date fecha1, Date fecha2) {
        listaSup05Vista = new Sup05ViewJpaController().findItemsSup05Vista(fecha1, fecha2);  
        return (listaSup05Vista);
    }  // getListaSup05View().  

    //--------------------------------------------------------------------------
    public Sup05Vista[] getAllSup05VistaArray() {
        return (listaSup05Vista.toArray(new Sup05Vista[listaSup05Vista.size()]));
    }  // getAllSup05VistaArray()

    //--------------------------------------------------------------------------
    public List<Sup05Vista> getFilterSup05Vista(Sup05ViewFilter sup05ViewFilter) {
        List<Sup05Vista> someItem = new ArrayList<Sup05Vista>();
        String keyCodItem=sup05ViewFilter.getCodItem().toLowerCase();
        String keyDescripcion=sup05ViewFilter.getDescripcion().toLowerCase();
        String keyFechaS=sup05ViewFilter.getFecha();  
        String keyTipoMov=sup05ViewFilter.getTipoMov().toLowerCase();
        String keyCostoS=sup05ViewFilter.getCosto();
        String keyCantRequeridaS=sup05ViewFilter.getCantRequerida();
        String keyUnidadesS=sup05ViewFilter.getUnidades();
        String keyReferencia=sup05ViewFilter.getReferencia().toLowerCase();
        String keyCentroCosto=sup05ViewFilter.getCentroCosto().toLowerCase();
        String keyCodProceso=sup05ViewFilter.getCodProceso().toLowerCase();
        String keyCodMaquina=sup05ViewFilter.getCodMaquina().toLowerCase();
        String keyOrigen=sup05ViewFilter.getOrigen().toLowerCase();
        String keyObservacion = sup05ViewFilter.getObservacion().toLowerCase();
        Sup05Vista tmp = null;  
        Iterator<Sup05Vista> i = listaSup05Vista.iterator();  
        while ( i.hasNext() ) {
            tmp = i.next();  
            if ( ( tmp.getCodItem()==null?"":tmp.getCodItem().toLowerCase() ).contains(keyCodItem)
                    && ( tmp.getDescripcion()==null?"":tmp.getDescripcion().toLowerCase() ).contains(keyDescripcion)
                    && ( tmp.getFechaS()==null?"":tmp.getFechaS() ).contains(keyFechaS)
                    && ( tmp.getTipoMov()==null?"":tmp.getTipoMov() ).toLowerCase().contains(keyTipoMov)
                    && ( tmp.getCostoS()==null?"":tmp.getCostoS() ).contains(keyCostoS)
                    && ( tmp.getCantRequeridaS()==null?"":tmp.getCantRequeridaS() ).contains(keyCantRequeridaS)
                    && ( tmp.getUnidadesS()==null?"":tmp.getUnidadesS() ).contains(keyUnidadesS)
                    && ( tmp.getReferencia()==null?"":tmp.getReferencia() ).toLowerCase().contains(keyReferencia)
                    && ( tmp.getCentroCosto()==null?"":tmp.getCentroCosto() ).toLowerCase().contains(keyCentroCosto)
                    && ( tmp.getCodProceso()==null?"":tmp.getCodProceso() ).toLowerCase().contains(keyCodProceso)
                    && ( tmp.getCodMaquina()==null?"":tmp.getCodMaquina() ).toLowerCase().contains(keyCodMaquina)
                    && ( tmp.getOrigen()==null?"":tmp.getOrigen() ).toLowerCase().contains(keyOrigen)
                    && ( tmp.getObservacion()==null?"":tmp.getObservacion() ).toLowerCase().contains(keyObservacion)  ) {
                someItem.add(tmp);  
            }  // if
        }  // while 
        return (someItem);
    } // getFilterSup05Vista()
    
}
