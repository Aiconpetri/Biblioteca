package com.aicon.biblioteca.controladores;

import com.aicon.biblioteca.excepciones.MiException;
import com.aicon.biblioteca.services.autorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    @Autowired
    private autorServicio AutorServicio;
    
    @GetMapping("/autorHome")
    public String autoria(){
        return "autor_home.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        try {
            AutorServicio.crearAutor(nombre);
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }
         return "autor_home.html";
    }
}
