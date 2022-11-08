/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hpulgar
 */
@Entity
@Table(name = "SUP05_DAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sup05Dat.findAll", query = "SELECT s FROM Sup05Dat s"),
    @NamedQuery(name = "Sup05Dat.findByC5Codigo", query = "SELECT s FROM Sup05Dat s WHERE s.sup05DatPK.c5Codigo = :c5Codigo"),
    @NamedQuery(name = "Sup05Dat.findByC5Fecha", query = "SELECT s FROM Sup05Dat s WHERE s.sup05DatPK.c5Fecha = :c5Fecha"),
    @NamedQuery(name = "Sup05Dat.findByC5TipoMov", query = "SELECT s FROM Sup05Dat s WHERE s.sup05DatPK.c5TipoMov = :c5TipoMov"),
    @NamedQuery(name = "Sup05Dat.findByC5Costo", query = "SELECT s FROM Sup05Dat s WHERE s.c5Costo = :c5Costo"),
    @NamedQuery(name = "Sup05Dat.findByC5CantRequerida", query = "SELECT s FROM Sup05Dat s WHERE s.c5CantRequerida = :c5CantRequerida"),
    @NamedQuery(name = "Sup05Dat.findByC5Unidades", query = "SELECT s FROM Sup05Dat s WHERE s.c5Unidades = :c5Unidades"),
    @NamedQuery(name = "Sup05Dat.findByC5Referencia", query = "SELECT s FROM Sup05Dat s WHERE s.sup05DatPK.c5Referencia = :c5Referencia"),
    @NamedQuery(name = "Sup05Dat.findByC5Depart", query = "SELECT s FROM Sup05Dat s WHERE s.c5Depart = :c5Depart"),
    @NamedQuery(name = "Sup05Dat.findByC5CodProceso", query = "SELECT s FROM Sup05Dat s WHERE s.c5CodProceso = :c5CodProceso"),
    @NamedQuery(name = "Sup05Dat.findByC5CodMaq", query = "SELECT s FROM Sup05Dat s WHERE s.c5CodMaq = :c5CodMaq"),
    @NamedQuery(name = "Sup05Dat.findByC5Origen", query = "SELECT s FROM Sup05Dat s WHERE s.c5Origen = :c5Origen"),
    @NamedQuery(name = "Sup05Dat.findByC5Sysdate", query = "SELECT s FROM Sup05Dat s WHERE s.c5Sysdate = :c5Sysdate"),
    @NamedQuery(name = "Sup05Dat.findByC5Observacion", query = "SELECT s FROM Sup05Dat s WHERE s.c5Observacion = :c5Observacion")})
public class Sup05Dat implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Sup05DatPK sup05DatPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "C5_COSTO")
    private BigDecimal c5Costo;
    @Column(name = "C5_CANT_REQUERIDA")
    private BigDecimal c5CantRequerida;
    @Column(name = "C5_UNIDADES")
    private BigDecimal c5Unidades;
    @Column(name = "C5_DEPART")
    private String c5Depart;
    @Column(name = "C5_COD_PROCESO")
    private String c5CodProceso;
    @Column(name = "C5_COD_MAQ")
    private String c5CodMaq;
    @Column(name = "C5_ORIGEN")
    private String c5Origen;
    @Column(name = "C5_SYSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date c5Sysdate;
    @Column(name = "C5_OBSERVACION")
    private String c5Observacion;

    public Sup05Dat() {
    }

    public Sup05Dat(Sup05DatPK sup05DatPK) {
        this.sup05DatPK = sup05DatPK;
    }

    public Sup05Dat(String c5Codigo, Date c5Fecha, String c5TipoMov, String c5Referencia) {
        this.sup05DatPK = new Sup05DatPK(c5Codigo, c5Fecha, c5TipoMov, c5Referencia);
    }

    //-----*HJPB*----------------------------------------------------------------
    public Sup05Dat(Sup05DatPK sup05DatPK, BigDecimal c5Costo, BigDecimal c5CantRequerida, BigDecimal c5Unidades, String c5Depart, String c5CodProceso, String c5CodMaq, String c5Origen, Date c5Sysdate, String c5Observacion) {
        this.sup05DatPK = sup05DatPK;
        this.c5Costo = c5Costo;
        this.c5CantRequerida = c5CantRequerida;
        this.c5Unidades = c5Unidades;
        this.c5Depart = c5Depart;
        this.c5CodProceso = c5CodProceso;
        this.c5CodMaq = c5CodMaq;
        this.c5Origen = c5Origen;
        this.c5Sysdate = c5Sysdate;
        this.c5Observacion = c5Observacion;
    }

    public Sup05DatPK getSup05DatPK() {
        return sup05DatPK;
    }

    public void setSup05DatPK(Sup05DatPK sup05DatPK) {
        this.sup05DatPK = sup05DatPK;
    }

    public BigDecimal getC5Costo() {
        return c5Costo;
    }

    public void setC5Costo(BigDecimal c5Costo) {
        this.c5Costo = c5Costo;
    }

    public BigDecimal getC5CantRequerida() {
        return c5CantRequerida;
    }

    public void setC5CantRequerida(BigDecimal c5CantRequerida) {
        this.c5CantRequerida = c5CantRequerida;
    }

    public BigDecimal getC5Unidades() {
        return c5Unidades;
    }

    public void setC5Unidades(BigDecimal c5Unidades) {
        this.c5Unidades = c5Unidades;
    }

    public String getC5Depart() {
        return c5Depart;
    }

    public void setC5Depart(String c5Depart) {
        this.c5Depart = c5Depart;
    }

    public String getC5CodProceso() {
        return c5CodProceso;
    }

    public void setC5CodProceso(String c5CodProceso) {
        this.c5CodProceso = c5CodProceso;
    }

    public String getC5CodMaq() {
        return c5CodMaq;
    }

    public void setC5CodMaq(String c5CodMaq) {
        this.c5CodMaq = c5CodMaq;
    }

    public String getC5Origen() {
        return c5Origen;
    }

    public void setC5Origen(String c5Origen) {
        this.c5Origen = c5Origen;
    }

    public Date getC5Sysdate() {
        return c5Sysdate;
    }

    public void setC5Sysdate(Date c5Sysdate) {
        this.c5Sysdate = c5Sysdate;
    }

    public String getC5Observacion() {
        return c5Observacion;
    }

    public void setC5Observacion(String c5Observacion) {
        this.c5Observacion = c5Observacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sup05DatPK != null ? sup05DatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sup05Dat)) {
            return false;
        }
        Sup05Dat other = (Sup05Dat) object;
        if ((this.sup05DatPK == null && other.sup05DatPK != null) || (this.sup05DatPK != null && !this.sup05DatPK.equals(other.sup05DatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Sup05Dat[ sup05DatPK=" + sup05DatPK + " ]";
    }
    
}
