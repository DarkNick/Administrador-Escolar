package com.lusadi.beans;

import com.lusadi.constant.CommonInterface;
import com.lusadi.dao.CargoFacade;
import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.LoginFacade;
import com.lusadi.dao.RolFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Cargo;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Login;
import com.lusadi.entities.Rol;
import com.lusadi.entities.Salon;
import com.lusadi.entities.Usuario;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dark-nick
 */
@ManagedBean(name = "adminDocenteBean")
@ViewScoped
public class AdminDocenteBean implements Serializable {

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

    private Login login = new Login();
    private Usuario usuario = new Usuario();
    private Cargo cargo = new Cargo();
    private UsuarioPK usuarioPk = new UsuarioPK();
    private Funcionario funcionario = new Funcionario();

    private Map<String, Cargo> cargos;

    public AdminDocenteBean() {
    }

    @PostConstruct
    public void init() {
        cargos = parseSalonesToMap(cargoFacade.findAll());
    }

    public CargoFacade getCargoFacade() {
        return cargoFacade;
    }

    public void setCargoFacade(CargoFacade cargoFacade) {
        this.cargoFacade = cargoFacade;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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

    public Map<String, Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(Map<String, Cargo> cargos) {
        this.cargos = cargos;
    }

    public void createCourse() {
        try {
            System.out.println("OK");
            Rol rol = rolFacade.find(CommonInterface.ROL_ID_DOCENTE);
            usuario.setUsuarioPK(usuarioPk);
            usuario.setRolId(rol);
            usuarioFacade.create(usuario);
            funcionario.setUsuario(usuario);
            funcionario.setCargoId(cargoFacade.find(cargo.getCargoId()));
            funcionarioFacade.create(funcionario);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-usuarios.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    private Map<String, Cargo> parseSalonesToMap(List<Cargo> findAll) {
        Map<String, Cargo> outcome = new LinkedHashMap<String, Cargo>();
        for (Cargo s : findAll) {
            String key = s.getNombreCargo();
            outcome.put(key.toUpperCase(), s);
        }
        return outcome;
    }
}
