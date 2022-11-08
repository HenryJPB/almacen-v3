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
@Table(name = "SUP08_DAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sup08Dat.findAll", query = "SELECT s FROM Sup08Dat s"),
    @NamedQuery(name = "Sup08Dat.findByC8CodProceso", query = "SELECT s FROM Sup08Dat s WHERE s.c8CodProceso = :c8CodProceso"),
    @NamedQuery(name = "Sup08Dat.findByC8NombreProc", query = "SELECT s FROM Sup08Dat s WHERE s.c8NombreProc = :c8NombreProc")})
public class Sup08Dat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "C8_COD_PROCESO")
    private String c8CodProceso;
    @Column(name = "C8_NOMBRE_PROC")
    private String c8NombreProc;

    public Sup08Dat() {
    }

    public Sup08Dat(String c8CodProceso) {
        this.c8CodProceso = c8CodProceso;
    }

    public String getC8CodProceso() {
        return c8CodProceso;
    }

    public void setC8CodProceso(String c8CodProceso) {
        this.c8CodProceso = c8CodProceso;
    }

    public String getC8NombreProc() {
        return c8NombreProc;
    }

    public void setC8NombreProc(String c8NombreProc) {
        this.c8NombreProc = c8NombreProc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c8CodProceso != null ? c8CodProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sup08Dat)) {
            return false;
        }
        Sup08Dat other = (Sup08Dat) object;
        if ((this.c8CodProceso == null && other.c8CodProceso != null) || (this.c8CodProceso != null && !this.c8CodProceso.equals(other.c8CodProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Sup08Dat[ c8CodProceso=" + c8CodProceso + " ]";
    }
    
}
