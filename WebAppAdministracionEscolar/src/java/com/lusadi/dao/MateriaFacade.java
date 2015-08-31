/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Materia;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Personal
 */
@Stateless
public class MateriaFacade extends AbstractFacade<Materia> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaFacade() {
        super(Materia.class);
    }

    public void createCourse(Materia materia) {
        em.persist(materia);
    }

    public ArrayList<Materia> findAllMateria() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM PRUEBA.MATERIA");
        Query q = em.createNativeQuery(sql.toString(), Materia.class);
        return new ArrayList<Materia>(q.getResultList());
    }
}
