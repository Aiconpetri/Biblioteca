package com.aicon.biblioteca.services;

import com.aicon.biblioteca.entidades.Autor;
import com.aicon.biblioteca.entidades.Editorial;
import com.aicon.biblioteca.entidades.Libro;
import com.aicon.biblioteca.excepciones.MiException;
import com.aicon.biblioteca.repositorio.autorRepo;
import com.aicon.biblioteca.repositorio.editorialRepo;
import com.aicon.biblioteca.repositorio.libroRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class libroServicio {

    @Autowired
    private libroRepo libroRepositorio;

    @Autowired
    private editorialRepo editorialRepositorio;

    @Autowired
    private autorRepo autorRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, String autorId, String editorialId, Integer ejemplares) throws MiException {

        validar(isbn, titulo, autorId, editorialId, ejemplares);

        Autor a1 = autorRepositorio.findById(autorId).get();

        Libro l1 = new Libro();

        Editorial e1 = editorialRepositorio.findById(editorialId).get();

        l1.setIsbn(isbn);
        l1.setTitulo(titulo);
        l1.setEjemplares(ejemplares);

        l1.setAlta(new Date());

        l1.setAutor(a1);

        l1.setEditorial(e1);

        libroRepositorio.save(l1);
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<>();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String autorId, String editorialId, Integer ejemplares) throws MiException {

        validar(isbn, titulo, autorId, editorialId, ejemplares);

        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);

        Optional<Autor> respuestaAutor = autorRepositorio.findById(autorId);

        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(editorialId);

        Autor a1 = new Autor();

        Editorial e1 = new Editorial();

        if (respuestaAutor.isPresent()) {

            a1 = respuestaAutor.get();

        }
        if (respuestaEditorial.isPresent()) {

            e1 = respuestaEditorial.get();

        }
        if (respuestaLibro.isPresent()) {
            Libro l1 = respuestaLibro.get();

            l1.setTitulo(titulo);

            l1.setAutor(a1);

            l1.setEditorial(e1);

            l1.setEjemplares(ejemplares);

            libroRepositorio.save(l1);
        }

    }

    public void validar(Long isbn, String titulo, String autorId, String editorialId, Integer ejemplares) throws MiException {

        if (isbn == null) {
            throw new MiException("El ISBN no puede estar vacio");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
        if (autorId == null || autorId.isEmpty()) {
            throw new MiException("El id del autor no puede estar vacio o ser nulo");
        }
        if (editorialId == null || editorialId.isEmpty()) {
            throw new MiException("El id de la editorial no puede ser nulo o estar vacio");
        }
        if (ejemplares == null) {
            throw new MiException("El numero de ejmplares no puede ser nulo");
        }
    }
}
