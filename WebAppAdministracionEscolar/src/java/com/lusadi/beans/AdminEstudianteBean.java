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
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.Login;
import com.lusadi.entities.ParentescoFamilia;
import com.lusadi.entities.Usuario;
import com.lusadi.entities.UsuarioPK;
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
@ManagedBean(name = "adminEstudiante")
@RequestScoped
public class AdminEstudianteBean {

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
        parentescoFamilias = parseParentescoFamiliasToMap(parentescoFamiliaFacade.findAll());
        estudiante.setUsuario(new Usuario());
        estudiante.getUsuario().setUsuarioPK(new UsuarioPK());
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

    public AdminEstudianteBean() {
    }

    public void createStudent() {
        try {
            System.out.println("EMPEZO LA CUESTION");
            com.lusadi.entities.Rol rol = rolFacade.find(CommonInterface.ROL_ID_ESTUDIANTE);
            estudiante.getUsuario().setRolId(rol);
            estudiante.setParentescoFamiliaId(parentescoFamilia);
            login.setUsuario(estudiante.getUsuario());
            System.out.println("Correo: " + estudiante.getUsuario().getCorreoElectronico());
            System.out.println("Nacimiento: " + estudiante.getUsuario().getFechaNacimiento());
            System.out.println("Nonbres: " + estudiante.getUsuario().getNombres());
            System.out.println("1 apellido: " + estudiante.getUsuario().getPrimerApellido());
            System.out.println("2 apellido: " + estudiante.getUsuario().getSegundoApellido());
            System.out.println("tipo sangre " + estudiante.getUsuario().getTipoSangre());
            System.out.println("tipo ID " + estudiante.getUsuario().getUsuarioPK().getTipoId());
            System.out.println("numeri ID " + estudiante.getUsuario().getUsuarioPK().getNumeroId());
            System.out.println("ID rol" + estudiante.getUsuario().getRolId().getRolId());
            usuarioFacade.create(estudiante.getUsuario());
            estudianteFacade.create(estudiante);
            LoginFacade.create(login);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    private Map<String, ParentescoFamilia> parseParentescoFamiliasToMap(List<ParentescoFamilia> findAll) {
        Map<String, ParentescoFamilia> outcome = new LinkedHashMap<String, ParentescoFamilia>();
        for (ParentescoFamilia s : findAll) {
            String key = s.getParentesco();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }
}
