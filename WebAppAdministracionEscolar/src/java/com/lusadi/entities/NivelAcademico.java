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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "NIVEL_ACADEMICO", catalog = "lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelAcademico.findAll", query = "SELECT n FROM NivelAcademico n")})
public class NivelAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NIVEL_ACADEMICO_ID")
    private Integer nivelAcademicoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_NIVEL")
    private String nombreNivel;
    @Size(max = 225)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelAcademicoId", fetch = FetchType.LAZY)
    private List<Materia> materiaList;

    public NivelAcademico() {
    }

    public NivelAcademico(Integer nivelAcademicoId) {
        this.nivelAcademicoId = nivelAcademicoId;
    }

    public NivelAcademico(Integer nivelAcademicoId, String nombreNivel) {
        this.nivelAcademicoId = nivelAcademicoId;
        this.nombreNivel = nombreNivel;
    }

    public Integer getNivelAcademicoId() {
        return nivelAcademicoId;
    }

    public void setNivelAcademicoId(Integer nivelAcademicoId) {
        this.nivelAcademicoId = nivelAcademicoId;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nivelAcademicoId != null ? nivelAcademicoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAcademico)) {
            return false;
        }
        NivelAcademico other = (NivelAcademico) object;
        if ((this.nivelAcademicoId == null && other.nivelAcademicoId != null) || (this.nivelAcademicoId != null && !this.nivelAcademicoId.equals(other.nivelAcademicoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.NivelAcademico[ nivelAcademicoId=" + nivelAcademicoId + " ]";
    }
    
}
