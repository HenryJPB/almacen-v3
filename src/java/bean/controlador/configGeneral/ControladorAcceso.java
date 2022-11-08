/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.configGeneral;

import bean.modelo.entidad.UsuarioDat;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.SourceVersion;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author henrypb
 */
public class ControladorAcceso extends GenericForwardComposer {

    private Textbox txtUsuario;
    private Textbox txtPassword, txtNewPassword;
    private Label   lblUsuario;  
    private Button btnEditNewPassword;
    private Button btnEntrar;
    private Window winSelecEmpresa;
    private Window winEmpresaNew;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        //binder = new AnnotateDataBinder(comp);
        //binder.bindBean("controller", this);   // vincular (bind) un atributo de nombre 'controller' usado en la pag con este controlador.  
        //createModel();
        iniciar();
        //Executions.sendRedirect(_applied);
    } // doAfterCompose()

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void onClick$btnEntrar() {
        // 1) Validar Usuario
        validarUsuario();
        //    
        // 2) if BD Empresa esta vacia redirigir pag a pagEmpresa -> accionar metodo include. 
        //    , else popUp pagSelecEmpresa despues de seleccionada una empresa redirigir el Thread a
        //    VISTA_PRINCIPAL/Menu principal .... 
        //checkEmpresa();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void onClick$btnEditNewPassword() {
        actualizarPassword();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciar() {
    }

    //--------------------------------------------------------------------------
    // https://coding.tools/es/md5
    //--------------------------------------------------------------------------
    private String encriptar(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            result = new BigInteger(1, digest).toString(16).toUpperCase();

        } // encriptar().
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControladorAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }  // encriptar().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarUsuario() {
        //Sessions.getCurrent().setAttribute("usuarioAutentificado", null);
        if ((new bean.controlador.configGeneral.UsuarioDatJpaController().findUsuarioDat(txtUsuario.getText().toLowerCase())) == null) {
            System.out.println("(Incorrecto)*USUARIO NO REGISTRADO*");
            Messagebox.show("Usuario NO resgistrado ;-( ", "ATENCION", Messagebox.OK, Messagebox.ERROR);
        } else {
            System.out.println("(Correcto ✓ )*USUARIO REGISTRADO*");
            if (txtPassword.getText() == null || txtPassword.getText().isEmpty()) {
            } else {
                UsuarioDat usuarioDat = (new bean.controlador.configGeneral.UsuarioDatJpaController().findUsuarioDat(txtUsuario.getText().toLowerCase()));
                if ( encriptar(txtPassword.getText().trim()).equals(usuarioDat.getContrasena()) ) {
                    // Todo OK
                    //Sessions.getCurrent().setAttribute("usuarioAutentificado", "usuarioValidado");   // cualquier nombre; Recuerda validar el nombre del usuario: henrypb, 08 Julio 2012.
                    Executions.sendRedirect("VISTA_PRINCIPAL/pagPrincipal.zul");   
                } else {
                    Messagebox.show("Contraseña invalida ;=(. Intentalo nuevamente.", "ATENCION", Messagebox.OK, Messagebox.ERROR);
                }
            }
        }
    }  // validarUsuario().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    /*
     private void checkEmpresa() {
     if ((new NomConfigDatJpaController()).findNomConfigDatEntities() == null) {  // 1ra ves?? No hay empresas registradas 
     //Executions.sendRedirect("VISTA_PRINCIPAL/pagPrincipal.zul");
     winEmpresaNew.doPopup();  // 
     //Executions.sendRedirect("VISTA_EMPRESA/pagEmpresa.zul");  // incluir>>obligatorio
     } else {
     winSelecEmpresa.doPopup();
     //winSelecEmpresa.setTitle("SELECCIONAR EMPRESA / SUCURSAL");
     }
     }  // checkEmpresa()
     */
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void actualizarPassword() {
        if ((new bean.controlador.configGeneral.UsuarioDatJpaController().findUsuarioDat(txtUsuario.getText().toLowerCase())) == null) {
            System.out.println("(Incorrecto)*USUARIO NO REGISTRADO*");
            Messagebox.show("Usuario NO resgistrado. Check nombre!!!", "ATENCION", Messagebox.OK, Messagebox.ERROR);
        } else {
            System.out.println("(Correcto)*USUARIO REGISTRADO*");
            UsuarioDat usuarioDat = new bean.controlador.configGeneral.UsuarioDatJpaController().findUsuarioDat(txtUsuario.getText().toLowerCase());
            System.out.println("se trata de: " + usuarioDat.getUsuario() + " contraseña=" + usuarioDat.getContrasena() + " creado: " + usuarioDat.getFcreacion() + ".");
            if (txtNewPassword.getText().isEmpty()) {
                Messagebox.show("Nueva contraseña no puede ser nulo!!!!", "ATENCION", Messagebox.OK, Messagebox.ERROR);
            } else {
                usuarioDat.setContrasena(encriptar(txtNewPassword.getText().trim()));
                usuarioDat.setFactualizacion(new Date());
                try {
                    new bean.controlador.configGeneral.UsuarioDatJpaController().edit(usuarioDat);
                    Messagebox.show("Nueva contraseña registrada correctamente (=.=) ✓✓", "ATENCION", Messagebox.OK, Messagebox.EXCLAMATION);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorAcceso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
