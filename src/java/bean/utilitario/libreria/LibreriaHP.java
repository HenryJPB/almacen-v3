/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.libreria;

import bean.interfaz.ILibreriaHP;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.io.IOUtils;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author henrypb Documentacion sobre arrays:
 * https://darkbyteblog.wordpress.com/2011/03/28/java-estructuras-de-datos-arreglos/
 *
 */
public class LibreriaHP implements ILibreriaHP {

    public SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public DecimalFormat formatoDecimal = new DecimalFormat("###,###,###,##0.00");

    public DecimalFormat formatoFactura = new DecimalFormat("000000");

    public DecimalFormat formatoNcf = new DecimalFormat("0000000000");

    final String OPERADOR = "+-/*";
    final String SIMBOLO = "()[]";
    final String SIGNO = ",.";  // signo de puntuacion. 
    final String DIGITO = "0123456789";
    final String CONCEPTO = "VPCMT";  // |-> V)ariable,P)romedio,C)antidad,M)onto y/o T)otal.  
    // 
    final int MAX_ELEMENTOS = 100;
    //
    private enum TIPO_TOKEN {
        BLANK, CONCEPTO, DIGITO, FUNCION, NUMERO, PROMEDIO, SIMBOLO, SIGNO, OPERADOR
    }
    
    //--------------------------------------------------------------------------    
    // Original:  
    //--------------------------------------------------------------------------
    public void divideTokenizer(String cadena) {
        /*
         String[] arrTokens = new String[MAX_ELEMENTOS];
         char caracter;  
         for (int i=0; ( i<cadena.length() );i++) {
         caracter = cadena[i]; 
         System.out.println(cadena[i]);  
         }
         */
        //String string = cadena; 
        String string = cadena;
        //StringTokenizer token = new StringTokenizer(string,"=");  
        StringTokenizer token = new StringTokenizer(string, " ");
        String st = null;
        while (token.hasMoreTokens()) {
            st = token.nextToken();
            System.out.println("Token=" + st);
        }  // while. 
    }  // divideTokenizer();

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public boolean esNumerico(String valorStr) {
        try {
            double d = Double.parseDouble(valorStr);
        } // try.
        catch (NumberFormatException nfe) {
            return false;
        }  // catch.  
        return true;
    }  // esNumerico()

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public boolean numeroValido(String valorStr) {
        try {
            double d = Double.parseDouble(valorStr);
        } // try.
        catch (NumberFormatException nfe) {
            return false;
        }  // catch.  
        return true;
    }  // numeroValido(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void error(JPanel panel, JTextField campo, String mensaje) {
        campo.setBackground(Color.YELLOW);
        JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
        campo.setBackground(Color.WHITE);
        campo.requestFocus();
    }  // errror(). 

    //--------------------------------------------------------------------------
    // convertir un String a un String en formato numerico convencional. 
    // Ejm. 10.213,45 -> 10213.45 para convertir en <>.Parse... / new BigDecimal().  
    //--------------------------------------------------------------------------
    @Override
    public String convFormatoNumerico(String valorS1) {
        String valorS2 = null;
        valorS2 = valorS1.replace(".", "");
        valorS2 = valorS2.replace(",", ".");
        valorS2 = valorS2.replace(" ", "");
        return (valorS2);
    }  //  convFormatoNumerico(). 

    //--------------------------------------------------------------------------
    // convertir un String a un String en formato numerico convencional. 
    // Ejm. 10,213.45 -> 10213.45 para convertir en <>.Parse... / new BigDecimal(). 
    // para operar en Base de Datos. 
    //--------------------------------------------------------------------------
    @Override
    public String convFormatoNumericoBD(String valorS1) {
        String valorS2 = null;
        valorS2 = valorS1.replace(",", "");
        valorS2 = valorS2.replace(" ", "");
        return (valorS2);
    }  //  convFormatoNumerico(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void notificarFocus(JTextField campo) {
        campo.setBackground(Color.YELLOW);
        campo.requestFocus();
    }  // notificarFocus().  

    //--------------------------------------------------------------------------
    // Leer y ordenar ficheros del sistema de archivos (carpeta) 
    // aplicando un filtro:
    // Documentacion sobre arrays: https://darkbyteblog.wordpress.com/2011/03/28/java-estructuras-de-datos-arreglos/
    //--------------------------------------------------------------------------
    @Override
    public String[] leerFicheros(String ruta) {
        final String filtro = ".jpg";  // 
        final int MAX = 999;
        File carpeta = new File(ruta);
        String[] arreglo = new String[MAX];
        String[] arregloAux = null;
        int i = 0;
        Boolean capacidad = Boolean.TRUE;
        if (carpeta.isDirectory()) {
            //alert("La ruta: "+ruta+" es un directorio de este System files"); 
            for (File ficheroEntrada : carpeta.listFiles()) {
                if (ficheroEntrada.isFile()) {
                    String nombreArchivo = ficheroEntrada.getName().toLowerCase();
                    if (nombreArchivo.contains(filtro) && capacidad) {
                        arreglo[i] = ficheroEntrada.getName();
                        i++;
                        if (i > MAX) {
                            capacidad = Boolean.FALSE;
                        }
                    }
                }
            }  // for.  
        } else { // verificar q existe el directorio 
            //alert("La ruta: "+ruta+" NO es un directorio de este sistema"); 
        }
        if (i > 0) {
            // Procesar valores NO nulos.  ***************
            int contador = 0;
            for (String s : arreglo) {
                if (s != null && !s.isEmpty()) {
                    contador++;
                }
            }  // for
            arregloAux = new String[contador];
            System.arraycopy(arreglo, 0, arregloAux, 0, contador);
            Arrays.sort(arregloAux);
        }  // if. 
        return (arregloAux);
    }  // leerFicheros().

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public BufferedImage getImagen(byte[] bytes) {
        //leerFoto.setTooltiptext(String);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException ex) {
            Logger.getLogger(LibreriaHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (img);
    }  // getImagen().

    //--------------------------------------------------------------------------
    // Haciendo uso de Poliformismo: 
    //--------------------------------------------------------------------------
    @Override
    public BufferedImage getImagen(String carpeta) throws IOException {
        byte[] bytes = convertirImagen(carpeta);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException ex) {
            Logger.getLogger(LibreriaHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (img);
    }  // getImagen().

    //--------------------------------------------------------------------------
    // Archivo -> byte[]
    //--------------------------------------------------------------------------
    @Override
    public byte[] convertirImagen(String carpeta) throws FileNotFoundException, IOException {
        File file = new File(carpeta);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = IOUtils.toByteArray(fis);
        return (bytes);
    }  // convertirImagen()

    //--------------------------------------------------------------------------
    // Haciendo uso de Poliformismo. 
    // https://www.tutorialspoint.com/How-to-convert-Image-to-Byte-Array-in-java
    // Buffered img -> byte[]
    //--------------------------------------------------------------------------
    @Override
    public byte[] convertirImagen(BufferedImage bImage) throws IOException {
        //BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        byte[] bytes = bos.toByteArray();
        return (bytes);
    }  // convertirImagen()

}  // MyLibreryHP.  
