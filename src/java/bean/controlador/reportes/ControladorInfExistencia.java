/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.zkex.zul.Jasperreport;

/**
 *
 * @author henrypb
 */
public class ControladorInfExistencia extends GenericForwardComposer {

    final String driver = "oracle.jdbc.driver.OracleDriver";
    final String oracleSID = "DES112";
    final String host = "193.168.0.59";
    final String usuario = "OPS$DESSUP03";         //  *ezquema del usuario*
    final String contrasena = "OPS$DESSUP03";
    final String puerto = "1521";
    private Connection con = null;
    //private Jasperreport infExistencia;  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // << intrucciones del usuario >>
        //certificarUsuario(); 
        //checkEventQueves();  
        ejecutarReporte();
    }

    //--------------------------------------------------------------------------
    private void ejecutarReporte() {
        //infExistencia.setSrc("../REPORTES/Existencia.jasper");
        //--------------------------set data conection--------------------------
        try {
            Class.forName(driver);
            String url = "jdbc:oracle:thin:@" + host + ":" + puerto + ":" + oracleSID;
            //System.out.println(url+","+usuario+","+contrasena);
            con = DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException excepcion) {
            System.err.println(excepcion);
        } catch (SQLException excepcion) {
            System.err.println(excepcion);
        }
        //infExistencia.setDataConnection(con);
        //-----------------------------------------------------------------------
        String formato = (String) Sessions.getCurrent().getAttribute("formato");  
        //infExistencia.setType("html");
        //infExistencia.setType(formato);
    }  // ejecutarReporte().  

}
