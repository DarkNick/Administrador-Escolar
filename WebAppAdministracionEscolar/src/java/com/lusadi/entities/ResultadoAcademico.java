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
import javax.persistence.Lob;
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
@Table(name = "RESULTADO_ACADEMICO", catalog = "colegio_lusadi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadoAcademico.findAll", query = "SELECT r FROM ResultadoAcademico r")})
public class ResultadoAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESULTADO_ACADEMICO_ID")
    private Integer resultadoAcademicoId;
    @Lob
    @Size(max = 65535)
    @Column(name = "OBJETIVO_EVALUADO")
    private String objetivoEvaluado;
    @Column(name = "NOTA_DEFINITIVA")
    private Short notaDefinitiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultadoAcademicoId", fetch = FetchType.LAZY)
    private List<Nota> notaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultadoAcademicoId", fetch = FetchType.LAZY)
    private List<Horario> horarioList;

    public ResultadoAcademico() {
    }

    public ResultadoAcademico(Integer resultadoAcademicoId) {
        this.resultadoAcademicoId = resultadoAcademicoId;
    }

    public Integer getResultadoAcademicoId() {
        return resultadoAcademicoId;
    }

    public void setResultadoAcademicoId(Integer resultadoAcademicoId) {
        this.resultadoAcademicoId = resultadoAcademicoId;
    }

    public String getObjetivoEvaluado() {
        return objetivoEvaluado;
    }

    public void setObjetivoEvaluado(String objetivoEvaluado) {
        this.objetivoEvaluado = objetivoEvaluado;
    }

    public Short getNotaDefinitiva() {
        return notaDefinitiva;
    }

    public void setNotaDefinitiva(Short notaDefinitiva) {
        this.notaDefinitiva = notaDefinitiva;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
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
        hash += (resultadoAcademicoId != null ? resultadoAcademicoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoAcademico)) {
            return false;
        }
        ResultadoAcademico other = (ResultadoAcademico) object;
        if ((this.resultadoAcademicoId == null && other.resultadoAcademicoId != null) || (this.resultadoAcademicoId != null && !this.resultadoAcademicoId.equals(other.resultadoAcademicoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.ResultadoAcademico[ resultadoAcademicoId=" + resultadoAcademicoId + " ]";
    }
    
}
