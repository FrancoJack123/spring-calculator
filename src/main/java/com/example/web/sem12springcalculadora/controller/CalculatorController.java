package com.example.web.sem12springcalculadora.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Calculadora")
public class CalculatorController {

    @Value("${server.port:8080}")
    private int port;

    private Integer a;
    private Integer b;
    private String operacion;

    public void Asignar(Integer numero){
        if (a == null){
            a = numero;
            return;
        }
        if (b == null){
            b = numero;
        }
    }

    @GetMapping("/")
    public String index(Model model){
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numeros.add(i);
        }
        model.addAttribute("numeros", numeros);
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("operacion", operacion);
        return "Calculadora";
    }

    @PostMapping("/Asignar")
    public String OperacionNumeros(@RequestParam Integer numero, RedirectAttributes redirectAttributes){
        this.Asignar(numero);
        redirectAttributes.addFlashAttribute("numero", numero);
        return "redirect:/Calculadora/";
    }

    @PostMapping("/Asignar/Metodo")
    public String OperacionNumeros(@RequestParam String operar, RedirectAttributes redirectAttributes){
        operacion = operar;
        redirectAttributes.addFlashAttribute("numero", "");
        return "redirect:/Calculadora/";
    }

    @GetMapping("/Calcular")
    public String OperacionNumeros(RedirectAttributes redirectAttributes){

        if(a == null){
            return "redirect:/Calculadora/";
        }

        if(b == null){
            return "redirect:/Calculadora/";
        }

        if(operacion == null){
            return "redirect:/Calculadora/";
        }


        int resultado = 0;
        switch (operacion){
            case "suma":
                resultado = a + b;
                break;
            case "resta":
                resultado = a - b;
                break;
            case "multiplicacion":
                resultado = a * b;
                break;
            case "division":
                resultado = a / b;
                break;
        }
        LimpiarCalculadora();
        a=resultado;
        redirectAttributes.addFlashAttribute("numero", resultado);
        return "redirect:/Calculadora/";
    }

    public void LimpiarCalculadora(){
        a=null;
        b=null;
        operacion=null;
    }

    @GetMapping("/Borrar")
    public String Borrar(){
        LimpiarCalculadora();
        return "redirect:/Calculadora/";
    }


}
