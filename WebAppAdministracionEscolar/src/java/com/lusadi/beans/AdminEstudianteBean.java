/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.EstudianteFacade;
import com.lusadi.entities.Estudiante;
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
@ManagedBean
@RequestScoped
public class AdminEstudianteBean {

    @EJB
    private EstudianteFacade estudianteFacade;

    private Usuario usuario;
    private UsuarioPK usuarioPk;

    public AdminEstudianteBean() {
    }

    public void createEstudiante() {
        try {
            estudianteFacade.createEstudiante(usuario);
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