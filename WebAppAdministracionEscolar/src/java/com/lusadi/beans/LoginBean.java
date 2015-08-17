package com.lusadi.beans;

import com.lusadi.constant.SesionValuesEnum;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.modelo.Usuario;
import com.lusadi.utils.UtilFaces;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

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
            int numeroId = Integer.parseInt(usuario.getNumero_id());
            usuarioFacade.validateLogin(numeroId, usuario.getPassword());
            com.lusadi.entities.Usuario find = usuarioFacade.find(new UsuarioPK("CC", numeroId));
            HttpSession session = UtilFaces.getFacesUtil().getSession();
            session.setAttribute(SesionValuesEnum.NAME_USER.name(), find.getNombres());
            UtilFaces.getFacesUtil().redirect("/edu/administracion-usuarios.xhtml");
        } catch (IOException ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public String getNombreUsuario() {
        HttpSession session = UtilFaces.getFacesUtil().getSession();
        return (String) session.getAttribute(SesionValuesEnum.NAME_USER.name());
    }
}
