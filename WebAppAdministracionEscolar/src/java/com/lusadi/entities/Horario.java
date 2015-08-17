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
import javax.persistence.ManyToOne;
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
 * @author Personal
 */
@Entity
@Table(name = "horario", catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHorarioId", query = "SELECT h FROM Horario h WHERE h.horarioId = :horarioId"),
    @NamedQuery(name = "Horario.findByDiaClase", query = "SELECT h FROM Horario h WHERE h.diaClase = :diaClase"),
    @NamedQuery(name = "Horario.findByHoraInicioClase", query = "SELECT h FROM Horario h WHERE h.horaInicioClase = :horaInicioClase"),
    @NamedQuery(name = "Horario.findByHoraFinClase", query = "SELECT h FROM Horario h WHERE h.horaFinClase = :horaFinClase")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORARIO_ID")
    private Integer horarioId;
    @Size(max = 45)
    @Column(name = "DIA_CLASE")
    private String diaClase;
    @Size(max = 45)
    @Column(name = "HORA_INICIO_CLASE")
    private String horaInicioClase;
    @Size(max = 45)
    @Column(name = "HORA_FIN_CLASE")
    private String horaFinClase;
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoId;
    @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "FUNCIONARIO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionarioId;
    @JoinColumn(name = "MATERIA_ID", referencedColumnName = "MATERIA_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materia materiaId;
    @JoinColumn(name = "RESULTADO_ACADEMICO_ID", referencedColumnName = "RESULTADO_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ResultadoAcademico resultadoAcademicoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horarioId", fetch = FetchType.LAZY)
    private List<MatriculaEstudiante> matriculaEstudianteList;

    public Horario() {
    }

    public Horario(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public Integer getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public String getDiaClase() {
        return diaClase;
    }

    public void setDiaClase(String diaClase) {
        this.diaClase = diaClase;
    }

    public String getHoraInicioClase() {
        return horaInicioClase;
    }

    public void setHoraInicioClase(String horaInicioClase) {
        this.horaInicioClase = horaInicioClase;
    }

    public String getHoraFinClase() {
        return horaFinClase;
    }

    public void setHoraFinClase(String horaFinClase) {
        this.horaFinClase = horaFinClase;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Materia getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Materia materiaId) {
        this.materiaId = materiaId;
    }

    public ResultadoAcademico getResultadoAcademicoId() {
        return resultadoAcademicoId;
    }

    public void setResultadoAcademicoId(ResultadoAcademico resultadoAcademicoId) {
        this.resultadoAcademicoId = resultadoAcademicoId;
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
        hash += (horarioId != null ? horarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horarioId == null && other.horarioId != null) || (this.horarioId != null && !this.horarioId.equals(other.horarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.Horario[ horarioId=" + horarioId + " ]";
    }
    
}
