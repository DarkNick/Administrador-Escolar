/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Nota;
import com.lusadi.entities.ResultadoAcademico;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Personal
 */
@Stateless
public class ResultadoAcademicoFacade extends AbstractFacade<ResultadoAcademico> {
    
    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ResultadoAcademicoFacade() {
        super(ResultadoAcademico.class);
    }
    
    public void createResultadoAcademico(ResultadoAcademico resultadoAcademico, ArrayList<Nota> notas) {
        em.persist(resultadoAcademico);
        for (Nota nota : notas) {
            nota.setResultadoAcademicoId(resultadoAcademico);
            em.persist(nota);
        }
    }
}
