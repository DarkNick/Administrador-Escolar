/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Funcionario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Personal
 */
@Stateless
public class FuncionarioFacade extends AbstractFacade<Funcionario> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionarioFacade() {
        super(Funcionario.class);
    }

    public void createCourse(Funcionario funcionario) throws Exception {
        try {
            em.persist(funcionario);
        } catch (Exception e) {
            throw new Exception("Error al intentar crear al funcionario");
        }
    }

    public Funcionario findByTypeIdAndNumberId(String tipoId, long numeroId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM colegio_lusadi.FUNCIONARIO WHERE NUMERO_ID = ? AND TIPO_ID = ?");
        Query query = em.createNativeQuery(sb.toString(), Funcionario.class).setParameter(1, numeroId).setParameter(2, tipoId);
        List resultList = query.getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return null;
        }
        return (Funcionario) resultList.get(0);
    }

    public ArrayList<String> findAllNumebersIdStartWith(String param) {
        if (param.length() >= 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT NUMERO_ID FROM colegio_lusadi.FUNCIONARIO WHERE NUMERO_ID LIKE ?");
            Query query = em.createNativeQuery(sb.toString()).setParameter(1, param + "%");
            return new ArrayList<String>(query.getResultList());
        }
        return new ArrayList<String>();
    }

}
