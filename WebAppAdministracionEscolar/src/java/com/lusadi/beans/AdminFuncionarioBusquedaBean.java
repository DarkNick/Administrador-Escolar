/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.entities.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 * @author Dark_nick
 */
@Named(value = "adFuncionarioBusqueda")
@ViewScoped
public class AdminFuncionarioBusquedaBean implements Serializable {

    @EJB
    private FuncionarioFacade funcionarioFacade;
    private List<Funcionario> listBusquedaFuncionario = new ArrayList<>();
    private List<Funcionario> filteredListBusquedaFuncionario;

    public List<Funcionario> getFilteredListBusquedaFuncionario() {
        return filteredListBusquedaFuncionario;
    }

    public void setFilteredListBusquedaFuncionario(List<Funcionario> filteredListBusquedaFuncionario) {
        this.filteredListBusquedaFuncionario = filteredListBusquedaFuncionario;
    }

    public FuncionarioFacade getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public List<Funcionario> getListBusquedaFuncionario() {
        return listBusquedaFuncionario;
    }

    public void setListBusquedaFuncionario(List<Funcionario> listBusquedaFuncionario) {
        this.listBusquedaFuncionario = listBusquedaFuncionario;
    }

    @PostConstruct
    public void init() {
        this.listBusquedaFuncionario = funcionarioFacade.findAll();
    }
    public AdminFuncionarioBusquedaBean() {
    }
}
