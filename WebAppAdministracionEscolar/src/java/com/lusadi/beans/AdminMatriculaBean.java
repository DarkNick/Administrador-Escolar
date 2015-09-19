/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.CursoFacade;
import com.lusadi.dao.EstudianteFacade;
import com.lusadi.dao.MatriculaEstudianteFacade;
import com.lusadi.entities.Curso;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.MatriculaEstudiante;
import com.lusadi.utils.UtilFaces;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sebastian Vega
 */
@ManagedBean
@RequestScoped
public class AdminMatriculaBean {
    
    @EJB
    private EstudianteFacade estudianteFacade;
    @EJB
    private CursoFacade cursoFacade;
    @EJB
    private MatriculaEstudianteFacade matriculaEstudianteFacade;
    
    private MatriculaEstudiante matricula = new MatriculaEstudiante();
    private ArrayList<MatriculaEstudiante> matriculas = new ArrayList<MatriculaEstudiante>();
    private Map<String, Curso> cursos;
    private Map<String, Estudiante> estudiantes;
    
    public AdminMatriculaBean() {
    }
    
    @PostConstruct
    public void init() {
        cursos = parseCursosToMap(cursoFacade.findAll());
        estudiantes = parseEstudianteToMap(estudianteFacade.findAll());
        Map<String, Estudiante> treeMap = new TreeMap<String, Estudiante>(estudiantes);
        estudiantes.clear();
        estudiantes.putAll(treeMap);
    }
    
    public void createMatricula() {
        try {
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            boolean ban = false;
            String fechaMat = dia + "/" + (mes + 1) + "/" + anio;
            List<MatriculaEstudiante> matriculasAll = matriculaEstudianteFacade.findAll();
            for (MatriculaEstudiante aux : matriculasAll) {
                String arg[] = aux.getFechaMatricula().split("/");
                if ((matricula.getEstudianteId().equals(aux.getEstudianteId())) && (anio == Integer.parseInt(arg[2]))) {
                    ban = true;
                    break;
                }
            }
            if (!ban) {
                matricula.setFechaMatricula(fechaMat);
                matriculaEstudianteFacade.createMatricula(matricula);
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "El Registro Fue Realizado Correctamente");
            } else {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Ya se Encuentra Matriculado");
            }
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public void findAllMatriculas() {
        try {
            ArrayList<MatriculaEstudiante> result = (ArrayList<MatriculaEstudiante>) matriculaEstudianteFacade.findAll();
            if (result != null) {
                matriculas = result;
                Collections.sort(matriculas, new Comparator<MatriculaEstudiante>() {
                    @Override
                    public int compare(MatriculaEstudiante p1, MatriculaEstudiante p2) {
                        return new String(p1.getFechaMatricula()).compareTo(p2.getFechaMatricula());
                    }
                });
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    private Map<String, Curso> parseCursosToMap(List<Curso> findAll) {
        Map<String, Curso> outcome = new LinkedHashMap<String, Curso>();
        for (Curso s : findAll) {
            String key = "Curso:  " + s.getCursoId();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }
    
    private Map<String, Estudiante> parseEstudianteToMap(List<Estudiante> findAll) {
        Map<String, Estudiante> outcome = new LinkedHashMap<String, Estudiante>();
        for (Estudiante s : findAll) {
            String key = s.getUsuario().getPrimerApellido() + " " + s.getUsuario().getSegundoApellido() + ",  " + s.getUsuario().getNombres();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }
    
    public MatriculaEstudiante getMatricula() {
        return matricula;
    }
    
    public void setMatricula(MatriculaEstudiante matricula) {
        this.matricula = matricula;
    }
    
    public ArrayList<MatriculaEstudiante> getMatriculas() {
        return matriculas;
    }
    
    public void setMatriculas(ArrayList<MatriculaEstudiante> matriculas) {
        this.matriculas = matriculas;
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
    
}
