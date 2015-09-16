/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Login;
import com.lusadi.entities.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public void validateLogin(int numero_id, String password) throws Exception {
        em.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT p.loginId FROM ").append(Login.class.getSimpleName()).append(" p ");
        sql.append(" WHERE p.usuario.usuarioPK.tipoId = :tipoId AND p.usuario.usuarioPK.numeroId = :numeroId ");
        Query createNativeQuery = em.createQuery(sql.toString()).setParameter("tipoId", "CC").setParameter("numeroId", numero_id);
        List resultList = createNativeQuery.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            Login find = em.find(Login.class, Integer.parseInt(String.valueOf(resultList.get(0))));
            if (find != null) {
                if (find.getClave().compareTo(password) != 0) {
                    throw new Exception("La clave no coincide con la registrada en el sistema.");
                }
            } else {
                throw new Exception("El número de identificación no se encontró en la base de datos");
            }
        } else {
            throw new Exception("El número de identificación no se encontró en la base de datos");
        }
    }

    public void modificar(Usuario usuario) {
        try {
            em.merge(usuario);
            System.out.println("*******------------------******************");
        } catch (Exception e) {
            try {
                throw new Exception(e+" Error al intentar modificar al usuario");
            } catch (Exception ex) {
                Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
