package com.lusadi.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Asistencia implements Serializable {

    private String numeroId;
    private Date fecha;

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
