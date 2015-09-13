package com.lusadi.beans;

import com.lusadi.constant.SessionValuesEnum;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.modelo.Usuario;
import com.lusadi.utils.UtilFaces;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean")
@RequestScoped
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
            String fullName = find.getPrimerApellido() + " " + find.getSegundoApellido() + " " + find.getNombres();
            UtilFaces.getFacesUtil().getSession().setAttribute(SessionValuesEnum.FULLNAME_USER.name(), fullName);
            UtilFaces.getFacesUtil().getSession().setAttribute(SessionValuesEnum.ROL_USER.name(), find.getRolId().getNombreRol());
            UtilFaces.getFacesUtil().redirect("/edu/administracion-usuarios.xhtml");
        } catch (IOException ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void loginOutControl() {
        try {
            HttpSession session = UtilFaces.getFacesUtil().getSession();
            session.invalidate();
            UtilFaces.getFacesUtil().redirect("/edu/");
        } catch (IOException ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public String getNombreUsuario() {
        HttpSession session = UtilFaces.getFacesUtil().getSession();
        return (String) session.getAttribute(SessionValuesEnum.FULLNAME_USER.name());
    }

    public String getRolUsuario() {
        HttpSession session = UtilFaces.getFacesUtil().getSession();
        return (String) session.getAttribute(SessionValuesEnum.ROL_USER.name());
    }

}
