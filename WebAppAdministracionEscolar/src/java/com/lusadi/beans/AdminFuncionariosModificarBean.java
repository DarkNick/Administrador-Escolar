/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Dark_Nick
 */
@ManagedBean(name = "adminFuncionariosModificar")
@ViewScoped
public class AdminFuncionariosModificarBean implements Serializable {

    @EJB
    private FuncionarioFacade funcionarioFacade;
    private String campoFuncionario = "";
    private List<Funcionario> listFuncionario = new ArrayList<Funcionario>();
    private List<Funcionario> filteredListBusquedaFuncionario;

    public FuncionarioFacade getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public String getCampoFuncionario() {
        return campoFuncionario;
    }

    public void setCampoFuncionario(String campoFuncionario) {
        this.campoFuncionario = campoFuncionario;
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
    }

    public AdminFuncionariosModificarBean() {
    }

    public void onRowEdit(RowEditEvent event) {
        Funcionario funcionario = (Funcionario) event.getObject();
        funcionarioFacade.edit(funcionario);
        UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "ACTULIZACION EXITOSA");
    }

    public void onRowCancel(RowEditEvent event) {
    }

}
