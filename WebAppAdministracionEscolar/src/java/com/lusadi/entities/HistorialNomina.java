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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "HISTORIAL_NOMINA", catalog = "colegio_lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialNomina.findAll", query = "SELECT h FROM HistorialNomina h")})
public class HistorialNomina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HISTORIAL_NOMINA_ID")
    private Integer historialNominaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "SOPORTE_ADJUNTO")
    private byte[] soporteAdjunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RESPONSABLE_PAGO")
    private String responsablePago;
    @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "FUNCIONARIO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionarioId;

    public HistorialNomina() {
    }

    public HistorialNomina(Integer historialNominaId) {
        this.historialNominaId = historialNominaId;
    }

    public HistorialNomina(Integer historialNominaId, Date fechaRegistro, String responsablePago) {
        this.historialNominaId = historialNominaId;
        this.fechaRegistro = fechaRegistro;
        this.responsablePago = responsablePago;
    }

    public Integer getHistorialNominaId() {
        return historialNominaId;
    }

    public void setHistorialNominaId(Integer historialNominaId) {
        this.historialNominaId = historialNominaId;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public byte[] getSoporteAdjunto() {
        return soporteAdjunto;
    }

    public void setSoporteAdjunto(byte[] soporteAdjunto) {
        this.soporteAdjunto = soporteAdjunto;
    }

    public String getResponsablePago() {
        return responsablePago;
    }

    public void setResponsablePago(String responsablePago) {
        this.responsablePago = responsablePago;
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialNominaId != null ? historialNominaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialNomina)) {
            return false;
        }
        HistorialNomina other = (HistorialNomina) object;
        if ((this.historialNominaId == null && other.historialNominaId != null) || (this.historialNominaId != null && !this.historialNominaId.equals(other.historialNominaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.HistorialNomina[ historialNominaId=" + historialNominaId + " ]";
    }
    
}
