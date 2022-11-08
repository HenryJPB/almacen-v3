/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.servicio;

import bean.interfaz.IServicioAdmonSup05existView;
import bean.modelo.entidad.Sup05existViewS;
import bean.modelo.vista.ItemExistViewFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author henrypb
 */
public class ServicioAdmonSup05existView implements IServicioAdmonSup05existView {

    // ----------------
    // * Declarativas *
    // ----------------
    public static List<Sup05existViewS> listaSup05existViewS = new ArrayList<Sup05existViewS>();
    
    //-------------
    // * Metodos *
    //-------------
    //--------------------------------------------------------------------------
    public List<Sup05existViewS> getListaSup05existViewS() {
       listaSup05existViewS = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existViewSentidades(); 
       //System.out.println("*****mi ListaSup05Exist="+listaSup05existViewS+"*********");  
       //System.out.println(Arrays.toString(listaSup05existViewS.toArray()));   // *No funciono*
       //System.out.println("*********Tamaño del Listbox="+listaSup05existViewS.size()+"*************************");
       //for (Sup05existViewS s : listaSup05existViewS ) {
       //    System.out.println("****cod="+s.getCodItem()+"***descrip="+s.getDescripcion()+"existencia="+"*****Uni Medi="+s.getUm()+"**"+s.getExistencia()+"*****");  
       //}
       return (listaSup05existViewS); 
    }  // getListaSup05existViewS()
    
    //--------------------------------------------------------------------------
    public Sup05existViewS[] getAllSup05existViewArray() {
        return ( listaSup05existViewS.toArray( new Sup05existViewS[listaSup05existViewS.size()] ) ); 
    }  // getAllSup05existViewArray()

    //--------------------------------------------------------------------------
    // NOTA: 'ItemExistViewFilter' trata los atributos como valores tipo String. 
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public List<Sup05existViewS> getFilterSup05existView(ItemExistViewFilter itemExistViewFilter) {
        List<Sup05existViewS> someItem = new ArrayList<Sup05existViewS>(); 
        String keyCodItem = itemExistViewFilter.getCodItem().toLowerCase();  
        String keyDescripcion =  itemExistViewFilter.getDescripcion().toLowerCase(); 
        String keyUbicacion = itemExistViewFilter.getUbicacion().toLowerCase(); 
        String keyUm = itemExistViewFilter.getUm().toLowerCase();  
        String keyExistenciaS = itemExistViewFilter.getExistencia();  
        //System.out.println("Claves: Cod= "+keyCodItem+"  descrip= "+keyDescripcion+" ubicacion= "+keyUbicacion+"  um= "+keyUm+" existencia= "+keyExistenciaS+"*****");
        Sup05existViewS tmp = null;
        for ( Iterator<Sup05existViewS> i = listaSup05existViewS.iterator(); i.hasNext() ; ) {
              tmp = i.next();  
              if ( ( tmp.getCodItem()==null?"":tmp.getCodItem().toLowerCase() ).contains(keyCodItem)
                      && ( tmp.getDescripcion()==null?"":tmp.getDescripcion().toLowerCase() ).contains(keyDescripcion) 
                      && ( tmp.getUbicacion()==null?"":tmp.getUbicacion().toLowerCase() ).contains(keyUbicacion) 
                      && ( tmp.getUm()==null?"":tmp.getUm().toLowerCase() ).contains(keyUm)
                      && ( tmp.getExistencia()==null?"":tmp.getExistencia() ).contains(keyExistenciaS) ) {
                  someItem.add(tmp);  
              }  // if. 
        }  // for
        return (someItem); 
    }  // getFilterSup05Vista().  
    
}
