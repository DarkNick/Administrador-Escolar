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
        TreeNode root = new DefaultTreeNode(new Funcion("Files", "-", "Folder"), null);

        TreeNode documents = new DefaultTreeNode(new Funcion("Documents", "-", "Folder"), root);
        TreeNode pictures = new DefaultTreeNode(new Funcion("Pictures", "-", "Folder"), root);
        TreeNode movies = new DefaultTreeNode(new Funcion("Movies", "-", "Folder"), root);

        TreeNode work = new DefaultTreeNode(new Funcion("Work", "-", "Folder"), documents);
        TreeNode primefaces = new DefaultTreeNode(new Funcion("PrimeFaces", "-", "Folder"), documents);

        //Documents
        TreeNode expenses = new DefaultTreeNode("document", new Funcion("Expenses.doc", "30 KB", "Word Document"), work);
        TreeNode resume = new DefaultTreeNode("document", new Funcion("Resume.doc", "10 KB", "Word Document"), work);
        TreeNode refdoc = new DefaultTreeNode("document", new Funcion("RefDoc.pages", "40 KB", "Pages Document"), primefaces);

        //Pictures
        TreeNode barca = new DefaultTreeNode("picture", new Funcion("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new DefaultTreeNode("picture", new Funcion("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new DefaultTreeNode("picture", new Funcion("optimusprime.png", "96 KB", "PNG Image"), pictures);

        //Movies
        TreeNode pacino = new DefaultTreeNode(new Funcion("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new DefaultTreeNode(new Funcion("Robert De Niro", "-", "Folder"), movies);

        TreeNode scarface = new DefaultTreeNode("mp3", new Funcion("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Funcion("Carlitos' Way", "24 GB", "Movie File"), pacino);

        TreeNode goodfellas = new DefaultTreeNode("mp3", new Funcion("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new DefaultTreeNode("mp3", new Funcion("Untouchables", "17 GB", "Movie File"), deniro);

        return root;
    }

}
