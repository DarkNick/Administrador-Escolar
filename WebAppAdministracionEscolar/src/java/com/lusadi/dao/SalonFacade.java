/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Salon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Personal
 */
@Stateless
public class SalonFacade extends AbstractFacade<Salon> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void createSalon(Salon salon) {
        em.persist(salon);
    }

    public SalonFacade() {
        super(Salon.class);
    }

    public void deleteSalon(Salon salon) {
        try {
            em.remove(salon);
        } catch (Exception e) {
            try {
                throw new Exception(e + " Error al intentar eliminar el salon");
            } catch (Exception ex) {
                Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void mergeSalon(Salon salon) {
        try {
            em.merge(salon);
        } catch (Exception e) {
            try {
                throw new Exception(e + " Error al intentar modificar el salon");
            } catch (Exception ex) {
                Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
