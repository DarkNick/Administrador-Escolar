/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Login;
import com.lusadi.entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void validateLogin(int numero_id, String password) throws Exception {
        Query createNativeQuery = em.createNativeQuery("select login_id from prueba.usuario where tipo_id = 'cc' and numero_id = " + numero_id);
        List resultList = createNativeQuery.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            Login find = em.find(Login.class, Integer.parseInt(String.valueOf(resultList.get(0))));
            if (find != null) {
                if (find.getClave().compareTo(password) != 0) {
                    throw new Exception("La clave no coincide con el sistema");
                }
            } else {
                throw new Exception("El número de identificación no se encontró en la base de datos");
            }
        } else {
            throw new Exception("El número de identificación no se encontró en la base de datos");
        }
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

}
