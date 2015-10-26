/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.security;

import com.lusadi.constant.SessionValuesEnum;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andresfelipegarciaduran
 */
@WebFilter(filterName = "NavegationFilter", urlPatterns = {"/*"})
public class NavigationFilter implements Filter {

    private final FilterConfig filterConfig = null;

    public NavigationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String scheme = httpRequest.getScheme();             // http
        String serverName = httpRequest.getServerName();     // hostname
        int serverPort = httpRequest.getServerPort();        // 8080
        String contextPath = httpRequest.getContextPath();   // /mywebapp
        String indexURI = "/edu/index.xhtml";
        String homeURI = "/edu/lusadi/administracion-registro.xhtml";
        String requestURI = httpRequest.getRequestURI();
        boolean sessionCreated = false;

        if (!httpRequest.getRequestURI().startsWith(contextPath + ResourceHandler.RESOURCE_IDENTIFIER)) { // Ignore resources (CSS/JS/Images/etc)
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            httpResponse.setDateHeader("Expires", 0); // Proxies.
        }

        if (session != null) {
            Object object = session.getAttribute(SessionValuesEnum.ROL_USER.toString());
            if (object != null) {
                sessionCreated = true;
            }
        }
        if (requestURI.endsWith(".xhtml")) { // Evalua toda peticion terminada en .xhtml.
            String[] tokens = requestURI.split("/");
            String page = tokens[tokens.length - 1]; // vista .xhtml actual.
            //System.out.println("page: " + page + "  session:" + sessionCreated + " ip:" + request.getRemoteHost());
            if (!sessionCreated) {
                if (!indexURI.endsWith(page)) {
                    httpResponse.sendRedirect(contextPath); // Redirige la respuesta al index, ya que no existe sesion.
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                if (session.getAttribute(SessionValuesEnum.ROL_USER.name()) == null) {
                    httpResponse.sendRedirect(contextPath); // Redirige la respuesta al index, ya que no existe sesion.
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
