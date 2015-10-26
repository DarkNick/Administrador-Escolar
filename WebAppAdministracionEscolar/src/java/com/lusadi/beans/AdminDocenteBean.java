package com.lusadi.beans;

import com.lusadi.constant.CommonInterface;
import com.lusadi.dao.CargoFacade;
import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.LoginFacade;
import com.lusadi.dao.RolFacade;
import com.lusadi.dao.TipoSangreFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Cargo;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Login;
import com.lusadi.entities.Rol;
import com.lusadi.entities.TipoSangre;
import com.lusadi.entities.Usuario;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

/**
 * @author dark-nick
 */
@ManagedBean(name = "adminDocente")
@ViewScoped
public class AdminDocenteBean implements Serializable {

    @EJB
    private TipoSangreFacade tipoSangreFacade;
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

    private Login login = new Login();
    private Usuario usuario = new Usuario();
    private Cargo cargo = new Cargo();
    private TipoSangre tipoSangre = new TipoSangre();
    private UsuarioPK usuarioPk = new UsuarioPK();
    private Funcionario funcionario = new Funcionario();

    private List<SelectItem> cargosItems;
    private List<SelectItem> tiposSangreItems;

    @PostConstruct
    public void init() {
        cargosItems = parseCargosToMap(cargoFacade.findAll());
        tiposSangreItems = parseTipoSangreToMap(tipoSangreFacade.findAll());
    }

    public AdminDocenteBean() {
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
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

    public List<SelectItem> getCargosItems() {
        return cargosItems;
    }

    public void setCargosItems(List<SelectItem> cargosItems) {
        this.cargosItems = cargosItems;
    }

    public List<SelectItem> getTiposSangreItems() {
        return tiposSangreItems;
    }

    public void setTiposSangreItems(List<SelectItem> tiposSangreItems) {
        this.tiposSangreItems = tiposSangreItems;
    }

    public void createDocente() {
        try {
            Rol rol = rolFacade.find(CommonInterface.ROL_ID_DOCENTE);
            usuario.setUsuarioPK(usuarioPk);
            usuario.setRolId(rol);
            usuarioFacade.create(usuario);
            funcionario.setUsuario(usuario);
            funcionario.setCargoId(cargoFacade.find(cargo.getCargoId()));
            login.setUsuario(usuario);
            LoginFacade.create(login);
            funcionarioFacade.create(funcionario);
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "REGISTRO EXITOSO");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    private List<SelectItem> parseCargosToMap(List<Cargo> findAll) {
        cargosItems = new ArrayList<SelectItem>();
        for (Cargo s : findAll) {
            cargosItems.add(new SelectItem(s, s.getNombreCargo()));
        }
        return cargosItems;
    }

    private List<SelectItem> parseTipoSangreToMap(List<TipoSangre> findAll) {
        tiposSangreItems = new ArrayList<SelectItem>();
        for (TipoSangre s : findAll) {
            tiposSangreItems.add(new SelectItem(s.getTipoSangre(), s.getTipoSangre()));
        }
        return tiposSangreItems;
    }
}
