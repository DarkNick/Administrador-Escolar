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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "MATRICULA_ESTUDIANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatriculaEstudiante.findAll", query = "SELECT m FROM MatriculaEstudiante m"),
    @NamedQuery(name = "MatriculaEstudiante.findByMatriculaId", query = "SELECT m FROM MatriculaEstudiante m WHERE m.matriculaId = :matriculaId"),
    @NamedQuery(name = "MatriculaEstudiante.findByFechaMatricula", query = "SELECT m FROM MatriculaEstudiante m WHERE m.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "MatriculaEstudiante.findByCursoMatricula", query = "SELECT m FROM MatriculaEstudiante m WHERE m.cursoMatricula = :cursoMatricula"),
    @NamedQuery(name = "MatriculaEstudiante.findByValorMatricula", query = "SELECT m FROM MatriculaEstudiante m WHERE m.valorMatricula = :valorMatricula"),
    @NamedQuery(name = "MatriculaEstudiante.findByResultadoAcademicoId", query = "SELECT m FROM MatriculaEstudiante m WHERE m.resultadoAcademicoId = :resultadoAcademicoId")})
public class MatriculaEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATRICULA_ID")
    private Integer matriculaId;
    @Size(max = 45)
    @Column(name = "FECHA_MATRICULA")
    private String fechaMatricula;
    @Size(max = 45)
    @Column(name = "CURSO_MATRICULA")
    private String cursoMatricula;
    @Size(max = 45)
    @Column(name = "VALOR_MATRICULA")
    private String valorMatricula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESULTADO_ACADEMICO_ID")
    private int resultadoAcademicoId;
    @JoinColumn(name = "ESTUDIANTE_ID", referencedColumnName = "ESTUDIATE_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteId;
    @JoinColumn(name = "HORARIO_ID", referencedColumnName = "HORARIO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Horario horarioId;

    public MatriculaEstudiante() {
    }

    public MatriculaEstudiante(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public MatriculaEstudiante(Integer matriculaId, int resultadoAcademicoId) {
        this.matriculaId = matriculaId;
        this.resultadoAcademicoId = resultadoAcademicoId;
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

    public String getCursoMatricula() {
        return cursoMatricula;
    }

    public void setCursoMatricula(String cursoMatricula) {
        this.cursoMatricula = cursoMatricula;
    }

    public String getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(String valorMatricula) {
        this.valorMatricula = valorMatricula;
    }

    public int getResultadoAcademicoId() {
        return resultadoAcademicoId;
    }

    public void setResultadoAcademicoId(int resultadoAcademicoId) {
        this.resultadoAcademicoId = resultadoAcademicoId;
    }

    public Estudiante getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Horario getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Horario horarioId) {
        this.horarioId = horarioId;
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
