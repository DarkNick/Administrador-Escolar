/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Curso;
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
public class CursoFacade extends AbstractFacade<Curso> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void createCurso(Curso curso) throws Exception {
        em.persist(curso);
    }

    public ArrayList<Curso> findAllCursos() {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PRUEBA.CURSO");
            Query q = em.createNativeQuery(sql.toString());
            return new ArrayList<Curso>(q.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CursoFacade() {
        super(Curso.class);
    }

}
