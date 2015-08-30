/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.MatriculaEstudianteFacade;
import com.lusadi.entities.MatriculaEstudiante;
import com.lusadi.utils.UtilFaces;
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
public class AdminMatriculaBean {

    @EJB
    private MatriculaEstudianteFacade matriculaEstudianteFacade;

    private MatriculaEstudiante matricula = new MatriculaEstudiante();
    private ArrayList<MatriculaEstudiante> matriculas = new ArrayList<MatriculaEstudiante>();

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
}
