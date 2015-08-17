/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "funcionario", catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByFuncionarioId", query = "SELECT f FROM Funcionario f WHERE f.funcionarioId = :funcionarioId"),
    @NamedQuery(name = "Funcionario.findBySaldoNeto", query = "SELECT f FROM Funcionario f WHERE f.saldoNeto = :saldoNeto")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUNCIONARIO_ID")
    private Integer funcionarioId;
    @Column(name = "SALDO_NETO")
    private Long saldoNeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId", fetch = FetchType.LAZY)
    private List<Horario> horarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId", fetch = FetchType.LAZY)
    private List<AdjuntoNomina> adjuntoNominaList;
    @JoinColumn(name = "CARGO_CARGO_ID", referencedColumnName = "CARGO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cargo cargoCargoId;
    @JoinColumns({
        @JoinColumn(name = "USUARIO_TIPO_ID", referencedColumnName = "TIPO_ID"),
        @JoinColumn(name = "USUARIO_NUMERO_ID", referencedColumnName = "NUMERO_ID")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Funcionario() {
    }

    public Funcionario(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Long getSaldoNeto() {
        return saldoNeto;
    }

    public void setSaldoNeto(Long saldoNeto) {
        this.saldoNeto = saldoNeto;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    @XmlTransient
    public List<AdjuntoNomina> getAdjuntoNominaList() {
        return adjuntoNominaList;
    }

    public void setAdjuntoNominaList(List<AdjuntoNomina> adjuntoNominaList) {
        this.adjuntoNominaList = adjuntoNominaList;
    }

    public Cargo getCargoCargoId() {
        return cargoCargoId;
    }

    public void setCargoCargoId(Cargo cargoCargoId) {
        this.cargoCargoId = cargoCargoId;
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
        hash += (funcionarioId != null ? funcionarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.funcionarioId == null && other.funcionarioId != null) || (this.funcionarioId != null && !this.funcionarioId.equals(other.funcionarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.Funcionario[ funcionarioId=" + funcionarioId + " ]";
    }
    
}
