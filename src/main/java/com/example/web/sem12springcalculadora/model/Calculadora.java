package com.example.web.sem12springcalculadora.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

