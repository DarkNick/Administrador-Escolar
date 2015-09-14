/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.CursoFacade;
import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.HorarioFacade;
import com.lusadi.dao.MateriaFacade;
import com.lusadi.dao.ResultadoAcademicoFacade;
import com.lusadi.entities.Curso;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Horario;
import com.lusadi.entities.Materia;
import com.lusadi.entities.ResultadoAcademico;
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
    private MateriaFacade materiaFacade;
    @EJB
    private ResultadoAcademicoFacade resultadoAcademicoFacade;
    @EJB
    private HorarioFacade horarioFacade;
    @EJB
    private FuncionarioFacade funcionarioFacade;
    @EJB
    private CursoFacade cursoFacade;

    private Horario horario = new Horario();
    private Map<String, Curso> cursos;
    private Map<String, Funcionario> funcionarios;
    private Map<String, Materia> materias;
    private Map<String, ResultadoAcademico> resultados;

    public AdminHorarioBean() {
    }

    @PostConstruct
    public void init() {
        cursos = parseCursosToMap(cursoFacade.findAll());
        funcionarios = parseFuncionarioToMap(funcionarioFacade.findAll());
        materias = parseMateriaToMap(materiaFacade.findAll());
        resultados = parseResultadoAcademicoToMap(resultadoAcademicoFacade.findAll());
    }

    public void createHorario() {
        try {
            boolean ban = false;
            List<Horario> horarioAll = horarioFacade.findAll();
            for (Horario varHorario : horarioAll) {
                if (varHorario.getFuncionarioId().getFuncionarioId().equals(horario.getFuncionarioId().getFuncionarioId())){
                    if ((varHorario.getDiaClase().equals(horario.getDiaClase())) && (varHorario.getHoraInicioClase().equals(horario.getHoraInicioClase()))){
                        ban = true;
                    }
                }
            }
            if ( !ban ) horarioFacade.createHorario(horario);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    private Map<String, Curso> parseCursosToMap(List<Curso> findAll) {
        Map<String, Curso> outcome = new LinkedHashMap<String, Curso>();
        for (Curso s : findAll) {
            String key = Integer.toString(s.getCursoId());
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

    private Map<String, Materia> parseMateriaToMap(List<Materia> findAll) {
        Map<String, Materia> outcome = new LinkedHashMap<String, Materia>();
        for (Materia s : findAll) {
            String key = s.getNombreMateria();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }

    private Map<String, ResultadoAcademico> parseResultadoAcademicoToMap(List<ResultadoAcademico> findAll) {
        Map<String, ResultadoAcademico> outcome = new LinkedHashMap<String, ResultadoAcademico>();
        for (ResultadoAcademico s : findAll) {
            String key = s.getObjetivoEvaluado();
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

    public Map<String, Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Map<String, Curso> cursos) {
        this.cursos = cursos;
    }

    public Map<String, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Map<String, Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Map<String, Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Map<String, Materia> materias) {
        this.materias = materias;
    }

    public Map<String, ResultadoAcademico> getResultados() {
        return resultados;
    }

    public void setResultados(Map<String, ResultadoAcademico> resultados) {
        this.resultados = resultados;
    }

}
