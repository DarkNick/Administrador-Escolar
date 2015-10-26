/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.Asistencia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class AsistenciaFacade extends AbstractFacade<Asistencia> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }

    public ArrayList<String> findAllNumebersIdStartWith(String param) {
        if (param.length() >= 2) {
            ArrayList<String> outcome = new ArrayList<String>();
            String sql = "SELECT NUMERO_ID FROM lusadi.USUARIO WHERE NUMERO_ID LIKE ?";
            Query query = em.createNativeQuery(sql).setParameter(1, param + "%");
            outcome.addAll(query.getResultList());
            return outcome;
        }
        return new ArrayList<String>();
    }

    public List<Asistencia> findAsistenciaByUsuario(long numeroId, Date fecha) {
        System.out.println(numeroId + " " + fecha);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM lusadi.ASISTENCIA");
        StringBuilder sbConditionals = new StringBuilder();
        if (fecha != null) {
            if (sbConditionals.length() != 0) {
                sbConditionals.append(" AND ");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
            sbConditionals.append(" FECHA_HORA_INGRESO >= '").append(dateFormat.format(fecha)).append("'");
        }
        if (numeroId != -1) {
            if (sbConditionals.length() != 0) {
                sbConditionals.append(" AND ");
            }
            sbConditionals.append(" NUMERO_ID = ").append(numeroId);
        }
        Query query = em.createNativeQuery(sb.toString() + ((sbConditionals.length() != 0) ? " WHERE " + sbConditionals.toString() : ""), Asistencia.class);
        return new ArrayList<Asistencia>(query.getResultList());
    }

}
