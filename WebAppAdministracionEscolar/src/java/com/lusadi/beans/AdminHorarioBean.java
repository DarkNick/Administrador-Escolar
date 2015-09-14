/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.CursoFacade;
import com.lusadi.dao.EstudianteFacade;
import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.HorarioFacade;
import com.lusadi.dao.SalonFacade;
import com.lusadi.entities.Curso;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Horario;
import com.lusadi.entities.Salon;
import com.lusadi.utils.UtilFaces;
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
public class AdminHorarioBean {

    @EJB
    private HorarioFacade horarioFacade;
    @EJB
    private FuncionarioFacade funcionarioFacade;
    @EJB
    private EstudianteFacade estudianteFacade;
    @EJB
    private SalonFacade salonFacade;
    @EJB
    private CursoFacade cursoFacade;

    private Horario horario = new Horario();
    private Map<String, Salon> salones;
    private Map<String, Curso> cursos;
    private Map<String, Estudiante> estudiantes;
    private Map<String, Funcionario> funcionarios;

    public AdminHorarioBean() {
    }

    @PostConstruct
    public void init() {
        salones = parseSalonesToMap(salonFacade.findAll());
        cursos = parseCursosToMap(cursoFacade.findAll());
        estudiantes = parseEstudianteToMap(estudianteFacade.findAll());
        funcionarios = parseFuncionarioToMap(funcionarioFacade.findAll());
    }

    public void createHorario() {
        try {
            horarioFacade.create(horario);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    private Map<String, Salon> parseSalonesToMap(List<Salon> findAll) {
        Map<String, Salon> outcome = new LinkedHashMap<String, Salon>();
        for (Salon s : findAll) {
            String key = s.getUbicacionSalon() + "/" + s.getCapacidad();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

    private Map<String, Curso> parseCursosToMap(List<Curso> findAll) {
        Map<String, Curso> outcome = new LinkedHashMap<String, Curso>();
        for (Curso s : findAll) {
            String key = Integer.toString(s.getCursoId());
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

    private Map<String, Estudiante> parseEstudianteToMap(List<Estudiante> findAll) {
        Map<String, Estudiante> outcome = new LinkedHashMap<String, Estudiante>();
        for (Estudiante s : findAll) {
            String key = s.getUsuario().getPrimerApellido() + " " + s.getUsuario().getSegundoApellido() + " " + s.getUsuario().getNombres();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

    private Map<String, Funcionario> parseFuncionarioToMap(List<Funcionario> findAll) {
        Map<String, Funcionario> outcome = new LinkedHashMap<String, Funcionario>();
        for (Funcionario s : findAll) {
            String key = s.getUsuario().getPrimerApellido() + " " + s.getUsuario().getSegundoApellido() + " " + s.getUsuario().getNombres();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Map<String, Salon> getSalones() {
        return salones;
    }

    public void setSalones(Map<String, Salon> salones) {
        this.salones = salones;
    }

    public Map<String, Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Map<String, Curso> cursos) {
        this.cursos = cursos;
    }

    public Map<String, Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Map<String, Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Map<String, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Map<String, Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
