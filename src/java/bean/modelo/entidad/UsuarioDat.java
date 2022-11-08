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
 * @author hpulgar
 */
@Entity
@Table(name = "USUARIO_DAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioDat.findAll", query = "SELECT u FROM UsuarioDat u"),
    @NamedQuery(name = "UsuarioDat.findByUsuario", query = "SELECT u FROM UsuarioDat u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UsuarioDat.findByContrasena", query = "SELECT u FROM UsuarioDat u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "UsuarioDat.findByFcreacion", query = "SELECT u FROM UsuarioDat u WHERE u.fcreacion = :fcreacion"),
    @NamedQuery(name = "UsuarioDat.findByFactualizacion", query = "SELECT u FROM UsuarioDat u WHERE u.factualizacion = :factualizacion")})
public class UsuarioDat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Column(name = "FCREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcreacion;
    @Column(name = "FACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factualizacion;

    public UsuarioDat() {
    }

    public UsuarioDat(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFcreacion() {
        return fcreacion;
    }

    public void setFcreacion(Date fcreacion) {
        this.fcreacion = fcreacion;
    }

    public Date getFactualizacion() {
        return factualizacion;
    }

    public void setFactualizacion(Date factualizacion) {
        this.factualizacion = factualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioDat)) {
            return false;
        }
        UsuarioDat other = (UsuarioDat) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.modelo.entidad.UsuarioDat[ usuario=" + usuario + " ]";
    }
    
}
