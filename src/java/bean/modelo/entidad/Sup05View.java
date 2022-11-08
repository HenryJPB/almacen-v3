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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author henrypb
 * ** NO ELIMINAR ** (Fue ajustado x el programador. Bqto: 15 octubre 2019 )
 */
@Entity
@Table(name = "SUP05_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sup05View.findAll", query = "SELECT s FROM Sup05View s"),
    @NamedQuery(name = "Sup05View.findByCoditem", query = "SELECT s FROM Sup05View s WHERE s.coditem = :coditem"),
    @NamedQuery(name = "Sup05View.findByDescripcion", query = "SELECT s FROM Sup05View s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sup05View.findByFecha", query = "SELECT s FROM Sup05View s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Sup05View.findByTipomov", query = "SELECT s FROM Sup05View s WHERE s.tipomov = :tipomov"),
    @NamedQuery(name = "Sup05View.findByCosto", query = "SELECT s FROM Sup05View s WHERE s.costo = :costo"),
    @NamedQuery(name = "Sup05View.findByCantrequerida", query = "SELECT s FROM Sup05View s WHERE s.cantrequerida = :cantrequerida"),
    @NamedQuery(name = "Sup05View.findByUnidades", query = "SELECT s FROM Sup05View s WHERE s.unidades = :unidades"),
    @NamedQuery(name = "Sup05View.findByReferencia", query = "SELECT s FROM Sup05View s WHERE s.referencia = :referencia"),
    @NamedQuery(name = "Sup05View.findByCentrocosto", query = "SELECT s FROM Sup05View s WHERE s.centrocosto = :centrocosto"),
    @NamedQuery(name = "Sup05View.findByCodproceso", query = "SELECT s FROM Sup05View s WHERE s.codproceso = :codproceso"),
    @NamedQuery(name = "Sup05View.findByCodmaquina", query = "SELECT s FROM Sup05View s WHERE s.codmaquina = :codmaquina"),
    @NamedQuery(name = "Sup05View.findByOrigen", query = "SELECT s FROM Sup05View s WHERE s.origen = :origen"),
    @NamedQuery(name = "Sup05View.findByClaveCompuesta", query = "SELECT s FROM Sup05View s WHERE s.coditem = :codItem and s.fecha = :fecha and s.tipomov = :tipoMov and s.referencia = :referencia")})  // Autor HJPB (Bqto: 15 octubre 2019)
public class Sup05View implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "CODITEM")
    @Id
    private String coditem;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    @Id
    private Date fecha;
    @Column(name = "TIPOMOV")
    @Id
    private String tipomov;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    private BigDecimal costo;
    @Column(name = "CANTREQUERIDA")
    private BigDecimal cantrequerida;
    @Column(name = "UNIDADES")
    private BigDecimal unidades;
    @Column(name = "REFERENCIA")
    @Id
    private String referencia;
    @Column(name = "CENTROCOSTO")
    private String centrocosto;
    @Column(name = "CODPROCESO")
    private String codproceso;
    @Column(name = "CODMAQUINA")
    private String codmaquina;
    @Column(name = "ORIGEN")
    private String origen;

    public Sup05View() {
    }

    public Sup05View(String coditem, String descripcion, Date fecha, String tipomov, BigDecimal costo, BigDecimal cantrequerida, BigDecimal unidades, String referencia, String centrocosto, String codproceso, String codmaquina, String origen) {
        this.coditem = coditem;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipomov = tipomov;
        this.costo = costo;
        this.cantrequerida = cantrequerida;
        this.unidades = unidades;
        this.referencia = referencia;
        this.centrocosto = centrocosto;
        this.codproceso = codproceso;
        this.codmaquina = codmaquina;
        this.origen = origen;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipomov() {
        return tipomov;
    }

    public void setTipomov(String tipomov) {
        this.tipomov = tipomov;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getCantrequerida() {
        return cantrequerida;
    }

    public void setCantrequerida(BigDecimal cantrequerida) {
        this.cantrequerida = cantrequerida;
    }

    public BigDecimal getUnidades() {
        return unidades;
    }

    public void setUnidades(BigDecimal unidades) {
        this.unidades = unidades;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCentrocosto() {
        return centrocosto;
    }

    public void setCentrocosto(String centrocosto) {
        this.centrocosto = centrocosto;
    }

    public String getCodproceso() {
        return codproceso;
    }

    public void setCodproceso(String codproceso) {
        this.codproceso = codproceso;
    }

    public String getCodmaquina() {
        return codmaquina;
    }

    public void setCodmaquina(String codmaquina) {
        this.codmaquina = codmaquina;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
    
}
