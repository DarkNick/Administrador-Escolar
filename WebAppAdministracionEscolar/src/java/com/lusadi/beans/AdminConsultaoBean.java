/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.HorarioFacade;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.Horario;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Personal
 */
@ManagedBean
@RequestScoped
public class AdminConsultaoBean {

    @EJB
    private HorarioFacade horarioFacade;
    @EJB
    private FuncionarioFacade funcionarioFacade;

    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Horario> horarios = new ArrayList<Horario>();
    private Funcionario funcionario = new Funcionario();
    private Horario horario = new Horario();

    public AdminConsultaoBean() {
    }

    @PostConstruct
    public void init() {
        funcionarios = new ArrayList<Funcionario>(funcionarioFacade.findAll());
    }

    public void cargarDatos() {
        ArrayList<Horario> horarioAll = new ArrayList<Horario>(horarioFacade.findAll());
        for (Horario var : horarioAll) {
            if (var.getFuncionarioId().getFuncionarioId().equals(funcionario.getFuncionarioId())) {
                horarios.add(var);
            }
        }
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        cargarDatos();
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

}
