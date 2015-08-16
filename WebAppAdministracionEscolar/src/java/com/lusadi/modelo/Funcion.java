package com.lusadi.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Funcion implements Serializable, Comparable<Funcion> {

    private String nombreFuncion;
    private String urlFuncion;
    private String tipo;

    public Funcion(String nombreFuncion, String urlFuncion, String tipo) {
        this.nombreFuncion = nombreFuncion;
        this.urlFuncion = urlFuncion;
        this.tipo = (tipo == null) ? "FOLDER" : "DOC";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public String getUrlFuncion() {
        return urlFuncion;
    }

    public void setUrlFuncion(String urlFuncion) {
        this.urlFuncion = urlFuncion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Funcion other = (Funcion) obj;
        if (nombreFuncion == null) {
            if (other.nombreFuncion != null) {
                return false;
            }
        } else if (!nombreFuncion.equals(other.nombreFuncion)) {
            return false;
        }
        if (tipo == null) {
            if (other.tipo != null) {
                return false;
            }
        } else if (!tipo.equals(other.tipo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nombreFuncion);
        hash = 29 * hash + Objects.hashCode(this.urlFuncion);
        hash = 29 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public String toString() {
        return "[" + nombreFuncion + " " + urlFuncion + "]";
    }

    @Override
    public int compareTo(Funcion funcion) {
        return this.getNombreFuncion().compareTo(funcion.getNombreFuncion());
    }
}
