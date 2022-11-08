/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.sup05.Sup05ViewJpaController;
import bean.interfaz.IServicioAdmonSup05View;
import bean.modelo.entidad.Sup05View;
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

public class ServicioAdmonSup05View implements IServicioAdmonSup05View {
    
    // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public'  --> 
    // *                     de los contrario se generaran salidas indeseables,.... =)                      * 
    // ******************************************************************************************************
    public static List<Sup05View> listaSup05View = new ArrayList<Sup05View>();

    //--------------------------------------------------------------------------
    public List<Sup05View> getListaSup05View(Date fecha1, Date fecha2) {
        listaSup05View = new Sup05ViewJpaController().findItemsSup05View(fecha1, fecha2);   // Named Query de esta clase <-
        return (listaSup05View);
    }  // getListaSup05View().  

    //--------------------------------------------------------------------------
    public Sup05View[] getAllSup05ViewArray() {
        return (listaSup05View.toArray(new Sup05View[listaSup05View.size()]));
    }  // getAllSup05ViewArray()

    //--------------------------------------------------------------------------
    public List<Sup05View> getFilterSup05View(Sup05ViewFilter sup05ViewFilter) {
        List<Sup05View> someItem = new ArrayList<Sup05View>();
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
        Sup05View tmp = null;  
        Iterator<Sup05View> i = listaSup05View.iterator();  
        while ( i.hasNext() ) {
            tmp = i.next(); 
            if ( ( tmp.getCoditem()==null?"":tmp.getCoditem().toLowerCase() ).contains(keyCodItem)
                    && ( tmp.getDescripcion()==null?"":tmp.getDescripcion().toLowerCase() ).contains(keyDescripcion)
                    && ( tmp.getFecha()==null?"":tmp.getFecha().toString() ) .contains(keyFechaS)
                    && ( tmp.getTipomov()==null?"":tmp.getTipomov().toLowerCase() ).contains(keyTipoMov)
                    && ( tmp.getCosto()==null?"": tmp.getCosto().toString() ).contains(keyCostoS)
                    && ( tmp.getCantrequerida()==null?"":tmp.getCantrequerida().toString() ).contains(keyCantRequeridaS)
                    && ( tmp.getUnidades()==null?"":tmp.getUnidades().toString() ).contains(keyUnidadesS)
                    && ( tmp.getReferencia()==null?"":tmp.getReferencia().toLowerCase() ).contains(keyReferencia)
                    && ( tmp.getCentrocosto()==null?"":tmp.getCentrocosto().toLowerCase() ).contains(keyCentroCosto)
                    && ( tmp.getCodmaquina()==null?"":tmp.getCodmaquina().toLowerCase() ).contains(keyCodMaquina)
                    && ( tmp.getOrigen()==null?"":tmp.getOrigen().toLowerCase() ).contains(keyOrigen) ) {
                someItem.add(tmp);  
            }  // if
        }  // while 
        return (someItem);
    } // getFilterSup05View().  
    
    
}
