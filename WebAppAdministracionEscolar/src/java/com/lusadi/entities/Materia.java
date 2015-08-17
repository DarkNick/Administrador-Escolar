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
@Table(name = "materia", catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByMateriaId", query = "SELECT m FROM Materia m WHERE m.materiaId = :materiaId"),
    @NamedQuery(name = "Materia.findByNombreMateria", query = "SELECT m FROM Materia m WHERE m.nombreMateria = :nombreMateria"),
    @NamedQuery(name = "Materia.findByHorasSemana", query = "SELECT m FROM Materia m WHERE m.horasSemana = :horasSemana"),
    @NamedQuery(name = "Materia.findByObservacion", query = "SELECT m FROM Materia m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "Materia.findByNivelAcademico", query = "SELECT m FROM Materia m WHERE m.nivelAcademico = :nivelAcademico")})
public class Materia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATERIA_ID")
    private Integer materiaId;
    @Size(max = 255)
    @Column(name = "NOMBRE_MATERIA")
    private String nombreMateria;
    @Column(name = "HORAS_SEMANA")
    private Short horasSemana;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "NIVEL_ACADEMICO")
    private Integer nivelAcademico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaId", fetch = FetchType.LAZY)
    private List<Horario> horarioList;
    @JoinColumn(name = "SALON_SALON_ID", referencedColumnName = "SALON_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Salon salonSalonId;

    public Materia() {
    }

    public Materia(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Short getHorasSemana() {
        return horasSemana;
    }

    public void setHorasSemana(Short horasSemana) {
        this.horasSemana = horasSemana;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(Integer nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Salon getSalonSalonId() {
        return salonSalonId;
    }

    public void setSalonSalonId(Salon salonSalonId) {
        this.salonSalonId = salonSalonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materiaId != null ? materiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.materiaId == null && other.materiaId != null) || (this.materiaId != null && !this.materiaId.equals(other.materiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lusadi.entities.Materia[ materiaId=" + materiaId + " ]";
    }
    
}
