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
 * @modificado Diego Pineda =)
 */
@Named(value = "homeConfigBean")
@ViewScoped
public class HomeConfigBean implements Serializable {

    private TreeNode root01, root02, root03, root04;
    private TreeNode selectedNode01, selectedNode02, selectedNode03, selectedNode04;
    private String pathForwardMenu01 = "/modulos/admin-usuario/menu-administrador.xhtml";
    private String pathForwardMenu02 = "/modulos/admin-asistencia/menu-control-asistencia.xhtml";
    private String pathForwardMenu03 = "/modulos/admin-nomina/menu-nomina.xhtml";
    private String pathForwardMenu04 = "/modulos/admin-registro/menu-registro.xhtml";

    public TreeNode getRoot04() {
        return root04;
    }

    public void setRoot04(TreeNode root04) {
        this.root04 = root04;
    }

    public TreeNode getSelectedNode04() {
        return selectedNode04;
    }

    public void setSelectedNode04(TreeNode selectedNode04) {
        this.selectedNode04 = selectedNode04;
    }

    public String getPathForwardMenu04() {
        return pathForwardMenu04;
    }

    public void setPathForwardMenu04(String pathForwardMenu04) {
        this.pathForwardMenu04 = pathForwardMenu04;
    }

    public TreeNode getRoot03() {
        return root03;
    }

    public void setRoot03(TreeNode root03) {
        this.root03 = root03;
    }

    public TreeNode getSelectedNode03() {
        return selectedNode03;
    }

    public void setSelectedNode03(TreeNode selectedNode03) {
        this.selectedNode03 = selectedNode03;
    }

    public String getPathForwardMenu03() {
        return pathForwardMenu03;
    }

    public void setPathForwardMenu03(String pathForwardMenu03) {
        this.pathForwardMenu03 = pathForwardMenu03;
    }

    @PostConstruct
    public void init() {
        root01 = createRelationAdministracionUsuario();
        root02 = createRelationAdministracionAsistencia();
        root03 = createRelatioAdministracionNomina();
        root04 = createRelationAdministracionRegistroAcademico();
    }

    public TreeNode getRoot01() {
        return root01;
    }

    public void setRoot01(TreeNode root01) {
        this.root01 = root01;
    }

    public TreeNode getRoot02() {
        return root02;
    }

    public void setRoot02(TreeNode root02) {
        this.root02 = root02;
    }

    public TreeNode getSelectedNode01() {
        return selectedNode01;
    }

    public void setSelectedNode01(TreeNode selectedNode01) {
        this.selectedNode01 = selectedNode01;
    }

    public TreeNode getSelectedNode02() {
        return selectedNode02;
    }

    public void setSelectedNode02(TreeNode selectedNode02) {
        this.selectedNode02 = selectedNode02;
    }

    public String getPathForwardMenu01() {
        return pathForwardMenu01;
    }

    public void setPathForwardMenu01(String pathForwardMenu01) {
        this.pathForwardMenu01 = pathForwardMenu01;
    }

    public String getPathForwardMenu02() {
        return pathForwardMenu02;
    }

    public void setPathForwardMenu02(String pathForwardMenu02) {
        this.pathForwardMenu02 = pathForwardMenu02;
    }

    private TreeNode createRelationAdministracionUsuario() {
        TreeNode root = new DefaultTreeNode(new Funcion("Funciones", null, null), null);

        TreeNode node01 = new DefaultTreeNode(new Funcion("Docente", null, null), root);
        node01.setExpanded(true);
        TreeNode node02 = new DefaultTreeNode(new Funcion("Alumno", null, null), root);
        node02.setExpanded(true);

        TreeNode node01_01 = new DefaultTreeNode(new Funcion("Registrar Docente", "/modulos/admin-usuario/registro-docentes.xhtml", "D"), node01);
        TreeNode node01_02 = new DefaultTreeNode(new Funcion("Busqueda Docente", "/modulos/admin-usuario/busqueda-docente.xhtml", "D"), node01);
        TreeNode node01_03 = new DefaultTreeNode(new Funcion("Eliminar Docente", "/modulos/admin-usuario/eliminar-docente.xhtml", "D"), node01);

        TreeNode node02_01 = new DefaultTreeNode(new Funcion("Registrar Alumno", "/modulos/admin-usuario/registro-estudiante.xhtml", "D"), node02);
        TreeNode node02_02 = new DefaultTreeNode(new Funcion("Busqueda Alumno", "/modulos/admin-usuario/busqueda-alumno.xhtml", "D"), node02);
        TreeNode node02_03 = new DefaultTreeNode(new Funcion("Eliminar Alumno", "/modulos/admin-usuario/eliminar-alumno.xhtml", "D"), node02);

        return root;
    }

    private TreeNode createRelationAdministracionAsistencia() {
        TreeNode root = new DefaultTreeNode(new Funcion("Funciones", null, null), null);

        TreeNode node01 = new DefaultTreeNode(new Funcion("Asistencias", null, null), root);
        node01.setExpanded(true);

        TreeNode node01_01 = new DefaultTreeNode(new Funcion("Registro Asistencia", "/modulos/admin-asistencia/registro-asistencia.xhtml", "D"), node01);
        TreeNode node01_02 = new DefaultTreeNode(new Funcion("Consulta Asistencia", "/modulos/admin-asistencia/consulta-asistencia.xhtml", "D"), node01);
        return root;
    }

    private TreeNode createRelatioAdministracionNomina() {
        TreeNode root = new DefaultTreeNode(new Funcion("Funciones", null, null), null);

        TreeNode node01 = new DefaultTreeNode(new Funcion("Nomina", null, null), root);
        node01.setExpanded(true);

        TreeNode node01_01 = new DefaultTreeNode(new Funcion("Consulta Pagos", "/modulos/admin-nomina/busqueda-nomina.xhtml", "D"), node01);
        TreeNode node01_02 = new DefaultTreeNode(new Funcion("Registro pago", "/modulos/admin-usuario/busqueda-docente.xhtml", "D"), node01);

        return root;
    }

    private TreeNode createRelationAdministracionRegistroAcademico() {
        TreeNode root = new DefaultTreeNode(new Funcion("Funciones", null, null), null);

        TreeNode node01 = new DefaultTreeNode(new Funcion("Horario", null, null), root);
        node01.setExpanded(true);
        TreeNode node02 = new DefaultTreeNode(new Funcion("Matricula Estudiante", null, null), root);
        node02.setExpanded(true);
        TreeNode node03 = new DefaultTreeNode(new Funcion("Registro Notas", null, null), root);
        node03.setExpanded(true);

        TreeNode node01_01 = new DefaultTreeNode(new Funcion("Crear / Modificar / Eliminar - Materia", "/modulos/admin-usuario/registro-docentes.xhtml", "D"), node01);
        TreeNode node01_02 = new DefaultTreeNode(new Funcion("Crear / Modificar / Eliminar - Salon", "/modulos/admin-usuario/registro-docentes.xhtml", "D"), node01);
        TreeNode node01_03 = new DefaultTreeNode(new Funcion("Crear / Modificar / Eliminar - Curso", "/modulos/admin-usuario/registro-docentes.xhtml", "D"), node01);
        TreeNode node01_04 = new DefaultTreeNode(new Funcion("Crear - Registro Materias", "/modulos/admin-usuario/registro-docentes.xhtml", "D"), node01);

        TreeNode node02_01 = new DefaultTreeNode(new Funcion("Registro Estudiante", "/modulos/admin-usuario/registro-alumno.xhtml", "D"), node02);

        TreeNode node03_01 = new DefaultTreeNode(new Funcion("Consulta Historial Academico", "/modulos/admin-usuario/registro-alumno.xhtml", "D"), node03);
        TreeNode node03_02 = new DefaultTreeNode(new Funcion("Registro Notas", "/modulos/admin-usuario/registro-alumno.xhtml", "D"), node03);

        return root;
    }

    public void onFunctionSelectMenu01(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        setPathForwardMenu01(funcion.getUrlFuncion());
        RequestContext.getCurrentInstance().update("center01");
    }

    public void onFunctionSelectMenu02(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        setPathForwardMenu02(funcion.getUrlFuncion());
        RequestContext.getCurrentInstance().update("center02");
    }

    public void onFunctionSelectMenu03(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        setPathForwardMenu03(funcion.getUrlFuncion());
        RequestContext.getCurrentInstance().update("center03");
    }

    public void onFunctionSelectMenu04(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        setPathForwardMenu04(funcion.getUrlFuncion());
        RequestContext.getCurrentInstance().update("center04");
    }
}
