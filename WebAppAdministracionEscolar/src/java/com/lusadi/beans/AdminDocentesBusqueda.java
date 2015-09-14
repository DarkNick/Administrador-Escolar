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
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dark_nick
 */
@Named(value = "adminDocentesBusqueda")
@ViewScoped
public class AdminDocentesBusqueda implements Serializable {

    @EJB
    private FuncionarioFacade funcionarioFacade;
    private boolean filtroAllRegisters = true;
    private String campoBusquedaAsistencia = "";
    private UsuarioPK usuarioPK = new UsuarioPK();

    private List<Funcionario> listTemporalAsistencia;
    private List<Funcionario> listBusquedaAsistencia = new ArrayList<Funcionario>();
    private List<Funcionario> filteredListBusquedaAsistencia;

    @PostConstruct
    public void init() {
        this.listBusquedaAsistencia = funcionarioFacade.findAll();
    }

    public FuncionarioFacade getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public boolean isFiltroAllRegisters() {
        return filtroAllRegisters;
    }

    public void setFiltroAllRegisters(boolean filtroAllRegisters) {
        this.filtroAllRegisters = filtroAllRegisters;
    }

    public String getCampoBusquedaAsistencia() {
        return campoBusquedaAsistencia;
    }

    public void setCampoBusquedaAsistencia(String campoBusquedaAsistencia) {
        this.campoBusquedaAsistencia = campoBusquedaAsistencia;
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public List<Funcionario> getListTemporalAsistencia() {
        return listTemporalAsistencia;
    }

    public void setListTemporalAsistencia(List<Funcionario> listTemporalAsistencia) {
        this.listTemporalAsistencia = listTemporalAsistencia;
    }

    public List<Funcionario> getListBusquedaAsistencia() {
        return listBusquedaAsistencia;
    }

    public void setListBusquedaAsistencia(List<Funcionario> listBusquedaAsistencia) {
        this.listBusquedaAsistencia = listBusquedaAsistencia;
    }

    public List<Funcionario> getFilteredListBusquedaAsistencia() {
        return filteredListBusquedaAsistencia;
    }

    public void setFilteredListBusquedaAsistencia(List<Funcionario> filteredListBusquedaAsistencia) {
        this.filteredListBusquedaAsistencia = filteredListBusquedaAsistencia;
    }

    public AdminDocentesBusqueda() {
    }

    public void actionBusquedaDocente() {
        System.out.println("MMM HOLA");
        if (!filtroAllRegisters && campoBusquedaAsistencia.isEmpty()) {
            System.out.println("MMM TRUE");
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "INGRESE UN NUMERO DOCUMENTO VALIDO.");
        } else {
            System.out.println("MMM FALSE");
            this.listBusquedaAsistencia = funcionarioFacade.findDocenteByUsuario(((filtroAllRegisters) ? -1 : Long.parseLong(campoBusquedaAsistencia)));
            RequestContext.getCurrentInstance().update("docente-table");
        }
    }
}
