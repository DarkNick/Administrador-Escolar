package com.lusadi.beans;

import com.lusadi.modelo.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "adminNominaBean")
@ViewScoped
public class AdminNominaBean implements Serializable{

    private List<Funcionario> listFuncionarios;

    public AdminNominaBean() {
    }

    public List<Funcionario> getListFuncionarios() {
        return listFuncionarios;
    }

    public void setListFuncionarios(List<Funcionario> listFuncionarios) {
        this.listFuncionarios = listFuncionarios;
    }

}
