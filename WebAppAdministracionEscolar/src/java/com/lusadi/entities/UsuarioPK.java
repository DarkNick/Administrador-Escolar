/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Familia
 */
@Embeddable
public class UsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TIPO_ID")
    private String tipoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ID")
    private long numeroId;

    public UsuarioPK() {
    }

    public UsuarioPK(String tipoId, long numeroId) {
        this.tipoId = tipoId;
        this.numeroId = numeroId;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public long getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(long numeroId) {
        this.numeroId = numeroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoId != null ? tipoId.hashCode() : 0);
        hash += (int) numeroId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPK)) {
            return false;
        }
        UsuarioPK other = (UsuarioPK) object;
        if ((this.tipoId == null && other.tipoId != null) || (this.tipoId != null && !this.tipoId.equals(other.tipoId))) {
            return false;
        }
        if (this.numeroId != other.numeroId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.UsuarioPK[ tipoId=" + tipoId + ", numeroId=" + numeroId + " ]";
    }
    
}
