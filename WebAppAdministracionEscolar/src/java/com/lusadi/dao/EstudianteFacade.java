/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Estudiante;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author darkNick
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    public List<Estudiante> findAlumnoByUsuario(long numeroId) {
        System.out.println(numeroId);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM colegio_lusadi.ESTUDIANTE");
        StringBuilder sbConditionals = new StringBuilder();
        System.out.println((numeroId != -1));
        if (numeroId != -1) 
            sbConditionals.append("colegio_lusadi.ESTUDIANTE.USUARIO_NUMERO_ID = ").append(numeroId);
        Query query = em.createNativeQuery(sb.toString() + ((sbConditionals.length() != 0) ? " WHERE " + sbConditionals.toString() : ""), Estudiante.class);
        return new ArrayList<Estudiante>(query.getResultList());
    }
}
