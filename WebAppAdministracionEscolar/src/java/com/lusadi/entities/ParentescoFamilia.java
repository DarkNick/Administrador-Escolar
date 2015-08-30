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
@Table(name = "PARENTESCO_FAMILIA", catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentescoFamilia.findAll", query = "SELECT p FROM ParentescoFamilia p")})
public class ParentescoFamilia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PARENTESCO_FAMILIA_ID")
    private Integer parentescoFamiliaId;
    @Size(max = 45)
    @Column(name = "PARENTESCO")
    private String parentesco;
    @Size(max = 45)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentescoFamiliaId", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;

    public ParentescoFamilia() {
    }

    public ParentescoFamilia(Integer parentescoFamiliaId) {
        this.parentescoFamiliaId = parentescoFamiliaId;
    }

    public Integer getParentescoFamiliaId() {
        return parentescoFamiliaId;
    }

    public void setParentescoFamiliaId(Integer parentescoFamiliaId) {
        this.parentescoFamiliaId = parentescoFamiliaId;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentescoFamiliaId != null ? parentescoFamiliaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentescoFamilia)) {
            return false;
        }
        ParentescoFamilia other = (ParentescoFamilia) object;
        if ((this.parentescoFamiliaId == null && other.parentescoFamiliaId != null) || (this.parentescoFamiliaId != null && !this.parentescoFamiliaId.equals(other.parentescoFamiliaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.ParentescoFamilia[ parentescoFamiliaId=" + parentescoFamiliaId + " ]";
    }
    
}
