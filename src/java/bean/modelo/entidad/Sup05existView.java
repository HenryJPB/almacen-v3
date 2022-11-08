/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "SUP05EXIST_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sup05existView.findAll", query = "SELECT s FROM Sup05existView s"),
    @NamedQuery(name = "Sup05existView.findByCoditem", query = "SELECT s FROM Sup05existView s WHERE s.coditem = :coditem"),
    @NamedQuery(name = "Sup05existView.findByDescripcion", query = "SELECT s FROM Sup05existView s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sup05existView.findByUbicacion", query = "SELECT s FROM Sup05existView s WHERE s.ubicacion = :ubicacion"),
    @NamedQuery(name = "Sup05existView.findByUm", query = "SELECT s FROM Sup05existView s WHERE s.um = :um"),
    @NamedQuery(name = "Sup05existView.findByExistencia", query = "SELECT s FROM Sup05existView s WHERE s.existencia = :existencia")})
public class Sup05existView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "CODITEM")
    @Id
    private String coditem;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "UBICACION")
    private String ubicacion;
    @Column(name = "UM")
    private String um;
    @Column(name = "EXISTENCIA")
    private BigDecimal existencia;

    public Sup05existView() {
    }

    public String getCoditem() {
        return coditem;
    }

    public void setCoditem(String coditem) {
        this.coditem = coditem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

}
