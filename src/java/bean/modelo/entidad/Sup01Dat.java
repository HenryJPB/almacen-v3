/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author henrypb
 */
@Entity
@Table(name = "SUP01_DAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sup01Dat.findAll", query = "SELECT s FROM Sup01Dat s"),
    @NamedQuery(name = "Sup01Dat.findByC1Codigo", query = "SELECT s FROM Sup01Dat s WHERE s.sup01DatPK.c1Codigo = :c1Codigo"),
    @NamedQuery(name = "Sup01Dat.findByC1Grupo", query = "SELECT s FROM Sup01Dat s WHERE s.sup01DatPK.c1Grupo = :c1Grupo"),
    @NamedQuery(name = "Sup01Dat.findByC1Descripcion", query = "SELECT s FROM Sup01Dat s WHERE s.c1Descripcion = :c1Descripcion"),
    @NamedQuery(name = "Sup01Dat.findByC1CodUbicacion", query = "SELECT s FROM Sup01Dat s WHERE s.c1CodUbicacion = :c1CodUbicacion"),
    @NamedQuery(name = "Sup01Dat.findByC1Um", query = "SELECT s FROM Sup01Dat s WHERE s.c1Um = :c1Um"),
    @NamedQuery(name = "Sup01Dat.findByC1NivelControl", query = "SELECT s FROM Sup01Dat s WHERE s.c1NivelControl = :c1NivelControl"),
    @NamedQuery(name = "Sup01Dat.findByC1StockMin", query = "SELECT s FROM Sup01Dat s WHERE s.c1StockMin = :c1StockMin"),
    @NamedQuery(name = "Sup01Dat.findByC1StockMax", query = "SELECT s FROM Sup01Dat s WHERE s.c1StockMax = :c1StockMax"),
    @NamedQuery(name = "Sup01Dat.findByC1PtoReorden", query = "SELECT s FROM Sup01Dat s WHERE s.c1PtoReorden = :c1PtoReorden"),
    @NamedQuery(name = "Sup01Dat.findByC1Indicador", query = "SELECT s FROM Sup01Dat s WHERE s.c1Indicador = :c1Indicador")})
public class Sup01Dat implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Sup01DatPK sup01DatPK;
    @Column(name = "C1_DESCRIPCION")
    private String c1Descripcion;
    @Column(name = "C1_COD_UBICACION")
    private String c1CodUbicacion;
    @Column(name = "C1_UM")
    private String c1Um;
    @Column(name = "C1_NIVEL_CONTROL")
    private String c1NivelControl;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "C1_STOCK_MIN")
    private BigDecimal c1StockMin;
    @Column(name = "C1_STOCK_MAX")
    private BigDecimal c1StockMax;
    @Column(name = "C1_PTO_REORDEN")
    private BigDecimal c1PtoReorden;
    @Column(name = "C1_INDICADOR")
    private String c1Indicador;

    public Sup01Dat() {
    }

    public Sup01Dat(Sup01DatPK sup01DatPK) {
        this.sup01DatPK = sup01DatPK;
    }

    public Sup01Dat(String c1Codigo, String c1Grupo) {
        this.sup01DatPK = new Sup01DatPK(c1Codigo, c1Grupo);
    }

    public Sup01DatPK getSup01DatPK() {
        return sup01DatPK;
    }

    public void setSup01DatPK(Sup01DatPK sup01DatPK) {
        this.sup01DatPK = sup01DatPK;
    }

    public String getC1Descripcion() {
        return c1Descripcion;
    }

    public void setC1Descripcion(String c1Descripcion) {
        this.c1Descripcion = c1Descripcion;
    }

    public String getC1CodUbicacion() {
        return c1CodUbicacion;
    }

    public void setC1CodUbicacion(String c1CodUbicacion) {
        this.c1CodUbicacion = c1CodUbicacion;
    }

    public String getC1Um() {
        return c1Um;
    }

    public void setC1Um(String c1Um) {
        this.c1Um = c1Um;
    }

    public String getC1NivelControl() {
        return c1NivelControl;
    }

    public void setC1NivelControl(String c1NivelControl) {
        this.c1NivelControl = c1NivelControl;
    }

    public BigDecimal getC1StockMin() {
        return c1StockMin;
    }

    public void setC1StockMin(BigDecimal c1StockMin) {
        this.c1StockMin = c1StockMin;
    }

    public BigDecimal getC1StockMax() {
        return c1StockMax;
    }

    public void setC1StockMax(BigDecimal c1StockMax) {
        this.c1StockMax = c1StockMax;
    }

    public BigDecimal getC1PtoReorden() {
        return c1PtoReorden;
    }

    public void setC1PtoReorden(BigDecimal c1PtoReorden) {
        this.c1PtoReorden = c1PtoReorden;
    }

    public String getC1Indicador() {
        return c1Indicador;
    }

    public void setC1Indicador(String c1Indicador) {
        this.c1Indicador = c1Indicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sup01DatPK != null ? sup01DatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sup01Dat)) {
            return false;
        }
        Sup01Dat other = (Sup01Dat) object;
        if ((this.sup01DatPK == null && other.sup01DatPK != null) || (this.sup01DatPK != null && !this.sup01DatPK.equals(other.sup01DatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.Sup01Dat[ sup01DatPK=" + sup01DatPK + " ]";
    }
    
}
