/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.MateriaFacade;
import com.lusadi.dao.SalonFacade;
import com.lusadi.entities.Materia;
import com.lusadi.entities.Salon;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Personal
 */
@ManagedBean
@RequestScoped
public class AdminMateriaBean implements Serializable {

    @EJB
    private SalonFacade salonFacade;
    @EJB
    private MateriaFacade materiaFacade;

    private Materia materia = new Materia();
    private HashMap<Integer, Integer> salones = new HashMap<Integer, Integer>();
    private ArrayList<Materia> materias = new ArrayList<Materia>();

    public AdminMateriaBean() {
        //findAllSalones();
    }

    public void createCourse() {
        try {
            materiaFacade.createCourse(materia);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void findAllSalones() {
        ArrayList<Salon> salonesAdd = new ArrayList<Salon>();
        salonesAdd = salonFacade.findAllSalon();
        if (salonesAdd != null) {
            for (Salon varSalon : salonesAdd) {
                salones.put(varSalon.getSalonId(), varSalon.getSalonId());
            }
        }
    }

    public void findAllMaterias() {
        ArrayList<Materia> result = materiaFacade.findAllMateria();
        if (result != null) {
            materias = result;
        }
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public HashMap<Integer, Integer> getSalones() {
        return salones;
    }

    public void setSalones(HashMap<Integer, Integer> salones) {
        this.salones = salones;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

}
