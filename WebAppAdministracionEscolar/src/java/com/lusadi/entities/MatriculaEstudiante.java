/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "MATRICULA_ESTUDIANTE", catalog = "colegio_lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatriculaEstudiante.findAll", query = "SELECT m FROM MatriculaEstudiante m")})
public class MatriculaEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MATRICULA_ID")
    private Integer matriculaId;
    @Size(max = 45)
    @Column(name = "FECHA_MATRICULA")
    private String fechaMatricula;
    @Size(max = 45)
    @Column(name = "VALOR_MATRICULA")
    private String valorMatricula;
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoId;
    @JoinColumn(name = "ESTUDIANTE_ID", referencedColumnName = "ESTUDIATE_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteId;

    public MatriculaEstudiante() {
    }

    public MatriculaEstudiante(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(String valorMatricula) {
        this.valorMatricula = valorMatricula;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    public Estudiante getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaId != null ? matriculaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaEstudiante)) {
            return false;
        }
        MatriculaEstudiante other = (MatriculaEstudiante) object;
        if ((this.matriculaId == null && other.matriculaId != null) || (this.matriculaId != null && !this.matriculaId.equals(other.matriculaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.MatriculaEstudiante[ matriculaId=" + matriculaId + " ]";
    }
    
}
