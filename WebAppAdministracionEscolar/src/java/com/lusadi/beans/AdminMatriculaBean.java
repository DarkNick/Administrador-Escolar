/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.CursoFacade;
import com.lusadi.dao.MatriculaEstudianteFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Curso;
import com.lusadi.entities.MatriculaEstudiante;
import com.lusadi.entities.Usuario;
import com.lusadi.utils.UtilFaces;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
public class AdminMatriculaBean {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private CursoFacade cursoFacade;
    @EJB
    private MatriculaEstudianteFacade matriculaEstudianteFacade;

    private MatriculaEstudiante matricula = new MatriculaEstudiante();
    private ArrayList<MatriculaEstudiante> matriculas = new ArrayList<MatriculaEstudiante>();
    private HashMap<Integer, Integer> cursos = new HashMap<Integer, Integer>();
    private HashMap<String, String> estudiantes = new HashMap<String, String>();

    public AdminMatriculaBean() {
    }

    public void createMatricula() {
        try {
            matriculaEstudianteFacade.createMatricula(matricula);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void findAllMatriculas() {
        try {
            ArrayList<MatriculaEstudiante> result = matriculaEstudianteFacade.findAllMAtriculas();
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

    public void findAllCursos() {
        try {
            ArrayList<Curso> cursosAdd = new ArrayList<Curso>();
            cursosAdd = cursoFacade.findAllCursos();
            if (cursosAdd != null) {
                Collections.sort(cursosAdd, new Comparator<Curso>() {
                    @Override
                    public int compare(Curso p1, Curso p2) {
                        return new Integer(p1.getCursoId()).compareTo(p2.getCursoId());
                    }
                });
                for (Curso varSalon : cursosAdd) {
                    cursos.put(varSalon.getCursoId(), varSalon.getCursoId());
                }
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void findAllEstudiantes() {
        try {
            ArrayList<Usuario> estudiantesAdd = new ArrayList<Usuario>();
            estudiantesAdd = (ArrayList<Usuario>) usuarioFacade.findAll();
            if (estudiantesAdd != null) {
                Collections.sort(estudiantesAdd, new Comparator<Usuario>() {
                    @Override
                    public int compare(Usuario p1, Usuario p2) {
                        String aux1 = p1.getPrimerApellido() + " " + p1.getSegundoApellido() + " " + p1.getNombres();
                        String aux2 = p2.getPrimerApellido() + " " + p2.getSegundoApellido() + " " + p2.getNombres();
                        return new String(aux1).compareTo(aux2);
                    }
                });
                for (Usuario varSalon : estudiantesAdd) {
                    String aux1 = varSalon.getPrimerApellido() + " " + varSalon.getSegundoApellido() + " " + varSalon.getNombres();
                    estudiantes.put(aux1, varSalon.getNombres()); // --- cambiar esto
                }
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }
}
