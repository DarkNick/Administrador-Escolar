/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.dao;

import com.lusadi.entities.HistorialNomina;
import com.lusadi.utils.GenerateExcel;
import com.lusadi.utils.UtilFaces;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.poi.util.IOUtils;

/**
 *
 * @author Personal
 */
@Stateless
public class HistorialNominaFacade extends AbstractFacade<HistorialNomina> {

    @PersistenceContext(unitName = "WebAppAdministracionEscolarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialNominaFacade() {
        super(HistorialNomina.class);
    }

    public List<HistorialNomina> findAllById(String tipoId, long numeroId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM lusadi.HISTORIAL_NOMINA WHERE FUNCIONARIO_ID IN ");
        sb.append("(SELECT FUNCIONARIO_ID FROM lusadi.FUNCIONARIO WHERE NUMERO_ID = ? AND TIPO_ID = ?);");
        Query query = em.createNativeQuery(sb.toString(), HistorialNomina.class).setParameter(1, numeroId).setParameter(2, tipoId);
        return query.getResultList();
    }

    public void createHistorialNomina(HistorialNomina historialNomina) throws IOException, SQLException {
        File outputFile = new File("AJUNTO_" + historialNomina.getHistorialNominaId() + "_" + GenerateExcel.getGenerateExcel().currentDateToString());
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(historialNomina.getSoporteAdjunto());
        fos.close();
        Connection connection = em.unwrap(Connection.class);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO lusadi.HISTORIAL_NOMINA (FECHA_REGISTRO,RESPONSABLE_PAGO,SOPORTE_ADJUNTO,FUNCIONARIO_ID)  VALUES (NOW(), ?, ?, ?)");
        PreparedStatement prepareStatement = connection.prepareStatement(sb.toString());
        prepareStatement.setString(1, historialNomina.getResponsablePago());
        prepareStatement.setBinaryStream(2, new FileInputStream(outputFile));
        prepareStatement.setInt(3, historialNomina.getFuncionarioId().getFuncionarioId());
        prepareStatement.executeUpdate();
        outputFile.delete();
    }

}
