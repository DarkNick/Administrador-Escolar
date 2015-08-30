/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.CursoFacade;
import com.lusadi.entities.Curso;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class AdminCursoBean implements Serializable {

    @EJB
    private CursoFacade cursoFacade;

    private Curso curso = new Curso();
    private ArrayList<Curso> cursos = new ArrayList<Curso>();

    public AdminCursoBean() {
    }

    public void createCurso() {
        try {
            cursoFacade.createCurso(curso);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void findAllCursos() {
        try {
            ArrayList<Curso> resultAll = new ArrayList<Curso>();
            resultAll = cursoFacade.findAllCursos();
            if (resultAll != null) {
                cursos = resultAll;
                Collections.sort(cursos, new Comparator<Curso>() {
                    @Override
                    public int compare(Curso p1, Curso p2) {
                        return new Integer(p1.getCursoId()).compareTo(p2.getCursoId());
                    }
                });
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

}
