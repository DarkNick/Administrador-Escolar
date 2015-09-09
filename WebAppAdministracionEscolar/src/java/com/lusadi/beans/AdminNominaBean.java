package com.lusadi.beans;

import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.entities.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "adminNominaBean")
@ViewScoped
public class AdminNominaBean implements Serializable {

    @EJB
    private FuncionarioFacade funcionarioFacade;

    private List<Funcionario> listFuncionarios;

    public AdminNominaBean() {
    }

    @PostConstruct
    public void init() {
        this.listFuncionarios = funcionarioFacade.findAll();
    }

    public List<Funcionario> getListFuncionarios() {
        return listFuncionarios;
    }

    public void setListFuncionarios(List<Funcionario> listFuncionarios) {
        this.listFuncionarios = listFuncionarios;
    }

}
