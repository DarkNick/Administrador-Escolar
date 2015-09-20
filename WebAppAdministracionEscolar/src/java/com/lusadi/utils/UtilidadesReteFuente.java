/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.utils;

/**
 *
 * @author Sebastian Vega
 */
public class UtilidadesReteFuente {

    private static final Integer DIAS_TOTAL_MES = 30;
    private static final Integer DIAS_DEL_ANIO = 240;
    private static final double PORCENTAJE_HORAS_EXTRA_DIURNA = 1.25;
    private static final double PORCENTAJE_HORAS_EXTRA_NOCTURNA = 1.75;
    private static final double PORCENTAJE_HORAS_EXTRA_DOMINICAL_DIURNA = 2;
    private static final double PORCENTAJE_HORAS_EXTRA_DOMINICAL_NOCTURNA = 2.5;
    private static final double PORCENTAJE_RECARGO_NOCTURNO = 0.0135;
    private static final double VALOR_AUXILIO_TRANSPORTE = 74000;
    private static final double VALOR_SALARIO_MINIMO_LEGAL_VIGENTE = 644350;
    private static final double PORCENTAJE_SALUD = 0.04;
    private static final double PORCENTAJE_PENSION = 0.04;
    private static final double PORCENTAJE_EXENTO = 0.75;
    private static final double VALOR_UVT = 28279;

    public UtilidadesReteFuente() {
    }

    public static double calcularSalarioBasico(double salario, Integer diasTrabajados) {
        return ((salario * diasTrabajados) / 30);
    }

    public static double calcularValorHoraOrdinario(double salario) {
        return (salario / DIAS_DEL_ANIO);
    }

    public static double calcularHoraExtraDiurna(double valorHoraOrdinario, Integer numerHoras) {
        return (valorHoraOrdinario * numerHoras * PORCENTAJE_HORAS_EXTRA_DIURNA);
    }

    public static double calcularHoraExtraNocturnas(double valorHoraOrdinario, Integer numerHorasNoc) {
        return (valorHoraOrdinario * numerHorasNoc * PORCENTAJE_HORAS_EXTRA_NOCTURNA);
    }

    public static double calcularHoraExtraDomiDiu(double valorHoraOrdinario, Integer numerHoras) {
        return (valorHoraOrdinario * numerHoras * PORCENTAJE_HORAS_EXTRA_DOMINICAL_DIURNA);
    }

    public static double calcularHoraExtraDomiNocturna(double valorHoraOrdinario, Integer numerHoras) {
        return (valorHoraOrdinario * numerHoras * PORCENTAJE_HORAS_EXTRA_DOMINICAL_NOCTURNA);
    }

    public static double calcularRecargoNocturno(double valorHoraOrdinario, Integer numerHoras) {
        return (valorHoraOrdinario * numerHoras * PORCENTAJE_RECARGO_NOCTURNO);
    }

    public static double calcularValorIbc(double totalDevengado) {
        double resultado = totalDevengado;
        if (totalDevengado < (VALOR_SALARIO_MINIMO_LEGAL_VIGENTE * 2)) {
            resultado = totalDevengado - VALOR_AUXILIO_TRANSPORTE;
        }
        return resultado;
    }

    public static double calcularValorSalud(double ibc) {
        return (ibc * PORCENTAJE_SALUD);
    }

    public static double calcularValorPension(double ibc) {
        return (ibc * PORCENTAJE_PENSION);
    }

    public static double calcularFondoSolidaridad(double totalDevengado) {
        if (totalDevengado > (VALOR_SALARIO_MINIMO_LEGAL_VIGENTE * 4)) {
            return totalDevengado * 0.01;
        }
        return 0.0;
    }

    public static double calcularExentoPorcentaje(double salarioBase) {
        return salarioBase * PORCENTAJE_EXENTO;
    }

    public static double calcularRetencion(double valorSalarioExento) {
        double resultadoCalculo = 0.0;
        double valorTemporal = valorSalarioExento / VALOR_UVT;

        if (valorTemporal >= 0 && valorTemporal < 95) {
            resultadoCalculo = 0.0;
        } else if (valorTemporal >= 95 && valorTemporal < 150) {
            resultadoCalculo = (valorTemporal - 95) * .19;
        } else if (valorTemporal >= 150 && valorTemporal < 360) {
            resultadoCalculo = (valorTemporal - 150) * .28 + (VALOR_UVT * 10);
        } else if (valorTemporal >= 360) {
            resultadoCalculo = (valorTemporal - 360) * .33 + (VALOR_UVT * 69);
        }
        return resultadoCalculo;
    }
}
