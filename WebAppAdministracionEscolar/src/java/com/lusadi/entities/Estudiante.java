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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "ESTUDIANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByEstudiateId", query = "SELECT e FROM Estudiante e WHERE e.estudiateId = :estudiateId"),
    @NamedQuery(name = "Estudiante.findByFechaIngreso", query = "SELECT e FROM Estudiante e WHERE e.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Estudiante.findByCorreoElectronico", query = "SELECT e FROM Estudiante e WHERE e.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Estudiante.findByApellidosAcudiente", query = "SELECT e FROM Estudiante e WHERE e.apellidosAcudiente = :apellidosAcudiente"),
    @NamedQuery(name = "Estudiante.findByNombresAcudiente", query = "SELECT e FROM Estudiante e WHERE e.nombresAcudiente = :nombresAcudiente")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTUDIATE_ID")
    private Integer estudiateId;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 45)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @Size(max = 45)
    @Column(name = "APELLIDOS_ACUDIENTE")
    private String apellidosAcudiente;
    @Size(max = 45)
    @Column(name = "NOMBRES_ACUDIENTE")
    private String nombresAcudiente;
    @JoinColumns({
        @JoinColumn(name = "USUARIO_TIPO_ID", referencedColumnName = "TIPO_ID"),
        @JoinColumn(name = "USUARIO_NUMERO_ID", referencedColumnName = "NUMERO_ID")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "PARENTESCO_FAMILIA_ID", referencedColumnName = "PARENTESCO_FAMILIA_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParentescoFamilia parentescoFamiliaId;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ParentescoFamilia getParentescoFamiliaId() {
        return parentescoFamiliaId;
    }

    public void setParentescoFamiliaId(ParentescoFamilia parentescoFamiliaId) {
        this.parentescoFamiliaId = parentescoFamiliaId;
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
