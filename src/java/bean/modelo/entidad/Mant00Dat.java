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
@Table(name = "MANT00_DAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mant00Dat.findAll", query = "SELECT m FROM Mant00Dat m"),
    @NamedQuery(name = "Mant00Dat.findByCodMaq", query = "SELECT m FROM Mant00Dat m WHERE m.codMaq = :codMaq")})
public class Mant00Dat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_MAQ")
    private String codMaq;

    public Mant00Dat() {
    }

    public Mant00Dat(String codMaq) {
        this.codMaq = codMaq;
    }

    public String getCodMaq() {
        return codMaq;
    }

    public void setCodMaq(String codMaq) {
        this.codMaq = codMaq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMaq != null ? codMaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mant00Dat)) {
            return false;
        }
        Mant00Dat other = (Mant00Dat) object;
        if ((this.codMaq == null && other.codMaq != null) || (this.codMaq != null && !this.codMaq.equals(other.codMaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Mant00Dat[ codMaq=" + codMaq + " ]";
    }
    
}
