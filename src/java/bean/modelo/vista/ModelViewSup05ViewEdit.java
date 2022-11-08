/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.vista;

import bean.controlador.sup05.Sup05ViewJpaController;
import bean.interfaz.IModelViewSup05ViewEdit;
import bean.modelo.entidad.Sup05View;
import java.text.SimpleDateFormat;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

/**
 *
 * @author henrypb
 */
public class ModelViewSup05ViewEdit implements IModelViewSup05ViewEdit {

    /*  *** Esto *NO FUNCIONO **
    ControladorSup05 ctlrSup05 = new ControladorSup05();  
    private String codItem = ctlrSup05.getCodItem();  
    private java.util.Date fecha = ctlrSup05.getFecha();  
    private String tipoMov = ctlrSup05.getTipoMov();  
    private String referencia =  ctlrSup05.getReferencia();  
    */
    
    private String codItem = (String) Sessions.getCurrent().getAttribute("codItem");  
    private java.util.Date fecha = (java.util.Date) Sessions.getCurrent().getAttribute("fecha"); 
    private String tipoMov = (String) Sessions.getCurrent().getAttribute("tipoMov"); 
    private String referencia =  (String) Sessions.getCurrent().getAttribute("referencia");  
    
    
    //private Sup05View sup05View = new Sup05View();  
    private Sup05View sup05View = new Sup05ViewJpaController().findSup05ViewEntitie(codItem, fecha, tipoMov, referencia);   // Autor de este metodo: HJPB (Bqto: 16/10/2019 15:15 ) 

    // Eliminar:
    {
        System.out.println("**ModelViewSup05ViewEdit(Var sessions)***codItem="+codItem+"***fecha="+fecha+"***tipoMov="+tipoMov+"***Referencia="+referencia+"***"); 
        String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(sup05View.getFecha()); 
        System.out.println("**ModelViewSup05ViewEdit(sup05View)***Sup05ViewcodItem="+sup05View.getCoditem()+"***Sup05..fecha="+sup05View.getFecha()+"***su05V...tipoMov="+sup05View.getTipomov()+"***Referencia="+sup05View.getReferencia()+"**sup05View..fechaFormateada="+fechaFormateada+"*"); 
    }
    
    //--------------------------------------------------------------------------
    @Init
    public void init() {
        // Initialize();  
    }    

    /*
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
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
     **/
    
    public Sup05View getSup05View() {
        return sup05View;
    }

    public void setSup05View(Sup05View sup05View) {
        this.sup05View = sup05View;
    }
    
}
