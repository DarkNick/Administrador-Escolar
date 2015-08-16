package com.lusadi.utils;

import java.io.IOException;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author andresfelipegarciaduran
 */
public class UtilFaces {

    private static UtilFaces FacesUtil;

    public static UtilFaces getFacesUtil() {
        if (FacesUtil == null) {
            FacesUtil = new UtilFaces();
        }
        return FacesUtil;
    }

    public String getRealPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    }

    public String getRequestContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public void addMessage(FacesMessage.Severity severity, String messageBold) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, messageBold, null));
    }

    public Map<String, Object> getRequestMap() {
        Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        return requestMap;
    }

    public Map<String, Object> getSessionMap() {
        Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return requestMap;
    }

    public void redirect(String target) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(target);
    }
}
