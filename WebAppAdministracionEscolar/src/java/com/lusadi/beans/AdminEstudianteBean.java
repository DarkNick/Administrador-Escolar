/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.EstudianteFacade;
import com.lusadi.dao.LoginFacade;
import com.lusadi.dao.ParentescoFamiliaFacade;
import com.lusadi.dao.RolFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Estudiante;
import com.lusadi.entities.Login;
import com.lusadi.entities.Usuario;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
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
    
    private Usuario usuario = new Usuario();
    private UsuarioPK usuarioPk = new UsuarioPK();
    private Login login = new Login();
    private Estudiante estudiante = new Estudiante();
    int idParent;

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

    public UsuarioPK getUsuarioPk() {
        return usuarioPk;
    }

    public void setUsuarioPk(UsuarioPK usuarioPk) {
        this.usuarioPk = usuarioPk;
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

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public AdminEstudianteBean() {
    }

    public void createCourse() {
        try {
            System.out.println("EMPEZO LA CUESTION");
            com.lusadi.entities.Rol rol = rolFacade.find(3);
            estudiante.getUsuario().setRolId(rol);
            com.lusadi.entities.ParentescoFamilia parentesco = parentescoFamiliaFacade.find(idParent);
            estudiante.setParentescoFamiliaId(parentesco);
            usuario.setUsuarioPK(usuarioPk);
            estudiante.setUsuario(usuario);
            LoginFacade.createCourse(login);            
            estudianteFacade.createEstudiante(estudiante);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}