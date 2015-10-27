package com.lusadi.beans;

import com.lusadi.dao.FuncionarioFacade;
import com.lusadi.dao.HistorialNominaFacade;
import com.lusadi.entities.Funcionario;
import com.lusadi.entities.HistorialNomina;
import com.lusadi.entities.UsuarioPK;
import com.lusadi.utils.GenerateExcel;
import com.lusadi.utils.UtilFaces;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "adminNominaBean")
@ViewScoped
public class AdminNominaBean implements Serializable {

    @EJB
    private HistorialNominaFacade historialNominaFacade;

    @EJB
    private FuncionarioFacade funcionarioFacade;

    private UsuarioPK usuarioPK = new UsuarioPK();
    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> listFuncionarios;
    private List<Funcionario> filteredListFuncionarios;
    private List<Funcionario> filteredListHistorialFuncionarios;

    private List<HistorialNomina> listPagosFuncionario = new ArrayList<HistorialNomina>();
    private Part file;
    private HistorialNomina historialNomina = new HistorialNomina();

    public AdminNominaBean() {
    }

    @PostConstruct
    public void init() {
        this.listFuncionarios = funcionarioFacade.findAll();
    }

    public void generateExcelNomina(String funcionarioId) {

    }

    public void generateExcelNominaCompleta() {
        try {
            ArrayList<String> listParam = new ArrayList<String>();
            List<Funcionario> findAll = funcionarioFacade.findAll();
            for (Funcionario f : findAll) {
                listParam.add(String.valueOf(f.getUsuario().getUsuarioPK().getNumeroId()));
                listParam.add(f.getUsuario().getSegundoApellido());
                listParam.add(f.getUsuario().getPrimerApellido());
                listParam.add(f.getUsuario().getNombres());
                listParam.add(String.valueOf(f.getSaldoNeto()));
                listParam.add(String.valueOf(new Integer("30")));
            }
            //Funcionario funcionario = funcionarioFacade.find(Integer.parseInt(funcionarioId));
            /*listParam.add(String.valueOf(funcionario.getUsuario().getUsuarioPK().getNumeroId()));
             listParam.add(String.valueOf(funcionario.getUsuario().getPrimerApellido()));
             listParam.add(String.valueOf(funcionario.getUsuario().getSegundoApellido()));
             listParam.add(String.valueOf(funcionario.getUsuario().getNombres()));
             listParam.add(String.valueOf(String.valueOf(funcionario.getSaldoNeto())));
             */
            byte[] generarNomina = GenerateExcel.getGenerateExcel().generarNomina(listParam);
            String nombreArchivo = "NOMINA_" + GenerateExcel.getGenerateExcel().currentDateToString() + ".xls";
            UtilFaces.getFacesUtil().downloadFile(nombreArchivo, generarNomina);
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "EL REGISTRO FUE ALMACENADO CORRECTAMENTE");
        } catch (IOException ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void cargarHistorialFuncionario() {
        this.listPagosFuncionario = historialNominaFacade.findAllById(usuarioPK.getTipoId(), usuarioPK.getNumeroId());
        RequestContext.getCurrentInstance().update("historial-table");
    }

    public void registarPago() {
        if (file.getSize() == 0) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "SELECCIONE UN ARCHIVO.");
        } else {
            try {
                Funcionario funcionario = funcionarioFacade.findByTypeIdAndNumberId(usuarioPK.getTipoId(), usuarioPK.getNumeroId());
                if (funcionario == null) {
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "{TIPO: " + usuarioPK.getTipoId() + " NUMERO: " + usuarioPK.getNumeroId() + "} NO SE ENCUENTRA EN EL SISTEMA.");
                } else {
                    byte[] parseInputStreamToArrayByte = UtilFaces.getFacesUtil().parseInputStreamToArrayByte(file.getInputStream());
                    historialNomina.setSoporteAdjunto(parseInputStreamToArrayByte);
                    historialNomina.setFechaRegistro(Calendar.getInstance().getTime());
                    historialNomina.setFuncionarioId(funcionario);
                    historialNominaFacade.createHistorialNomina(historialNomina);
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "EL REGISTRO FUE ALMACENADO CORRECTAMENTE");
                }
            } catch (IOException ex) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
            } catch (SQLException ex) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
            }
        }
    }

    public void descargarAdjunto(String historialNominaId) {
        try {
            String nombreArchivo = "SOPORTE-ADJUNTO_" + usuarioPK.getTipoId() + "_" + usuarioPK.getNumeroId()
                    + "_" + GenerateExcel.getGenerateExcel().currentDateToString();
            HistorialNomina historialNomina = historialNominaFacade.find(Integer.parseInt(historialNominaId));
            byte[] generarNomina = historialNomina.getSoporteAdjunto();
            UtilFaces.getFacesUtil().downloadFile(nombreArchivo, generarNomina);
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "EL REGISTRO FUE ALMACENADO CORRECTAMENTE");
        } catch (IOException ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public List<String> obtenerNumerosIdSegun(String query) {
        List<String> outcome = new ArrayList<String>();
        outcome.addAll(funcionarioFacade.findAllNumebersIdStartWith(query));
        return outcome;
    }

    public List<Funcionario> getFilteredListFuncionarios() {
        return filteredListFuncionarios;
    }

    public void setFilteredListFuncionarios(List<Funcionario> filteredListFuncionarios) {
        this.filteredListFuncionarios = filteredListFuncionarios;
    }

    public List<Funcionario> getListFuncionarios() {
        return listFuncionarios;
    }

    public void setListFuncionarios(List<Funcionario> listFuncionarios) {
        this.listFuncionarios = listFuncionarios;
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<HistorialNomina> getListPagosFuncionario() {
        return listPagosFuncionario;
    }

    public void setListPagosFuncionario(List<HistorialNomina> listPagosFuncionario) {
        this.listPagosFuncionario = listPagosFuncionario;
    }

    public List<Funcionario> getFilteredListHistorialFuncionarios() {
        return filteredListHistorialFuncionarios;
    }

    public void setFilteredListHistorialFuncionarios(List<Funcionario> filteredListHistorialFuncionarios) {
        this.filteredListHistorialFuncionarios = filteredListHistorialFuncionarios;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public HistorialNomina getHistorialNomina() {
        return historialNomina;
    }

    public void setHistorialNomina(HistorialNomina historialNomina) {
        this.historialNomina = historialNomina;
    }

}
