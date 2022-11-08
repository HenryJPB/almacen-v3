/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.interfaz;

/**
 *
 * @author henrypb
 */
public interface IModelViewSup05DatEdit {
    
    // private Sup05View sup05View = new Sup05Vieww();  
    
    // ** aqui load el valor del atributo: 'sup05View'
    
    //--------------------------------------------------------------------------
    // @Init   // *Mandatorio*
    public abstract void init();  // Initialize


    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    /*
    @Command("btnEditClicked")
    public void btnEditClicked() {
        //System.out.println("***************[btnEditClicked] presionado*********************");
        Messagebox.show("CONFORME ?", "CONFIRMACION", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {

            @Override
            public void onEvent(Event evento) throws Exception {
                //if (evento.equals("onYes") && datosValido()) {
                if (evento.getName().equals("onYes")) {
                    ajustarDatos();
                    //System.out.println("Save? .. CodVar=" + nomVariableDat.getNomVariableDatPK().getCodVar() + "Nombre=" + nomVariableDat.getNombreVar() + "valor=" + nomVariableDat.getValor() + "conforme=" + nomVariableDat.getStatus() + "****");
                    new NomVariableDatJpaController().edit(nomVariableDat);
                    //System.out.println("Save listo!!!!!");
                } else {
                    System.out.println("ERROR ;-(");
                }
            } // onEvent()  
        });
    }  // btnEditClicked()
    */ 
    
    // ** Create los getter / setter del atributo: 'sup05Dat'
}
