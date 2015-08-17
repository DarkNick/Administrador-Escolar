package com.lusadi.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Funcionario implements Serializable {

    private String tipoId;
    private long numeroId;
    private Long saldoNeto;
    private String nombreCargo;
    private String salarioTopeMin;
    private String salarioTopeMax;
    private String primerApellido;
    private String segundoApellido;
    private String nombres;
    private Date fechaNacimiento;
    private String tipoSangre;

    public Funcionario(String tipoId, long numeroId, Long saldoNeto, String nombreCargo, String salarioTopeMin, String salarioTopeMax, String primerApellido, String segundoApellido, String nombres, Date fechaNacimiento, String tipoSangre) {
        this.tipoId = tipoId;
        this.numeroId = numeroId;
        this.saldoNeto = saldoNeto;
        this.nombreCargo = nombreCargo;
        this.salarioTopeMin = salarioTopeMin;
        this.salarioTopeMax = salarioTopeMax;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public long getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(long numeroId) {
        this.numeroId = numeroId;
    }

    public Long getSaldoNeto() {
        return saldoNeto;
    }

    public void setSaldoNeto(Long saldoNeto) {
        this.saldoNeto = saldoNeto;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getSalarioTopeMin() {
        return salarioTopeMin;
    }

    public void setSalarioTopeMin(String salarioTopeMin) {
        this.salarioTopeMin = salarioTopeMin;
    }

    public String getSalarioTopeMax() {
        return salarioTopeMax;
    }

    public void setSalarioTopeMax(String salarioTopeMax) {
        this.salarioTopeMax = salarioTopeMax;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

}
