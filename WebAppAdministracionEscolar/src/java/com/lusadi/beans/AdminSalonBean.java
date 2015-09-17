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
import java.util.Collections;
import java.util.Comparator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

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
    private ArrayList<Salon> salones;

    public AdminSalonBean() {
    }

    @PostConstruct
    public void init() {
        salones = new ArrayList<Salon>(salonFacade.findAll());
    }

    public void createSalon() {
        salonFacade.createSalon(salon);
        salones = new ArrayList<Salon>(salonFacade.findAll());
        salon = new Salon();
    }

    public void findAllSalon() {
        try {
            ArrayList<Salon> result = new ArrayList<Salon>(salonFacade.findAll());
            if (result != null) {
                salones = result;
                Collections.sort(salones, new Comparator<Salon>() {
                    @Override
                    public int compare(Salon p1, Salon p2) {
                        return p1.getCapacidad().compareTo(p2.getCapacidad());
                    }
                });
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void onRowEdit(RowEditEvent event) {
        salonFacade.mergeSalon(((Salon) event.getObject()));
        FacesMessage msg = new FacesMessage("Salon Edited", ((Salon) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Salon) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteMateria(Salon item) {
        try {
            salonFacade.deleteSalon(item);
            salones.remove(item);
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
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
