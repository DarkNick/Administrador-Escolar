package com.lusadi.beans;

import com.lusadi.modelo.Funcion;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "homeConfigBean")
@ViewScoped
public class HomeConfigBean implements Serializable {

    private TreeNode root01;
    private TreeNode selectedNode;
    private String pathForwardMenu01 = "/modulos/admin-usuario/menu-administrador.xhtml";

    public HomeConfigBean() {
    }

    @PostConstruct
    public void init() {
        root01 = createRelation01();
    }

    public TreeNode getRoot() {
        return root01;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle() {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteNode() {
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);

        selectedNode = null;
    }

    private TreeNode createRelation01() {
        TreeNode root = new DefaultTreeNode(new Funcion("Funciones", null, null), null);

        TreeNode node01 = new DefaultTreeNode(new Funcion("Docente", null, null), root);
        node01.setExpanded(true);
        TreeNode node02 = new DefaultTreeNode(new Funcion("Alumno", null, null), root);
        node02.setExpanded(true);

        TreeNode node01_01 = new DefaultTreeNode(new Funcion("Registrar Docente", "/modulos/admin-usuario/registro-docentes.xhtml", "D"), node01);
        TreeNode node01_02 = new DefaultTreeNode(new Funcion("Busqueda Docente", "/modulos/admin-usuario/busqueda-docente.xhtml", "D"), node01);
        TreeNode node01_03 = new DefaultTreeNode(new Funcion("Eliminar Docente", "/modulos/admin-usuario/eliminar-docente.xhtml", "D"), node01);

        TreeNode node02_01 = new DefaultTreeNode(new Funcion("Registrar Alumno", "/modulos/admin-usuario/registro-alumno.xhtml", "D"), node02);
        TreeNode node02_02 = new DefaultTreeNode(new Funcion("Busqueda Alumno", "/modulos/admin-usuario/busqueda-alumno.xhtml", "D"), node02);
        TreeNode node02_03 = new DefaultTreeNode(new Funcion("Eliminar Alumno", "/modulos/admin-usuario/eliminar-alumno.xhtml", "D"), node02);

        return root;
    }

    public void onFunctionSelectMenu01(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        setPathForwardMenu01(funcion.getUrlFuncion());
        RequestContext.getCurrentInstance().update("center");
    }

    public String getPathForwardMenu01() {
        return pathForwardMenu01;
    }

    public void setPathForwardMenu01(String pathForwardMenu01) {
        this.pathForwardMenu01 = pathForwardMenu01;
    }

}
