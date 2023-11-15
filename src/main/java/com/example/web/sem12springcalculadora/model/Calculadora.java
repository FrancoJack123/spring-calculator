package com.example.web.sem12springcalculadora.model;

public class Calculadora {
    private Integer a;
    private Integer b;
    private String operacion;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void Asignar(Integer numero){
        if (a == null){
            a = numero;
            return;
        }
        if (b == null){
            b = numero;
        }
    }

    public void LimpiarCalculadora(){
        a=null;
        b=null;
        operacion=null;
    }
}
