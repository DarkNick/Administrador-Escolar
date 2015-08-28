/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.dao.SalonFacade;
import com.lusadi.entities.Salon;
import com.lusadi.utils.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
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
public class AdminSalonBean implements Serializable {

    @EJB
    private SalonFacade salonFacade;

    private Salon salon = new Salon();
    private ArrayList<Salon> salones = new ArrayList<Salon>();

    public AdminSalonBean() {
    }

    public void createSalon() {
        try {
            salonFacade.createSalon(salon);
            UtilFaces.getFacesUtil().redirect("/edu/administracion-registro.xhtml");
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void findAllSalon() {
        ArrayList<Salon> result = salonFacade.findAllSalon();
        if (result != null) {
            salones = result;
        }
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public ArrayList<Salon> getSalones() {
        return salones;
    }

    public void setSalones(ArrayList<Salon> salones) {
        this.salones = salones;
    }

}
