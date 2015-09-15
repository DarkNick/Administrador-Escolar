/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.EstudianteFacade;
import com.lusadi.entities.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * @author darknick
 */
@Named(value = "adminAlumnoBusqueda")
@ViewScoped
public class AdminAlumnoBusquedaBean implements Serializable{

    @EJB
    private EstudianteFacade estudianteFacade;
    private List<Estudiante> listBusquedaAlumno = new ArrayList<>();
    private List<Estudiante> filteredListBusquedaAlumno;

    public EstudianteFacade getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacade estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
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

    @PostConstruct
    public void init() {
        this.listBusquedaAlumno = estudianteFacade.findAll();
        System.err.println("************************");
    }
        public AdminAlumnoBusquedaBean() {     
    }
}
