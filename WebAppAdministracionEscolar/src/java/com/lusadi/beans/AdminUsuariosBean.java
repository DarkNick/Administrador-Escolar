/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.beans;

import com.lusadi.modelo.Funcion;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author andresfelipegarciaduran
 */
@Named(value = "adminUsuariosBean")
@ViewScoped
public class AdminUsuariosBean implements Serializable {

    private TreeNode root;
    private TreeNode selectedNode;

    public AdminUsuariosBean() {
    }

    @PostConstruct
    public void init() {
        root = createDocuments();
    }

    public TreeNode getRoot() {
        return root;
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

    private TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Funcion("Funciones", null, null), null);

        TreeNode node01 = new DefaultTreeNode(new Funcion("Docente", null, null), root);
        node01.setExpanded(true);
        TreeNode node02 = new DefaultTreeNode(new Funcion("Alumno", null, null), root);
        node02.setExpanded(true);

        TreeNode node01_01 = new DefaultTreeNode(new Funcion("Registrar Docente", "registro-docente.xhtml", "D"), node01);
        TreeNode node01_02 = new DefaultTreeNode(new Funcion("Busqueda Docente", "busqueda-docente.xhtml", "D"), node01);
        TreeNode node01_03 = new DefaultTreeNode(new Funcion("Eliminar Docente", "busqueda-docente.xhtml", "D"), node01);

        TreeNode node02_01 = new DefaultTreeNode(new Funcion("Registrar Alumno", "registro-docente.xhtml", "D"), node02);
        TreeNode node02_02 = new DefaultTreeNode(new Funcion("Busqueda Alumno", "busqueda-docente.xhtml", "D"), node02);
        TreeNode node02_03 = new DefaultTreeNode(new Funcion("Eliminar Alumno", "busqueda-docente.xhtml", "D"), node02);

        return root;
    }

}
