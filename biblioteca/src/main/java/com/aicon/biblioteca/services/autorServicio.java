package com.aicon.biblioteca.services;

import com.aicon.biblioteca.entidades.Autor;
import com.aicon.biblioteca.excepciones.MiException;
import com.aicon.biblioteca.repositorio.autorRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class autorServicio {

    @Autowired
    autorRepo autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar(nombre, nombre);

        Autor a1 = new Autor();

        a1.setNombre(nombre);

        autorRepositorio.save(a1);
    }

    public List<Autor> listarLibros() {

        List<Autor> autores = new ArrayList<>();

        autores = autorRepositorio.findAll();

        return autores;
    }

    public void modificarAutor(String id, String nombre) throws MiException {

        validar(id, nombre);

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Autor a1 = respuesta.get();

            a1.setNombre(nombre);

            autorRepositorio.save(a1);

        }

    }

    public void validar(String id, String nombre) throws MiException {

        if (id == null || id.isEmpty()) {

            throw new MiException("El id del autor no puede estar vacio o ser nulo");

        }
        if (nombre == null || nombre.isEmpty()) {

            throw new MiException("El nombre del autor no puede estar vacio o ser nulo");

        }
    }
}
