package com.aicon.biblioteca.services;

import com.aicon.biblioteca.entidades.Editorial;
import com.aicon.biblioteca.excepciones.MiException;
import com.aicon.biblioteca.repositorio.editorialRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class editorialServicio {

    @Autowired
    editorialRepo editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        
        validar(nombre, nombre);
        
        Editorial e1 = new Editorial();

        e1.setNombre(nombre);

        editorialRepositorio.save(e1);
    }

    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList<>();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }


    public void modificarEditorial(String id, String nombre) throws MiException {

        validar(id, nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Editorial e1 = respuesta.get();

            e1.setNombre(nombre);

            editorialRepositorio.save(e1);

        }
    }

    public void validar(String id, String nombre) throws MiException {

        if (id == null || id.isEmpty()) {

            throw new MiException("El id de la editorial no puede estar vacio o ser nulo");

        }
        if (nombre == null || nombre.isEmpty()) {

            throw new MiException("El nombre de la editorial no puede estar vacio o ser nulo");

        }
    }
}
