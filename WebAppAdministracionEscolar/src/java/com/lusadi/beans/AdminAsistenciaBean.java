/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.AsistenciaFacade;
import com.lusadi.entities.Asistencia;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "adminAsistenciaBean")
@ViewScoped
public class AdminAsistenciaBean implements Serializable {

    @EJB
    private AsistenciaFacade asistenciaFacade;

    private boolean filtroAllRegisters = true;
    private boolean filtroFecha = true;
    private String campoBusquedaAsistencia;
    private Date fechaFiltroBusqueda;
    private UsuarioPK usuarioPK = new UsuarioPK();

    private List<Asistencia> listTemporalAsistencia;

    private List<Asistencia> listBusquedaAsistencia = new ArrayList<Asistencia>();
    private List<Asistencia> filteredListBusquedaAsistencia;

    public AdminAsistenciaBean() {
    }

    public void actionBusquedaAsistencia() {
        if (filtroFecha && fechaFiltroBusqueda == null) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "INGRESE UNA FECHA.");
        } else {
            if (!filtroAllRegisters && campoBusquedaAsistencia == null || campoBusquedaAsistencia.isEmpty()) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "INGRESE UN NUMERO DOCUMENTO VALIDO.");
            } else {
                this.listBusquedaAsistencia = asistenciaFacade.findAsistenciaByUsuario(((filtroAllRegisters) ? -1 : usuarioPK.getNumeroId()), ((filtroFecha) ? fechaFiltroBusqueda : null));
                RequestContext.getCurrentInstance().update("asistencia-table");
            }
        }
    }

    public boolean isFiltroAllRegisters() {
        return filtroAllRegisters;
    }

    public void setFiltroAllRegisters(boolean filtroAllRegisters) {
        this.filtroAllRegisters = filtroAllRegisters;
    }

    public boolean isFiltroFecha() {
        return filtroFecha;
    }

    public void setFiltroFecha(boolean filtroFecha) {
        this.filtroFecha = filtroFecha;
    }

    public String getCampoBusquedaAsistencia() {
        return campoBusquedaAsistencia;
    }

    public void setCampoBusquedaAsistencia(String campoBusquedaAsistencia) {
        this.campoBusquedaAsistencia = campoBusquedaAsistencia;
    }

    public Date getFechaFiltroBusqueda() {
        return fechaFiltroBusqueda;
    }

    public void setFechaFiltroBusqueda(Date fechaFiltroBusqueda) {
        this.fechaFiltroBusqueda = fechaFiltroBusqueda;
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public List<Asistencia> getListTemporalAsistencia() {
        return listTemporalAsistencia;
    }

    public void setListTemporalAsistencia(List<Asistencia> listTemporalAsistencia) {
        this.listTemporalAsistencia = listTemporalAsistencia;
    }

    public List<Asistencia> getListBusquedaAsistencia() {
        return listBusquedaAsistencia;
    }

    public void setListBusquedaAsistencia(List<Asistencia> listBusquedaAsistencia) {
        this.listBusquedaAsistencia = listBusquedaAsistencia;
    }

    public List<Asistencia> getFilteredListBusquedaAsistencia() {
        return filteredListBusquedaAsistencia;
    }

    public void setFilteredListBusquedaAsistencia(List<Asistencia> filteredListBusquedaAsistencia) {
        this.filteredListBusquedaAsistencia = filteredListBusquedaAsistencia;
    }

}
