/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Salon;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        salon.setSalonId(2);
        em.persist(salon);
    }

    public ArrayList<Salon> findAllSalon() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM PRUEBA.SALON");
        Query q = em.createNativeQuery(sql.toString(), Salon.class);
        return new ArrayList<Salon>(q.getResultList());
    }

    public SalonFacade() {
        super(Salon.class);
    }

}
