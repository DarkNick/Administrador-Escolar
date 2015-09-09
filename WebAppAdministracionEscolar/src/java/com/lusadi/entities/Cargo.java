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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "CARGO", catalog = "colegio_lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CARGO_ID")
    private Integer cargoId;
    @Size(max = 45)
    @Column(name = "NOMBRE_CARGO")
    private String nombreCargo;
    @Size(max = 45)
    @Column(name = "SALARIO_TOPE_MIN")
    private String salarioTopeMin;
    @Size(max = 45)
    @Column(name = "SALARIO_TOPE_MAX")
    private String salarioTopeMax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoId", fetch = FetchType.LAZY)
    private List<Funcionario> funcionarioList;

    public Cargo() {
    }

    public Cargo(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getSalarioTopeMin() {
        return salarioTopeMin;
    }

    public void setSalarioTopeMin(String salarioTopeMin) {
        this.salarioTopeMin = salarioTopeMin;
    }

    public String getSalarioTopeMax() {
        return salarioTopeMax;
    }

    public void setSalarioTopeMax(String salarioTopeMax) {
        this.salarioTopeMax = salarioTopeMax;
    }

    @XmlTransient
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoId != null ? cargoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.cargoId == null && other.cargoId != null) || (this.cargoId != null && !this.cargoId.equals(other.cargoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.Cargo[ cargoId=" + cargoId + " ]";
    }
    
}
