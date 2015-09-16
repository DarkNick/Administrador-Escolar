/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.UsuarioFacade;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Dark_Nick
 */
@ManagedBean(name="adminFuncionariosModificar")
@ViewScoped
public class AdminFuncionariosModificarBean implements Serializable{
    
    @EJB
    private FuncionarioFacade funcionarioFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    private List<Funcionario> listFuncionario = new ArrayList<>();
    private List<Funcionario> filteredListBusquedaFuncionario;

    public FuncionarioFacade getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public List<Funcionario> getListFuncionario() {
        return listFuncionario;
    }

    public void setListFuncionario(List<Funcionario> listFuncionario) {
        this.listFuncionario = listFuncionario;
    }

    public List<Funcionario> getFilteredListBusquedaFuncionario() {
        return filteredListBusquedaFuncionario;
    }

    public void setFilteredListBusquedaFuncionario(List<Funcionario> filteredListBusquedaFuncionario) {
        this.filteredListBusquedaFuncionario = filteredListBusquedaFuncionario;
    }

    @PostConstruct
    public void init() {
        this.listFuncionario = funcionarioFacade.findAll();
        System.err.println("*************************");
    }
    public AdminFuncionariosModificarBean() {
    }
    public void onRowEdit(RowEditEvent event) {
        usuarioFacade.modificar((Usuario)(((Funcionario) event.getObject()).getUsuario()));
        funcionarioFacade.modificar(((Funcionario) event.getObject()));
        System.err.println("*****EDITANDO************");
        FacesMessage msg = new FacesMessage("Car Edited", ((Funcionario) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Funcionario) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }   
}
