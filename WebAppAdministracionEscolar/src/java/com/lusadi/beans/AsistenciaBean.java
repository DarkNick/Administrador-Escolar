/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "asistenciaBean")
@ViewScoped
public class AsistenciaBean implements Serializable {

    private boolean allRegisters;
    private boolean byNumeroIdentificacion;

    public AsistenciaBean() {
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
