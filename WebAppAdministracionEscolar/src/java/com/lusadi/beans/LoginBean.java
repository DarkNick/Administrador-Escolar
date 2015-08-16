package com.lusadi.beans;

import com.lusadi.modelo.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

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

    public String loginControl() {
        String redireccion = "login?faces-redirect=true";
        /*    querys = new Querys();
         try {
         querys.iniciarConexion();
         List<Entitys.Login> f = querys.loginControl(usuario.getNumero_id());
         querys.cerrarConexion();
         System.out.println(f.isEmpty());
         if (!f.isEmpty() && f.get(0).getClave().equals(usuario.getPassword())) {
         usuario.setNombre(f.get(0).getUsuario().getNombres());
         usuario.setFecha_Nacimiento(f.get(0).getUsuario().getFechaNacimiento());
         usuario.setNombre_rol(f.get(0).getUsuario().getRolRolId().getNombreRol());
         usuario.setNumero_id(f.get(0).getUsuario().getRolRolId().getRolId());
         usuario.setPrimer_Apellido(f.get(0).getUsuario().getPrimerApellido());
         usuario.setSegundo_Apellido(f.get(0).getUsuario().getSegundoApellido());
         usuario.setTipo_Sangre(f.get(0).getUsuario().getTipoSangre());
         usuario.setTipo_id(f.get(0).getUsuario().getUsuarioPK().getTipoId());
         redireccion = "login?faces-redirect=true";
         } else {
         // RequestContext.getCurrentInstance().update("growl");
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Usuario o Password Incorrecto"));
         }

         } catch (Exception e) {
         //  querys.cerrarConexion();
         //  RequestContext.getCurrentInstance().update("growl");
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Un error inesperado en la base OMG!!!" + e));
         }*/
        return redireccion;
    }
}
