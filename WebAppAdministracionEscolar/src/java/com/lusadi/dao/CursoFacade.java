/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Curso;
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
public class CursoFacade extends AbstractFacade<Curso> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

    public void createCurso(Curso curso) {
        em.persist(curso);
    }

    public void deleteCurso(Curso curso) {
        try {
            em.remove(curso);
        } catch (Exception e) {
            try {
                throw new Exception(e + " Error al intentar eliminar el curso");
            } catch (Exception ex) {
                Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void mergeCurso(Curso curso) {
        try {
            em.merge(curso);
        } catch (Exception e) {
            try {
                throw new Exception(e + " Error al intentar modificar el curso");
            } catch (Exception ex) {
                Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
