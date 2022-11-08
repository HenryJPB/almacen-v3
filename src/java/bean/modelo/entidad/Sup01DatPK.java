/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author henrypb
 */
@Embeddable
public class Sup01DatPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "C1_CODIGO")
    private String c1Codigo;
    @Basic(optional = false)
    @Column(name = "C1_GRUPO")
    private String c1Grupo;

    public Sup01DatPK() {
    }

    public Sup01DatPK(String c1Codigo, String c1Grupo) {
        this.c1Codigo = c1Codigo;
        this.c1Grupo = c1Grupo;
    }

    public String getC1Codigo() {
        return c1Codigo;
    }

    public void setC1Codigo(String c1Codigo) {
        this.c1Codigo = c1Codigo;
    }

    public String getC1Grupo() {
        return c1Grupo;
    }

    public void setC1Grupo(String c1Grupo) {
        this.c1Grupo = c1Grupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c1Codigo != null ? c1Codigo.hashCode() : 0);
        hash += (c1Grupo != null ? c1Grupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sup01DatPK)) {
            return false;
        }
        Sup01DatPK other = (Sup01DatPK) object;
        if ((this.c1Codigo == null && other.c1Codigo != null) || (this.c1Codigo != null && !this.c1Codigo.equals(other.c1Codigo))) {
            return false;
        }
        if ((this.c1Grupo == null && other.c1Grupo != null) || (this.c1Grupo != null && !this.c1Grupo.equals(other.c1Grupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Sup01DatPK[ c1Codigo=" + c1Codigo + ", c1Grupo=" + c1Grupo + " ]";
    }
    
}
