/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.EstudianteFacade;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * @author darknick
 */
@Named(value = "adminAlumnoBusqueda")
@ViewScoped
public class AdminAlumnoBusquedaBean {

    @EJB
    private EstudianteFacade estudianteFacade;
    private boolean filtroAllRegisters = true;
    private String campoBusquedaAlumno = "";
    private UsuarioPK usuarioPK = new UsuarioPK();

    private List<Estudiante> listTemporalAlumno;
    private List<Estudiante> listBusquedaAlumno = new ArrayList<>();
    private List<Estudiante> filteredListBusquedaAlumno;

    public EstudianteFacade getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacade estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public boolean isFiltroAllRegisters() {
        return filtroAllRegisters;
    }

    public void setFiltroAllRegisters(boolean filtroAllRegisters) {
        this.filtroAllRegisters = filtroAllRegisters;
    }

    public String getCampoBusquedaAlumno() {
        return campoBusquedaAlumno;
    }

    public void setCampoBusquedaAlumno(String campoBusquedaAlumno) {
        this.campoBusquedaAlumno = campoBusquedaAlumno;
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public List<Estudiante> getListTemporalAlumno() {
        return listTemporalAlumno;
    }

    public void setListTemporalAlumno(List<Estudiante> listTemporalAlumno) {
        this.listTemporalAlumno = listTemporalAlumno;
    }

    public List<Estudiante> getListBusquedaAlumno() {
        return listBusquedaAlumno;
    }

    public void setListBusquedaAlumno(List<Estudiante> listBusquedaAlumno) {
        this.listBusquedaAlumno = listBusquedaAlumno;
    }

    public List<Estudiante> getFilteredListBusquedaAlumno() {
        return filteredListBusquedaAlumno;
    }

    public void setFilteredListBusquedaAlumno(List<Estudiante> filteredListBusquedaAlumno) {
        this.filteredListBusquedaAlumno = filteredListBusquedaAlumno;
    }

    public AdminAlumnoBusquedaBean() {     
    }
    public void actionBusquedaAlumno() {
        if (!filtroAllRegisters && campoBusquedaAlumno.isEmpty()) {
            System.out.println("MMM TRUE");
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "INGRESE UN NUMERO DOCUMENTO VALIDO.");
        } else {
            System.out.println("MMM FALSE");
            this.listBusquedaAlumno = estudianteFacade.findAlumnoByUsuario(((filtroAllRegisters) ? -1 : Long.parseLong(campoBusquedaAlumno)));
            RequestContext.getCurrentInstance().update("alumno-table");
        }
    }
}
