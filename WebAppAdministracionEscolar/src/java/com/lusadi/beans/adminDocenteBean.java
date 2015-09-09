package com.lusadi.beans;

import com.lusadi.dao.CargoFacade;
import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.LoginFacade;
import com.lusadi.dao.RolFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Login;
import com.lusadi.entities.Usuario;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dark-nick
 */
@ManagedBean(name = "adminDocente")
@ViewScoped
public class adminDocenteBean {

    @EJB
    private FuncionarioFacade funcionarioFacade;
    @EJB
    private LoginFacade LoginFacade;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private CargoFacade cargoFacade;

    public FuncionarioFacade getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    private Funcionario funcionario = new Funcionario();
    private Login login = new Login();
    private Usuario usuario = new Usuario();
    private UsuarioPK usuarioPk = new UsuarioPK();
    int idCargo;

    public CargoFacade getCargoFacade() {
        return cargoFacade;
    }

    public void setCargoFacade(CargoFacade cargoFacade) {
        this.cargoFacade = cargoFacade;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public UsuarioPK getUsuarioPk() {
        return usuarioPk;
    }

    public void setUsuarioPk(UsuarioPK usuarioPk) {
        this.usuarioPk = usuarioPk;
    }

    public LoginFacade getLoginFacade() {
        return LoginFacade;
    }

    public void setLoginFacade(LoginFacade LoginFacade) {
        this.LoginFacade = LoginFacade;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public adminDocenteBean() {
    }
    
    public void createCourse() {
        try {
            com.lusadi.entities.Rol rol = rolFacade.find(2);
            funcionario.getUsuario().setRolId(rol);
            com.lusadi.entities.Cargo cargo = cargoFacade.find(idCargo);
            funcionario.setCargoId(cargo);
            usuario.setUsuarioPK(usuarioPk);
            funcionario.setUsuario(usuario);
            funcionarioFacade.createCourse(funcionario);
            LoginFacade.createCourse(login);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-usuarios.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }
}
