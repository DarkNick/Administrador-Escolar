/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.MateriaFacade;
import com.lusadi.dao.NivelAcademicoFacade;
import com.lusadi.dao.SalonFacade;
import com.lusadi.entities.Materia;
import com.lusadi.entities.NivelAcademico;
import com.lusadi.entities.Salon;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Personal
 */
@ManagedBean
@RequestScoped
public class AdminMateriaBean implements Serializable {

    @EJB
    private SalonFacade salonFacade;
    @EJB
    private MateriaFacade materiaFacade;
    @EJB
    private NivelAcademicoFacade nivelAcademicoFacade;

    private Materia materia = new Materia();
    private ArrayList<Materia> materias;

    private Map<String, Salon> salones;
    private Map<String, NivelAcademico> nivelesAcademicos;

    public AdminMateriaBean() {
    }

    @PostConstruct
    public void init() {
        salones = parseSalonesToMap(salonFacade.findAll());
        nivelesAcademicos = parseMatriculaEstudianteToMap(nivelAcademicoFacade.findAll());
        materias = new ArrayList<Materia>(materiaFacade.findAll());
    }

    public void createCourse() {
        materiaFacade.create(materia);
        materias = new ArrayList<Materia>(materiaFacade.findAll());
        materia = new Materia();
    }

    public void findAllMaterias() {
        try {
            ArrayList<Materia> result = (ArrayList<Materia>) materiaFacade.findAll();
            if (result != null) {
                materias = result;
                Collections.sort(materias, new Comparator<Materia>() {
                    @Override
                    public int compare(Materia p1, Materia p2) {
                        return new String(p1.getNombreMateria()).compareTo(p2.getNombreMateria());
                    }
                });
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public Map<String, NivelAcademico> getNivelesAcademicos() {
        return nivelesAcademicos;
    }

    public void setNivelesAcademicos(Map<String, NivelAcademico> nivelesAcademicos) {
        this.nivelesAcademicos = nivelesAcademicos;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public Map<String, Salon> getSalones() {
        return salones;
    }

    public void setSalones(Map<String, Salon> salones) {
        this.salones = salones;
    }

    private Map<String, Salon> parseSalonesToMap(List<Salon> findAll) {
        Map<String, Salon> outcome = new LinkedHashMap<String, Salon>();
        for (Salon s : findAll) {
            String key = s.getUbicacionSalon() + "/" + s.getCapacidad();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

    private Map<String, NivelAcademico> parseMatriculaEstudianteToMap(List<NivelAcademico> findAll) {
        Map<String, NivelAcademico> outcome = new LinkedHashMap<String, NivelAcademico>();
        for (NivelAcademico s : findAll) {
            String key = s.getNombreNivel();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

}
