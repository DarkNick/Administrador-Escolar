/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.EstudianteFacade;
import com.lusadi.dao.ParentescoFamiliaFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.ParentescoFamilia;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 * @author Dark_Nick
 */
@ManagedBean(name = "adminEstudiantesModificar")
@ViewScoped
public class AdminEstudianteModificarBean implements Serializable {

    @EJB
    private EstudianteFacade estudianteFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ParentescoFamiliaFacade parentescoFamiliaFacade;
    private List<Estudiante> listEstudiantes = new ArrayList<>();
    private List<Estudiante> filteredListBusquedaEstudiante;
    private Map<String, ParentescoFamilia> parentescoFamilias;

    public ParentescoFamiliaFacade getParentescoFamiliaFacade() {
        return parentescoFamiliaFacade;
    }

    public void setParentescoFamiliaFacade(ParentescoFamiliaFacade parentescoFamiliaFacade) {
        this.parentescoFamiliaFacade = parentescoFamiliaFacade;
    }

    public Map<String, ParentescoFamilia> getParentescoFamilias() {
        return parentescoFamilias;
    }

    public void setParentescoFamilias(Map<String, ParentescoFamilia> parentescoFamilias) {
        this.parentescoFamilias = parentescoFamilias;
    }

    public EstudianteFacade getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacade estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<Estudiante> getListEstudiantes() {
        return listEstudiantes;
    }

    public void setListEstudiantes(List<Estudiante> listEstudiantes) {
        this.listEstudiantes = listEstudiantes;
    }

    public List<Estudiante> getFilteredListBusquedaEstudiante() {
        return filteredListBusquedaEstudiante;
    }

    public void setFilteredListBusquedaEstudiante(List<Estudiante> filteredListBusquedaEstudiante) {
        this.filteredListBusquedaEstudiante = filteredListBusquedaEstudiante;
    }

    @PostConstruct
    public void init() {
        this.listEstudiantes = estudianteFacade.findAll();
        this.parentescoFamilias = parseParentescoFamiliasToMap(parentescoFamiliaFacade.findAll());
    }

    public AdminEstudianteModificarBean() {
    }

    public void onRowEdit(RowEditEvent event) {
        Estudiante estudiante = (Estudiante) event.getObject();
        usuarioFacade.modificar(estudiante.getUsuario());
        estudianteFacade.modificar(estudiante);
        UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZACION EXITOSA");
    }

    public void onRowCancel(RowEditEvent event) {

    }

    private Map<String, ParentescoFamilia> parseParentescoFamiliasToMap(List<ParentescoFamilia> findAll) {
        Map<String, ParentescoFamilia> outcome = new LinkedHashMap<>();
        for (ParentescoFamilia s : findAll) {
            String key = s.getParentesco();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }
}
