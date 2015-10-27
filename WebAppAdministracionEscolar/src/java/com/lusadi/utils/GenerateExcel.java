/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.utils;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

/**
 *
 * @author Sebastian Vega
 */
public class GenerateExcel {

    private BigInteger salario;
    private int dias;
    private double sub_trans;
    private double totalDevengado;
    private double salud;
    private double pension;
    private double totalDeducido;
    private double netoPagar;
    private double totalSumaSalario = 0;
    private double totalSumaSub_trans = 0;
    private double totalSumaDevengado = 0;
    private double totalSumaNeto = 0;
    private double totalSumaSalud = 0;
    private double totalSumaPension = 0;
    private double totalSumaDeducido = 0;
    private static GenerateExcel generateExcel;

    public GenerateExcel() {
    }

    public static GenerateExcel getGenerateExcel() {
        if (generateExcel == null) {
            generateExcel = new GenerateExcel();
        }
        return generateExcel;
    }

    public byte[] generarNomina(ArrayList<String> documento) throws IOException {
        Calendar calendar = new GregorianCalendar();
        String month = recognizeMonth((calendar.get(Calendar.MONTH) + 1));

        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet(month);

        Font my_fontTitle = libro.createFont();
        my_fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle my_style = libro.createCellStyle();
        my_style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
        my_style.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
        my_style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
        my_style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        my_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        CellStyle my_styleTitle = libro.createCellStyle();
        my_styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        my_styleTitle.setFont(my_fontTitle);

        Row fila1 = hoja.createRow(0);
        org.apache.poi.ss.usermodel.Cell celda = fila1.createCell(6);
        celda.setCellStyle(my_styleTitle);
        celda.setCellValue("NUEVO COLEGIO LUSADI LTDA");
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 6, 14));

        Row fila2 = hoja.createRow(1);
        celda = fila2.createCell(6);
        celda.setCellStyle(my_styleTitle);
        celda.setCellValue("NOMINA DE SUELDOS  01 De Abril  AL  30 de Abril DE 2015");
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 6, 14));

        Row fila3 = hoja.createRow(2);
        celda = fila3.createCell(11);
        celda.setCellStyle(my_styleTitle);
        celda.setCellValue("NIT ");
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 11, 14));

        Row fila4 = hoja.createRow(3);
        celda = fila4.createCell(8);
        celda.setCellValue("DEVENGADOS");
        CellStyle cs = libro.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.AQUA.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        CellUtil.getCell(fila4, 8).setCellStyle(cs);
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 8, 9));
        celda = fila4.createCell(10);
        celda.setCellValue("DEDUCIDOS");
        cs = libro.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREEN.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        CellUtil.getCell(fila4, 10).setCellStyle(cs);
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 10, 12));

        Row fila5 = hoja.createRow(4);
        celda = fila5.createCell(1);
        celda.setCellValue("NUMERO ID");
        cs = libro.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
        CellUtil.getCell(fila5, 1).setCellStyle(cs);
        celda = fila5.createCell(2);
        celda.setCellValue("PRIMER APELLIDO");
        CellUtil.getCell(fila5, 2).setCellStyle(cs);
        celda = fila5.createCell(3);
        celda.setCellValue("SEGUNDO APELLIDO");
        CellUtil.getCell(fila5, 3).setCellStyle(cs);
        celda = fila5.createCell(4);
        celda.setCellValue("PRIMER NOMBRE");
        CellUtil.getCell(fila5, 4).setCellStyle(cs);
        celda = fila5.createCell(5);
        celda.setCellValue("SEGUNDO NOMBRE");
        CellUtil.getCell(fila5, 5).setCellStyle(cs);
        celda = fila5.createCell(6);
        celda.setCellValue("SALARIO");
        CellUtil.getCell(fila5, 6).setCellStyle(cs);

        celda = fila5.createCell(7);
        celda.setCellValue("DIAS");
        cs = libro.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.AQUA.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
        CellUtil.getCell(fila5, 7).setCellStyle(cs);
        celda = fila5.createCell(8);
        celda.setCellValue("SUB/TRANSS");
        CellUtil.getCell(fila5, 8).setCellStyle(cs);
        celda = fila5.createCell(9);
        celda.setCellValue("TOTAL DEVENGADO");
        CellUtil.getCell(fila5, 9).setCellStyle(cs);
        celda = fila5.createCell(10);
        celda.setCellValue("SALUD");
        CellUtil.getCell(fila5, 10).setCellStyle(cs);
        celda = fila5.createCell(11);
        celda.setCellValue("PENSIONES");
        CellUtil.getCell(fila5, 11).setCellStyle(cs);
        celda = fila5.createCell(12);
        celda.setCellValue("TOTAL DEDUCIDO");
        CellUtil.getCell(fila5, 12).setCellStyle(cs);
        celda = fila5.createCell(13);
        celda.setCellValue("NETO A PAGAR");
        CellUtil.getCell(fila5, 13).setCellStyle(cs);
        celda = fila5.createCell(14);
        celda.setCellValue("FIRMA");
        CellUtil.getCell(fila5, 14).setCellStyle(cs);

        CellStyle my_style2 = libro.createCellStyle();
        my_style2.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
        my_style2.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
        my_style2.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
        my_style2.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        Font my_font = libro.createFont();
        my_font.setColor(Font.COLOR_RED);
        System.out.println(documento);
        for (int i = 0, j = 0; i < documento.size(); i += 6, j++) {
            salario = new BigInteger(documento.get(i + 4));
            dias = Integer.parseInt(documento.get(i + 5));
            System.out.println(salario + " " + dias);
            sub_trans = ((2466.666666666666667) * dias);
            totalDevengado = (salario.doubleValue() / 30) * dias + sub_trans;
            salud = salario.doubleValue() * 4 / 100;
            pension = salario.doubleValue() * 4 / 100;
            totalDeducido = salud + pension;
            netoPagar = totalDevengado - totalDeducido;

            totalSumaSalario += salario.doubleValue();
            totalSumaSub_trans += sub_trans;
            totalSumaDevengado += totalDevengado;
            totalSumaDeducido += totalDeducido;
            totalSumaPension += pension;
            totalSumaSalud += salud;
            totalSumaNeto += netoPagar;

            Row fila6 = hoja.createRow(5 + j);
            my_style2.setFont(my_font);

            celda = fila6.createCell(1);
            celda.setCellValue(documento.get(i));
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(2);
            celda.setCellValue(documento.get(i + 1));
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(3);
            celda.setCellValue(documento.get(i + 2));
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(4);
            celda.setCellValue(documento.get(i + 3));
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(5);
            celda.setCellValue(documento.get(i + 4));
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(6);
            celda.setCellValue(salario.toString());
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(7);
            celda.setCellValue(dias);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(8);
            celda.setCellValue(sub_trans);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(9);
            celda.setCellValue(totalDevengado);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(10);
            celda.setCellValue(salud);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(11);
            celda.setCellValue(pension);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(12);
            celda.setCellValue(totalDeducido);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(13);
            celda.setCellValue(netoPagar);
            celda.setCellStyle(my_style2);
            celda = fila6.createCell(14);
            celda.setCellStyle(my_style2);
        }

        Row fila9 = hoja.createRow(documento.size() / 7 + 5);
        cs = libro.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        celda = fila9.createCell(6);
        celda.setCellValue(totalSumaSalario);
        CellUtil.getCell(fila9, 6).setCellStyle(cs);
        celda = fila9.createCell(8);
        celda.setCellValue(totalSumaSub_trans);
        CellUtil.getCell(fila9, 8).setCellStyle(cs);
        celda = fila9.createCell(9);
        celda.setCellValue(totalSumaDevengado);
        CellUtil.getCell(fila9, 9).setCellStyle(cs);
        celda = fila9.createCell(10);
        celda.setCellValue(totalSumaSalud);
        CellUtil.getCell(fila9, 10).setCellStyle(cs);
        celda = fila9.createCell(11);
        celda.setCellValue(totalSumaPension);
        CellUtil.getCell(fila9, 11).setCellStyle(cs);
        celda = fila9.createCell(12);
        celda.setCellValue(totalSumaDeducido);
        CellUtil.getCell(fila9, 12).setCellStyle(cs);
        celda = fila9.createCell(13);
        celda.setCellValue(totalSumaNeto);
        CellUtil.getCell(fila9, 13).setCellStyle(cs);

        Row fila7 = hoja.createRow(documento.size() / 7 + 5);
        cs = libro.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
        cs.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
        celda = fila7.createCell(8);
        celda.setCellValue("APORTE");
        CellUtil.getCell(fila7, 8).setCellStyle(cs);
        celda = fila7.createCell(9);
        celda.setCellValue("APORTE TRA");
        CellUtil.getCell(fila7, 9).setCellStyle(cs);
        celda = fila7.createCell(10);
        celda.setCellValue("TOTAL APORTE");
        CellUtil.getCell(fila7, 10).setCellStyle(cs);

        for (int i = 0; i < documento.size(); i++) {
            Row fila8 = hoja.createRow(documento.size() / 7 + 6);
            for (int j = 0; j < 3; j++) {
                celda = fila8.createCell(j + 8);
                celda.setCellValue(documento.get(i));
                celda.setCellStyle(my_style);
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            libro.write(bos);
        } finally {
            bos.close();
        }
        return bos.toByteArray();
    }

    private String recognizeMonth(int index) {
        if (index == 1) {
            return "ENERO";
        } else if (index == 2) {
            return "FEBRERO";
        } else if (index == 3) {
            return "MARZO";
        } else if (index == 4) {
            return "ABRIL";
        } else if (index == 5) {
            return "MAYO";
        } else if (index == 6) {
            return "JUNIO";
        } else if (index == 7) {
            return "JULIO";
        } else if (index == 8) {
            return "AGOSTO";
        } else if (index == 9) {
            return "SEPTIEMBRE";
        } else if (index == 10) {
            return "OCTUBRE";
        } else if (index == 11) {
            return "NOVIEMBRE";
        } else if (index == 12) {
            return "DICIEMBRE";
        }
        return null;
    }

    public String currentDateToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

}
