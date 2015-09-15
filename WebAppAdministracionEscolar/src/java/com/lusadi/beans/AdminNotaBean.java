/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.NotaFacade;
import com.lusadi.dao.ResultadoAcademicoFacade;
import com.lusadi.entities.Nota;
import com.lusadi.entities.ResultadoAcademico;
import com.lusadi.utils.UtilFaces;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class AdminNotaBean {

    @EJB
    private NotaFacade notaFacade;
    @EJB
    private ResultadoAcademicoFacade resultadoAcademicoFacade;

    private Nota nota = new Nota();
    private ResultadoAcademico resultadoAcademico = new ResultadoAcademico();
    private ArrayList<Nota> notas = new ArrayList<Nota>();
    private BigDecimal suma = new BigDecimal("0");

    public AdminNotaBean() {
    }

    public void registrarNota() {
        try {
            notas.add(nota);
            suma.add(nota.getNota().multiply(nota.getPorcentaje()));
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void createNota() {
        try {
            notaFacade.createNota(nota);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void createResultado() {
        try {
            resultadoAcademico.setNotaDefinitiva(suma);
            resultadoAcademicoFacade.createResultadoAcademico(resultadoAcademico, notas);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public ResultadoAcademico getResultadoAcademico() {
        return resultadoAcademico;
    }

    public void setResultadoAcademico(ResultadoAcademico resultadoAcademico) {
        this.resultadoAcademico = resultadoAcademico;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

}
