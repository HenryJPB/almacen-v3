/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.reportes;

import java.sql.Connection;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Image;
import org.zkoss.zul.Radiogroup;

/**
 *
 * @author henrypb
 */
public class ControladorInformes extends GenericForwardComposer {

    final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String oracleSID = "DES112";
    private final String host = "193.168.0.59";
    private final String usuario = "OPS$DESSUP03";         //  *ezquema del usuario*
    private final String contrasena = "OPS$DESSUP03";
    private final String puerto = "1521";
    private Connection con = null;
    //
    private Image imgRptExistencia, imgRptMovInv;
    private Radiogroup rdgFormato;

    //**************************************************************************
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // << intrucciones del usuario >>
        //certificarUsuario(); 
        //checkEventQueves();  
    }  // doAfterCompose().

    //--------------------------------------------------------------------------
    public void onClick$imgRptExistencia() {
        //System.out.println("*****Formato=" + rdgFormato.getSelectedItem().getLabel() + "*******");
        //ejecutarInfExistenciaZK();
        ejecutarInfExistenciaJSP();  
    }  // onClick$imgRptExistencia()

    
    //--------------------------------------------------------------------------
    public void onClick$imgRptMovInv() {
        ejecutarInfMovInvJSP();  
    }  // onClick$imgRptMovInv(). 
    
    //--------------------------------------------------------------------------
    private void ejecutarInfExistenciaZK() {
        String formato = rdgFormato.getSelectedItem().getLabel();
        switch (formato) {
            case "pdf":
                Executions.sendRedirect("../VISTA_INFORME/pagInfExistencia.zul");
                Sessions.getCurrent().setAttribute("formato","pdf");  
                break;
            case "excel":
                Executions.sendRedirect("../VISTA_INFORME/pagInfExistencia.zul");
                Sessions.getCurrent().setAttribute("formato","xls"); 
                break;
            default:
                Executions.sendRedirect("../VISTA_INFORME/pagInfExistencia.zul");
                Sessions.getCurrent().setAttribute("formato","html"); 
                break;
        }  // shitch()
    }  // ejecutarInfExistenciaZK(). 
    
    //--------------------------------------------------------------------------
    private void ejecutarInfExistenciaJSP() {
        String formato = rdgFormato.getSelectedItem().getLabel();
        switch (formato) {
            case "pdf":
                //request.setAttribute().setAttribute("formato","pdf");   // Revisar pase de parametros entre JSP..?? 
                Executions.sendRedirect("/JSP/jspReportExistenciaPDF.jsp");
                break;
            case "excel":
                //request.setAttribute().setAttribute("formato","pdf");   // Revisar pase de parametros entre JSP..?? 
                 Executions.sendRedirect("/JSP/jspReportExistenciaEXCEL.jsp");
                break;
            default:
                //request.setAttribute().setAttribute("formato","pdf");   // Revisar pase de parametros entre JSP..?? 
                Executions.sendRedirect("/JSP/jspReportExistenciaHTML.jsp");
                break;
        }  // shitch()
    }  // ejecutarInfExistenciaZK(). 
    
    //--------------------------------------------------------------------------
    private void ejecutarInfMovInvJSP() {
        /*
        String formato = rdgFormato.getSelectedItem().getLabel();
        switch (formato) {
            case "pdf":
                //request.setAttribute().setAttribute("formato","pdf");   // Revisar pase de parametros entre JSP..?? 
                Executions.sendRedirect("/JSP/jspReportExistenciaPDF.jsp");
                break;
            case "excel":
                //request.setAttribute().setAttribute("formato","pdf");   // Revisar pase de parametros entre JSP..?? 
                 Executions.sendRedirect("/JSP/jspReportExistenciaEXCEL.jsp");
                break;
            default:
                //request.setAttribute().setAttribute("formato","pdf");   // Revisar pase de parametros entre JSP..?? 
                //Executions.sendRedirect("/JSP/jspReportExistenciaHTML.jsp");
                Executions.sendRedirect("/JSP/jspParMovAlmacen.jsp");
                break;
        }  // shitch()
        */  
        Executions.sendRedirect("/JSP/jspParamMovAlmacen.jsp");
    }  // ejecutarInfMovInvJSP().   
    
}
