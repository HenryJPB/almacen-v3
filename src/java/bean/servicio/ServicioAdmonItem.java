/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.item.Sup01DatJpaController;
import bean.interfaz.IServicioAdmonItem;
import bean.modelo.entidad.Item;
import bean.modelo.entidad.Sup01Dat;
import bean.modelo.vista.ItemViewFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author henrypb
 *
 */
public class ServicioAdmonItem implements IServicioAdmonItem {

    // ******************************************************************************************************
    // * NOTA IMPORTANTE * : Es mandatorio que las siguientes atributos/variables sean del tipo 'Public' y  *
    // *                     y 'static' de los contrario se generaran salidas indeseables,.... =)           *
    // ******************************************************************************************************
    public static List<Item> listaItems = new ArrayList<Item>();  // 

    //--------------------------------------------------------------------------
    public List<Item> getListaItems() {
        List<Item> lista = new ArrayList<Item>();
        String codigo;
        String grupo;
        String descripcion;
        String codUbic;
        String um;
        String nivelControl;
        Float stockMin;
        Float stockMax;
        Float ptoReorden;
        String indicador;
        List<Sup01Dat> listaSup01 = new Sup01DatJpaController().findSup01DatEntities();
        for (Sup01Dat sup01Dat : listaSup01) {
            codigo = sup01Dat.getSup01DatPK().getC1Codigo();
            grupo = sup01Dat.getSup01DatPK().getC1Grupo();
            descripcion = sup01Dat.getC1Descripcion();
            codUbic = sup01Dat.getC1CodUbicacion();
            um = sup01Dat.getC1Um();
            nivelControl = sup01Dat.getC1NivelControl();
            stockMin = (Float) ( sup01Dat.getC1StockMin()==null?0:sup01Dat.getC1StockMin().floatValue() );
            stockMax = (Float) ( sup01Dat.getC1StockMax()==null?0:sup01Dat.getC1StockMax().floatValue() );
            ptoReorden = (Float) (  sup01Dat.getC1PtoReorden()==null?0:sup01Dat.getC1PtoReorden().floatValue() );
            indicador = sup01Dat.getC1Indicador();
            lista.add(new Item(codigo, grupo, descripcion, codUbic, um, nivelControl, stockMin, stockMax, ptoReorden, indicador));
        }
        listaItems = new ArrayList<Item>(lista);  // Copiar lista ** Tambien pudiste hacer .clear antes ??? 
        return (listaItems);     // listaItems    // NOTA el metodo .clone se utiliza para copiar ArrayList y no List. =) Atencion !!!
    }  // getListaItems().  

    //--------------------------------------------------------------------------
    public Item[] getAllListaItemsArray() {
        return listaItems.toArray(new Item[listaItems.size()]);
    }  // getAllListaItemsArray(). 

    //--------------------------------------------------------------------------
    public List<Item> getFilterItem(ItemViewFilter itemViewFilter) {
        List<Item> someItem = new ArrayList<Item>();
        String codigo = itemViewFilter.getCodigo().toLowerCase();
        String grupo = itemViewFilter.getGrupo().toLowerCase();
        String descripcion = itemViewFilter.getDescripcion().toLowerCase();
        String codUbic = itemViewFilter.getCodUbic().toLowerCase();
        String um = itemViewFilter.getUm().toLowerCase();
        String nivelControl = itemViewFilter.getNivelControl();
        String stockMinS = itemViewFilter.getStockMin();  
        String stockMaxS = itemViewFilter.getStockMax();
        String ptoReordenS = itemViewFilter.getPtoReorden();
        String indicador = itemViewFilter.getIndicador();
        Item tmp = null;
        for (Iterator<Item> i = listaItems.iterator(); i.hasNext();) {
            tmp = i.next();
            if ( ( tmp.getCodigo()==null?"":tmp.getCodigo().toLowerCase() ).contains(codigo) 
                    && ( tmp.getGrupo()==null?"":tmp.getGrupo().toLowerCase() ).contains(grupo)
                    && ( tmp.getDescripcion()==null?"":tmp.getDescripcion().toLowerCase() ).contains(descripcion)
                    && ( tmp.getCodUbic()==null?"":tmp.getCodUbic().toLowerCase() ).contains(codUbic)
                    && ( tmp.getUm()==null?"":tmp.getUm().toLowerCase() ).contains(um)
                    && ( tmp.getNivelControl()==null?"":tmp.getNivelControl().toLowerCase() ).contains(nivelControl)
                    && ( tmp.getStockMin()==null?"":tmp.getStockMin().toString().toLowerCase() ).contains(stockMinS)
                    && ( tmp.getStockMax()==null?"":tmp.getStockMax().toString().toLowerCase() ).contains(stockMaxS)
                    && ( tmp.getPtoReorden()==null?"":tmp.getPtoReorden().toString().toLowerCase() ).contains(ptoReordenS)
                    && ( tmp.getIndicador()==null?"":tmp.getIndicador().toLowerCase() ).contains(indicador)) {
                    someItem.add(tmp);  
            } //if
        }  // for 
        return (someItem);
    }  // getFilterItem().  

}
