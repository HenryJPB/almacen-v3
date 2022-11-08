/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author henrypb
 */
@Entity
@Table(name = "MANT01_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mant01View.findAll", query = "SELECT m FROM Mant01View m"),
    @NamedQuery(name = "Mant01View.findByC1CodMaquina", query = "SELECT m FROM Mant01View m WHERE m.c1CodMaquina = :c1CodMaquina"),
    @NamedQuery(name = "Mant01View.findByC1Descripcion", query = "SELECT m FROM Mant01View m WHERE m.c1Descripcion = :c1Descripcion")})
public class Mant01View implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "C1_COD_MAQUINA")
    @Id
    private String c1CodMaquina;
    @Basic(optional = false)
    @Column(name = "C1_DESCRIPCION")
    private String c1Descripcion;

    public Mant01View() {
    }

    public String getC1CodMaquina() {
        return c1CodMaquina;
    }

    public void setC1CodMaquina(String c1CodMaquina) {
        this.c1CodMaquina = c1CodMaquina;
    }

    public String getC1Descripcion() {
        return c1Descripcion;
    }

    public void setC1Descripcion(String c1Descripcion) {
        this.c1Descripcion = c1Descripcion;
    }
    
}
