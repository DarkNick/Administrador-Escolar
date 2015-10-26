/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.constant.CommonInterface;
import com.lusadi.dao.EstudianteFacade;
import com.lusadi.dao.LoginFacade;
import com.lusadi.dao.ParentescoFamiliaFacade;
import com.lusadi.dao.RolFacade;
import com.lusadi.dao.TipoSangreFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.Login;
import com.lusadi.entities.ParentescoFamilia;
import com.lusadi.entities.TipoSangre;
import com.lusadi.entities.Usuario;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Personal
 */
@ManagedBean(name = "adminEstudiante")
@RequestScoped
public class AdminEstudianteBean {

    @EJB
    private TipoSangreFacade tipoSangreFacade;

    @EJB
    private EstudianteFacade estudianteFacade;
    @EJB
    private LoginFacade LoginFacade;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ParentescoFamiliaFacade parentescoFamiliaFacade;

    private ParentescoFamilia parentescoFamilia = new ParentescoFamilia();
    private Login login = new Login();
    private Estudiante estudiante = new Estudiante();

    private List<Estudiante> listBusquedaAlumno;
    private List<Estudiante> filteredListBusquedaAlumno;

    private List<SelectItem> tiposSangreItems;
    private List<SelectItem> parentescoItems;

    public List<SelectItem> getTiposSangreItems() {
        return tiposSangreItems;
    }

    public void setTiposSangreItems(List<SelectItem> tiposSangreItems) {
        this.tiposSangreItems = tiposSangreItems;
    }

    public ParentescoFamilia getParentescoFamilia() {
        return parentescoFamilia;
    }

    public void setParentescoFamilia(ParentescoFamilia parentescoFamilia) {
        this.parentescoFamilia = parentescoFamilia;
    }
    private Map<String, ParentescoFamilia> parentescoFamilias;

    public Map<String, ParentescoFamilia> getParentescoFamilias() {
        return parentescoFamilias;
    }

    public void setParentescoFamilias(Map<String, ParentescoFamilia> parentescoFamilias) {
        this.parentescoFamilias = parentescoFamilias;
    }

    @PostConstruct
    public void init() {
        parentescoItems = parseParentescoFamiliasToMap(parentescoFamiliaFacade.findAll());
        estudiante.setUsuario(new Usuario());
        estudiante.getUsuario().setUsuarioPK(new UsuarioPK());
        listBusquedaAlumno = estudianteFacade.findAll();
        tiposSangreItems = parseTipoSangreToMap(tipoSangreFacade.findAll());
        
    }

    public EstudianteFacade getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacade estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public LoginFacade getLoginFacade() {
        return LoginFacade;
    }

    public void setLoginFacade(LoginFacade LoginFacade) {
        this.LoginFacade = LoginFacade;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public ParentescoFamiliaFacade getParentescoFamiliaFacade() {
        return parentescoFamiliaFacade;
    }

    public void setParentescoFamiliaFacade(ParentescoFamiliaFacade parentescoFamiliaFacade) {
        this.parentescoFamiliaFacade = parentescoFamiliaFacade;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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

    public List<SelectItem> getParentescoItems() {
        return parentescoItems;
    }

    public void setParentescoItems(List<SelectItem> parentescoItems) {
        this.parentescoItems = parentescoItems;
    }

    
    public AdminEstudianteBean() {
    }

    public void createStudent() {
        try {

            com.lusadi.entities.Rol rol = rolFacade.find(CommonInterface.ROL_ID_ESTUDIANTE);
            estudiante.getUsuario().setRolId(rol);
            estudiante.setParentescoFamiliaId(parentescoFamilia);
            login.setUsuario(estudiante.getUsuario());
            usuarioFacade.create(estudiante.getUsuario());
            estudianteFacade.create(estudiante);
            LoginFacade.create(login);
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "REGISTRO EXITOSO");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    private List<SelectItem> parseParentescoFamiliasToMap(List<ParentescoFamilia> findAll) {
          tiposSangreItems = new ArrayList<SelectItem>();
        for (ParentescoFamilia s : findAll) {
            tiposSangreItems.add(new SelectItem(s, s.getParentesco()));
        }
        return tiposSangreItems;
    }

    private List<SelectItem> parseTipoSangreToMap(List<TipoSangre> findAll) {
        tiposSangreItems = new ArrayList<SelectItem>();
        for (TipoSangre s : findAll) {
            tiposSangreItems.add(new SelectItem(s.getTipoSangre(), s.getTipoSangre()));
        }
        return tiposSangreItems;
    }

}
