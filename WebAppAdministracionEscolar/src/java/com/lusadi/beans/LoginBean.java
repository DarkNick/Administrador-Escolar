package com.lusadi.beans;

import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.modelo.Usuario;
import com.lusadi.utils.UtilFaces;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.print.attribute.standard.Severity;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LoginBean() {
        usuario = new Usuario();
    }

    public void loginControl() {

        try {
            usuarioFacade.validateLogin(Integer.parseInt(usuario.getNumero_id()), usuario.getPassword());
            UtilFaces.getFacesUtil().redirect("/edu/administracion-usuarios.xhtml");
        } catch (IOException ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }
}
