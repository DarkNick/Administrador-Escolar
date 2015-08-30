/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "ESTUDIANTE", catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESTUDIATE_ID")
    private Integer estudiateId;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 45)
    @Column(name = "APELLIDOS_ACUDIENTE")
    private String apellidosAcudiente;
    @Size(max = 45)
    @Column(name = "NOMBRES_ACUDIENTE")
    private String nombresAcudiente;
    @JoinColumn(name = "PARENTESCO_FAMILIA_ID", referencedColumnName = "PARENTESCO_FAMILIA_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParentescoFamilia parentescoFamiliaId;
    @JoinColumns({
        @JoinColumn(name = "TIPO_ID", referencedColumnName = "TIPO_ID"),
        @JoinColumn(name = "NUMERO_ID", referencedColumnName = "NUMERO_ID")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteId", fetch = FetchType.LAZY)
    private List<MatriculaEstudiante> matriculaEstudianteList;

    public Estudiante() {
    }

    public Estudiante(Integer estudiateId) {
        this.estudiateId = estudiateId;
    }

    public Integer getEstudiateId() {
        return estudiateId;
    }

    public void setEstudiateId(Integer estudiateId) {
        this.estudiateId = estudiateId;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getApellidosAcudiente() {
        return apellidosAcudiente;
    }

    public void setApellidosAcudiente(String apellidosAcudiente) {
        this.apellidosAcudiente = apellidosAcudiente;
    }

    public String getNombresAcudiente() {
        return nombresAcudiente;
    }

    public void setNombresAcudiente(String nombresAcudiente) {
        this.nombresAcudiente = nombresAcudiente;
    }

    public ParentescoFamilia getParentescoFamiliaId() {
        return parentescoFamiliaId;
    }

    public void setParentescoFamiliaId(ParentescoFamilia parentescoFamiliaId) {
        this.parentescoFamiliaId = parentescoFamiliaId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<MatriculaEstudiante> getMatriculaEstudianteList() {
        return matriculaEstudianteList;
    }

    public void setMatriculaEstudianteList(List<MatriculaEstudiante> matriculaEstudianteList) {
        this.matriculaEstudianteList = matriculaEstudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiateId != null ? estudiateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.estudiateId == null && other.estudiateId != null) || (this.estudiateId != null && !this.estudiateId.equals(other.estudiateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.Estudiante[ estudiateId=" + estudiateId + " ]";
    }
    
}
