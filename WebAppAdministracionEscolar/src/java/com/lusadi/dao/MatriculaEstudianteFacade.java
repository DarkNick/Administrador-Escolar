/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.MatriculaEstudiante;
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
public class MatriculaEstudianteFacade extends AbstractFacade<MatriculaEstudiante> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatriculaEstudianteFacade() {
        super(MatriculaEstudiante.class);
    }

    public void createMatricula(MatriculaEstudiante matricula) throws Exception {
        em.persist(matricula);
    }

    public ArrayList<MatriculaEstudiante> findAllMAtriculas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM PRUEBA.MATRICULAESTUDIANTE");
        Query q = em.createNativeQuery(sql.toString(), MatriculaEstudiante.class);
        return new ArrayList<MatriculaEstudiante>(q.getResultList());
    }
}
