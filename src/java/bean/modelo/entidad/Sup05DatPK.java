/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hpulgar
 */
@Embeddable
public class Sup05DatPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "C5_CODIGO")
    private String c5Codigo;
    @Basic(optional = false)
    @Column(name = "C5_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date c5Fecha;
    @Basic(optional = false)
    @Column(name = "C5_TIPO_MOV")
    private String c5TipoMov;
    @Basic(optional = false)
    @Column(name = "C5_REFERENCIA")
    private String c5Referencia;

    public Sup05DatPK() {
    }

    public Sup05DatPK(String c5Codigo, Date c5Fecha, String c5TipoMov, String c5Referencia) {
        this.c5Codigo = c5Codigo;
        this.c5Fecha = c5Fecha;
        this.c5TipoMov = c5TipoMov;
        this.c5Referencia = c5Referencia;
    }

    public String getC5Codigo() {
        return c5Codigo;
    }

    public void setC5Codigo(String c5Codigo) {
        this.c5Codigo = c5Codigo;
    }

    public Date getC5Fecha() {
        return c5Fecha;
    }

    public void setC5Fecha(Date c5Fecha) {
        this.c5Fecha = c5Fecha;
    }

    public String getC5TipoMov() {
        return c5TipoMov;
    }

    public void setC5TipoMov(String c5TipoMov) {
        this.c5TipoMov = c5TipoMov;
    }

    public String getC5Referencia() {
        return c5Referencia;
    }

    public void setC5Referencia(String c5Referencia) {
        this.c5Referencia = c5Referencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c5Codigo != null ? c5Codigo.hashCode() : 0);
        hash += (c5Fecha != null ? c5Fecha.hashCode() : 0);
        hash += (c5TipoMov != null ? c5TipoMov.hashCode() : 0);
        hash += (c5Referencia != null ? c5Referencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sup05DatPK)) {
            return false;
        }
        Sup05DatPK other = (Sup05DatPK) object;
        if ((this.c5Codigo == null && other.c5Codigo != null) || (this.c5Codigo != null && !this.c5Codigo.equals(other.c5Codigo))) {
            return false;
        }
        if ((this.c5Fecha == null && other.c5Fecha != null) || (this.c5Fecha != null && !this.c5Fecha.equals(other.c5Fecha))) {
            return false;
        }
        if ((this.c5TipoMov == null && other.c5TipoMov != null) || (this.c5TipoMov != null && !this.c5TipoMov.equals(other.c5TipoMov))) {
            return false;
        }
        if ((this.c5Referencia == null && other.c5Referencia != null) || (this.c5Referencia != null && !this.c5Referencia.equals(other.c5Referencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Sup05DatPK[ c5Codigo=" + c5Codigo + ", c5Fecha=" + c5Fecha + ", c5TipoMov=" + c5TipoMov + ", c5Referencia=" + c5Referencia + " ]";
    }
    
}
