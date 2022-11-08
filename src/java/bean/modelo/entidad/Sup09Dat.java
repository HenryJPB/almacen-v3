/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "SUP09_DAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sup09Dat.findAll", query = "SELECT s FROM Sup09Dat s"),
    @NamedQuery(name = "Sup09Dat.findByC9Um", query = "SELECT s FROM Sup09Dat s WHERE s.c9Um = :c9Um"),
    @NamedQuery(name = "Sup09Dat.findByC9Descripcion", query = "SELECT s FROM Sup09Dat s WHERE s.c9Descripcion = :c9Descripcion"),
    @NamedQuery(name = "Sup09Dat.findByC9CantUm", query = "SELECT s FROM Sup09Dat s WHERE s.c9CantUm = :c9CantUm")})
public class Sup09Dat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "C9_UM")
    private String c9Um;
    @Column(name = "C9_DESCRIPCION")
    private String c9Descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "C9_CANT_UM")
    private BigDecimal c9CantUm;

    public Sup09Dat() {
    }

    public Sup09Dat(String c9Um) {
        this.c9Um = c9Um;
    }

    public String getC9Um() {
        return c9Um;
    }

    public void setC9Um(String c9Um) {
        this.c9Um = c9Um;
    }

    public String getC9Descripcion() {
        return c9Descripcion;
    }

    public void setC9Descripcion(String c9Descripcion) {
        this.c9Descripcion = c9Descripcion;
    }

    public BigDecimal getC9CantUm() {
        return c9CantUm;
    }

    public void setC9CantUm(BigDecimal c9CantUm) {
        this.c9CantUm = c9CantUm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c9Um != null ? c9Um.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sup09Dat)) {
            return false;
        }
        Sup09Dat other = (Sup09Dat) object;
        if ((this.c9Um == null && other.c9Um != null) || (this.c9Um != null && !this.c9Um.equals(other.c9Um))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Sup09Dat[ c9Um=" + c9Um + " ]";
    }
    
}
