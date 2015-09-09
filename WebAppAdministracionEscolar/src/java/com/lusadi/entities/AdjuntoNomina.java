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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "ADJUNTO_NOMINA", catalog = "colegio_lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdjuntoNomina.findAll", query = "SELECT a FROM AdjuntoNomina a")})
public class AdjuntoNomina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADJUNTO_ID")
    private Integer adjuntoId;
    @Column(name = "FECHA_GENERACION_RECIBO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracionRecibo;
    @Lob
    @Column(name = "RECIBO_NOMINA")
    private byte[] reciboNomina;
    @Lob
    @Column(name = "RECIBO_NOMINA_FIRMADO")
    private byte[] reciboNominaFirmado;
    @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "FUNCIONARIO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionarioId;

    public AdjuntoNomina() {
    }

    public AdjuntoNomina(Integer adjuntoId) {
        this.adjuntoId = adjuntoId;
    }

    public Integer getAdjuntoId() {
        return adjuntoId;
    }

    public void setAdjuntoId(Integer adjuntoId) {
        this.adjuntoId = adjuntoId;
    }

    public Date getFechaGeneracionRecibo() {
        return fechaGeneracionRecibo;
    }

    public void setFechaGeneracionRecibo(Date fechaGeneracionRecibo) {
        this.fechaGeneracionRecibo = fechaGeneracionRecibo;
    }

    public byte[] getReciboNomina() {
        return reciboNomina;
    }

    public void setReciboNomina(byte[] reciboNomina) {
        this.reciboNomina = reciboNomina;
    }

    public byte[] getReciboNominaFirmado() {
        return reciboNominaFirmado;
    }

    public void setReciboNominaFirmado(byte[] reciboNominaFirmado) {
        this.reciboNominaFirmado = reciboNominaFirmado;
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
        hash += (adjuntoId != null ? adjuntoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdjuntoNomina)) {
            return false;
        }
        AdjuntoNomina other = (AdjuntoNomina) object;
        if ((this.adjuntoId == null && other.adjuntoId != null) || (this.adjuntoId != null && !this.adjuntoId.equals(other.adjuntoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.AdjuntoNomina[ adjuntoId=" + adjuntoId + " ]";
    }
    
}
