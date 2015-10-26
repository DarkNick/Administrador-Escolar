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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "FUNCIONARIO", catalog = "lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FUNCIONARIO_ID")
    private Integer funcionarioId;
    @Column(name = "SALDO_NETO")
    private Long saldoNeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId", fetch = FetchType.LAZY)
    private List<HistorialNomina> historialNominaList;
    @JoinColumn(name = "CARGO_ID", referencedColumnName = "CARGO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cargo cargoId;
    @JoinColumns({
        @JoinColumn(name = "TIPO_ID", referencedColumnName = "TIPO_ID"),
        @JoinColumn(name = "NUMERO_ID", referencedColumnName = "NUMERO_ID")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId", fetch = FetchType.LAZY)
    private List<Horario> horarioList;

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
    public List<HistorialNomina> getHistorialNominaList() {
        return historialNominaList;
    }

    public void setHistorialNominaList(List<HistorialNomina> historialNominaList) {
        this.historialNominaList = historialNominaList;
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
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
