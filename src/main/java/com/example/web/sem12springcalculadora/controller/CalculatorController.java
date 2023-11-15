package com.example.web.sem12springcalculadora.controller;

import com.example.web.sem12springcalculadora.model.Calculadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Calculadora")
public class CalculatorController {

    @Value("${server.port:8080}")
    private int port;

    @Autowired
    private Calculadora calculadora;

    @GetMapping("/")
    public String index(Model model){
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numeros.add(i);
        }
        model.addAttribute("numeros", numeros);
        model.addAttribute("a", calculadora.getA());
        model.addAttribute("b", calculadora.getB());
        model.addAttribute("operacion", calculadora.getOperacion());
        return "Calculadora";
    }

    @PostMapping("/Asignar")
    public String OperacionNumeros(@RequestParam Integer numero, RedirectAttributes redirectAttributes){
        calculadora.Asignar(numero);
        redirectAttributes.addFlashAttribute("numero", numero);
        return "redirect:/Calculadora/";
    }

    @PostMapping("/Asignar/Metodo")
    public String OperacionNumeros(@RequestParam String operar, RedirectAttributes redirectAttributes){
        calculadora.setOperacion(operar);
        redirectAttributes.addFlashAttribute("numero", "");
        return "redirect:/Calculadora/";
    }

    @GetMapping("/Calcular")
    public String OperacionNumeros(RedirectAttributes redirectAttributes){

        if(calculadora.getA() == null){
            return "redirect:/Calculadora/";
        }

        if(calculadora.getB() == null){
            return "redirect:/Calculadora/";
        }

        if(calculadora.getOperacion() == null){
            return "redirect:/Calculadora/";
        }


        int resultado = 0;
        switch (calculadora.getOperacion()){
            case "suma":
                resultado = calculadora.getA() + calculadora.getB();
                break;
            case "resta":
                resultado = calculadora.getA() - calculadora.getB();
                break;
            case "multiplicacion":
                resultado = calculadora.getA() * calculadora.getB();
                break;
            case "division":
                resultado = calculadora.getA() / calculadora.getB();
                break;
        }
        calculadora.LimpiarCalculadora();
        calculadora.setA(resultado);
        redirectAttributes.addFlashAttribute("numero", resultado);
        return "redirect:/Calculadora/";
    }

    @GetMapping("/Borrar")
    public String Borrar(){
        calculadora.LimpiarCalculadora();
        return "redirect:/Calculadora/";
    }

}
