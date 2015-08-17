package com.lusadi.utils;

import java.io.IOException;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andresfelipegarciaduran
 */
public class UtilFaces {

    private static UtilFaces facesUtil;

    private UtilFaces() {
    }

    public static UtilFaces getFacesUtil() {
        if (facesUtil == null) {
            facesUtil = new UtilFaces();
        }
        return facesUtil;
    }

    public HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getSession(true);
    }

    public void redirect(String string) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(string);
    }

    public void addMessage(FacesMessage.Severity SEVERITY, String string, String toString) {
        FacesMessage message = new FacesMessage(SEVERITY, string, toString);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getContextPath() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getContextPath();
    }

    public HttpServletRequest getHttpServletRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public void execute(String string) {
        RequestContext.getCurrentInstance().execute(string);
    }

    public void openDialog(String string, Map<String, Object> options) {
        RequestContext.getCurrentInstance().openDialog(string, options, null);
    }

    public void closeDialog(Object object) {
        RequestContext.getCurrentInstance().closeDialog(object);
    }

    public void update(String string) {
        RequestContext.getCurrentInstance().update(string);
    }

    public void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }
}
