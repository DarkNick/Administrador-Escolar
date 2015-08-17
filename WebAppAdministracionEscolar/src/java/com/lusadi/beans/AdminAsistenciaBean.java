/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.modelo.Asistencia;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "adminAsistenciaBean")
@ViewScoped
public class AdminAsistenciaBean implements Serializable {

    private boolean allRegisters;
    private boolean byNumeroIdentificacion;

    private List<Asistencia> listTemporalAsistencia;

    public AdminAsistenciaBean() {
    }

    public List<Asistencia> getListTemporalAsistencia() {
        return listTemporalAsistencia;
    }

    public void setListTemporalAsistencia(List<Asistencia> listTemporalAsistencia) {
        this.listTemporalAsistencia = listTemporalAsistencia;
    }

    public boolean isAllRegisters() {
        return allRegisters;
    }

    public void setAllRegisters(boolean allRegisters) {
        this.allRegisters = allRegisters;
    }

    public boolean isByNumeroIdentificacion() {
        return byNumeroIdentificacion;
    }

    public void setByNumeroIdentificacion(boolean byNumeroIdentificacion) {
        this.byNumeroIdentificacion = byNumeroIdentificacion;
    }

}
