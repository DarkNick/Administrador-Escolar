/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lusadi.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "ASISTENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByAsistenciaId", query = "SELECT a FROM Asistencia a WHERE a.asistenciaId = :asistenciaId"),
    @NamedQuery(name = "Asistencia.findByFechaHoraIngreso", query = "SELECT a FROM Asistencia a WHERE a.fechaHoraIngreso = :fechaHoraIngreso"),
    @NamedQuery(name = "Asistencia.findByFechaHoraSalida", query = "SELECT a FROM Asistencia a WHERE a.fechaHoraSalida = :fechaHoraSalida")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASISTENCIA_ID")
    private Long asistenciaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FECHA_HORA_SALIDA")
    private String fechaHoraSalida;
    @JoinColumns({
        @JoinColumn(name = "USUARIO_TIPO_ID", referencedColumnName = "TIPO_ID"),
        @JoinColumn(name = "USUARIO_NUMERO_ID", referencedColumnName = "NUMERO_ID")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Asistencia() {
    }

    public Asistencia(Long asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public Asistencia(Long asistenciaId, Date fechaHoraIngreso, String fechaHoraSalida) {
        this.asistenciaId = asistenciaId;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Long getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(Long asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public Date getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(Date fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public String getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(String fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asistenciaId != null ? asistenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.asistenciaId == null && other.asistenciaId != null) || (this.asistenciaId != null && !this.asistenciaId.equals(other.asistenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.Asistencia[ asistenciaId=" + asistenciaId + " ]";
    }

}
