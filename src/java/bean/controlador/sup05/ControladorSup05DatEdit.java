/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.sup05;

import bean.modelo.entidad.Sup01Dat;
import bean.modelo.entidad.Sup05Vista;
import bean.modelo.entidad.Sup05existView;
import bean.servicio.ServicioAdmonSup05Vista;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author henrypb
 */
public class ControladorSup05DatEdit extends GenericForwardComposer {

    private Window winSup05DatEdit;
    private Button btnEditWin;        // Boton actualizar datos de la forma.
    private Textbox txtCodItem;
    private Textbox txtDescripcion;
    private Datebox dtxFecha;
    private Textbox txtTipoMov;
    private Radiogroup rdgTipoMov;
    private Radio rd1;    // del widget rdgTipMov;  
    private Radio rd2;    //  
    private Textbox txtReferencia;
    private Decimalbox dcxUnidades, dcxExistencia, dcxExistDespues, dcxCosto, dcxTotalCosto;
    private Label lblMsgErrorCodItem, lblMsgErrorUnidades, lblMsgError;
    private Bandbox bbxProceso;
    private Button btnMsgError;
    private Image imgCloseErrorCodItem;
    private Listbox lbxMovAlmacen;  // Sujeto a Revision !!!!
    private java.util.Date fecha1, fecha2;  
    //
    private String codItem = (String) Sessions.getCurrent().getAttribute("codItem");
    private Boolean registroNuevo = ((codItem == null || codItem.isEmpty()) ? Boolean.TRUE : Boolean.FALSE);

    //--------------------------------------------------------------------------
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // << intrucciones del usuario >>
        //certificarUsuario(); 
        checkEventQueves(); 
        iniciar();
        //rdgTipoMov.getSelectedItem();
    }

    //--------------------------------------------------------------------------
    private void iniciar() {
        final String backgroundStyle = "background-color: azure;";
        //String codItem = (String) Sessions.getCurrent().getAttribute("codItem"); 
        //if ( codItem==null || codItem.isEmpty() ) {
        if (registroNuevo) {
            txtCodItem.setReadonly(Boolean.FALSE);
            txtCodItem.setStyle(backgroundStyle);
            // 
            dtxFecha.setReadonly(Boolean.FALSE);
            dtxFecha.setButtonVisible(Boolean.TRUE);
            dtxFecha.setStyle(backgroundStyle);
            //
            rdgTipoMov.setStyle(backgroundStyle);
            rd1.setDisabled(Boolean.FALSE);
            rd1.setChecked(Boolean.TRUE);      // E)ntrada.  
            rd2.setDisabled(Boolean.FALSE);    // S)alida. 
            //
            txtReferencia.setReadonly(Boolean.FALSE);
            txtReferencia.setStyle(backgroundStyle);
        } else {
            setExistencia();
            setTotalCosto();
        }
        lbxMovAlmacen = (Listbox) arg.get("lbxMovAlmacen");  
        fecha1 = (Date) arg.get("fecha1");  
        fecha2 = (Date) arg.get("fecha2");   
        // System.out.println("****PROBANDO::PROBANDO::MI Listbox (Cuantos campos/children posee el registro )="+lbxMovAlmacen.getItemAtIndex(1).getChildren().size()+"***f1="+fecha1+"***fecha2="+fecha2+"****");   // ✔✔
    }  // iniciar().  

    //--------------------------------------------------------------------------
    private void checkEventQueves() {
       EventQueues.lookup("evtQueOnSelect",EventQueues.APPLICATION, true).subscribe( new EventListener() {

           @Override
           public void onEvent(Event e) throws Exception {
               System.out.println("******Event 'e' del metodo 'checkEventQueues' del 'ControladorSup05Edit'. Index del item en el lbx="+e.getData()+"********");  
           }
       } );
        
    }  // checkEventQueves()
    
    //--------------------------------------------------------------------------
    public void onChange$txtCodItem() {
        //System.out.println("******++onChange Cod Item***********************"); 
        lblMsgErrorCodItem.setValue("");
        imgCloseErrorCodItem.setVisible(Boolean.FALSE);
        validarCodItem();
    }  // onChange$txtCodItem(). 

    //--------------------------------------------------------------------------
    public void onClick$btnEditWin() {
        // Este metodo no funciona para un Listbox basado en viewModel --> requiere @NotifyChange({"viewSup05VistaModel", "footer"}) en el ambito del componente que lo contiene (solo el padre???) 
        //List<Sup05Vista> lista = new ServicioAdmonSup05Vista().getListaSup05Vista(fecha1, fecha2);  
        //lbxMovAlmacen.setModel(new ListModelList<Sup05Vista>(lista));
        lbxMovAlmacen.renderAll();   // ????????????????????????????
        //lbxMovAlmacen.setModel(new BindingListModelList<Sup05Vista>(lista,Boolean.FALSE));  // Tampoco funciona
        //lbxMovAlmacen.setItemRenderer((ListitemRenderer<Sup05Vista>) lista);   // Either work :¡(
        //System.out.println("*****FUNCIONO???*****"); 
    }  // onClick$btnEdit(). 
    
    
    //--------------------------------------------------------------------------
    private void validarCodItem() {
        Sup01Dat sup01Dat = new bean.controlador.item.Sup01DatJpaController().findSup01DatEntidad(txtCodItem.getText());
        //String descripcion = ( new bean.controlador.item.Sup01DatJpaController().findSup01DatEntidad(codigoItem) ).getC1Descripcion();  
        if (sup01Dat == null) {
            lblMsgErrorCodItem.setValue("Item no registrado");
            imgCloseErrorCodItem.setVisible(Boolean.TRUE);
        } else {
            String descripcion = sup01Dat.getC1Descripcion();
            txtDescripcion.setText(descripcion);
            // set Existencia -......
            setExistencia();
            //Sup05existView sup05Exist = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existView(txtCodItem.getValue());
            //Double existencia = sup05Exist.getExistencia().doubleValue();
            //dcxExistencia.setValue(new java.math.BigDecimal(existencia));  // Rellenar el campo readOnly Existencia 
            // setTotalCosto().-...... 
            setTotalCosto();
        }
    }  // validarCodItem().  

    //--------------------------------------------------------------------------
    public void onChange$dcxUnidades() {
        //System.out.println("*********onChangeUnidades********");
        //    lblMsgErrorUnidades.setValue("");
        //    if (txtCodItem.getValue() != null || !txtCodItem.getValue().isEmpty()) {
        //        validarUnidades();
        //    }
        setExistDespues();
        setCostoPromedio();
        setTotalCosto();
    } // onChange$dbUnidades(). 

    //--------------------------------------------------------------------------
    private void validarUnidades() {
        Sup05existView sup05Exist = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existView(txtCodItem.getValue());
        if (sup05Exist == null) {
            lblMsgErrorUnidades.setValue("Problemas para identificar la existencia de este Item. Contacta tu DBA.");
            //btnMsgErrorUnidades.setVisible(Boolean.TRUE);
        } else {
            BigDecimal existencia = sup05Exist.getExistencia();
            //System.out.println("*******EXISTENCIA="+existencia+"*******");
            if (dcxUnidades.getValue().compareTo(existencia) > 0) {
                lblMsgErrorUnidades.setValue("La cantidad de unidades supera la Existencia.");
                //btnMsgErrorUnidades.setVisible(Boolean.TRUE);
            }
        }  // if-else
    } // validarUnidades().  


    //--------------------------------------------------------------------------
    public void onChange$dcxCosto() {
        setTotalCosto();
    }  // onChange$dbCosto().  

    //--------------------------------------------------------------------------
    private void setCostoPromedio() {
        if (dcxExistencia.getValue() != null && dcxExistencia.getValue() != BigDecimal.ZERO && rd2.isChecked()) {    // S)alida marcada 
            BigDecimal costoPromedio = new bean.controlador.sup05.Sup05DatJpaController().getCostoPromedioItem(txtCodItem.getText(), dtxFecha.getValue());
            //System.out.println("****onChangeUnidades****costoPromedio(01097)="+costo+"**al**"+dbFecha.getValue()+"****");
            dcxCosto.setValue(costoPromedio);
        } else {
            dcxCosto.setValue(BigDecimal.ZERO); // if  
        }
    }  // setCostoPromedio().  

    //--------------------------------------------------------------------------
    private void setExistencia() {
        // * 1er Intento: (funciona correctamente) *  
        //Sup05existView sup05Exist = new bean.controlador.sup05.Sup05existViewJpaController().findSup05existView(txtCodItem.getValue());
        //dbExistencia.setValue(sup05Exist.getExistencia());  // Rellenar el campo readOnly Existencia 
        // * 2do Intento  (other way): 
        BigDecimal existencia = new bean.controlador.sup05.Sup05DatJpaController().getExistencia(txtCodItem.getText());
        dcxExistencia.setValue(existencia);
    } // setExistencia(). 

    //--------------------------------------------------------------------------
    private void setExistDespues() {
        BigDecimal existencia = ( dcxExistencia.getValue()==null?BigDecimal.ZERO:dcxExistencia.getValue() );  
        BigDecimal unidades = ( dcxUnidades.getValue()==null?BigDecimal.ZERO:dcxUnidades.getValue() );  
        if ( rd1.isSelected() ) {  // tipoMov = 'Entrada' 
             dcxExistDespues.setValue(existencia.add(unidades));
        } else {
            dcxExistDespues.setValue(existencia.subtract(unidades));
        }
    }  // setExistDespues()

    //--------------------------------------------------------------------------
    private void setTotalCosto() {
        if (dcxUnidades.getValue() != null && dcxCosto.getValue() != null) {
            dcxTotalCosto.setValue(dcxUnidades.getValue().multiply(dcxCosto.getValue()));
        } else {
            dcxTotalCosto.setValue(BigDecimal.ZERO);
        }
    }  // setTotalCosto().  

    public void onChange$bbxProceso() {
        System.out.println("*****BandBox - codProceso=" + bbxProceso.getText() + "*****************");
    }

}
