package org.example.service;

import org.example.models.Pelicula;
import org.example.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {
    private final PeliculaRepository peliculaRepository;
    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository = peliculaRepository;
    }
    public void crearPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
    }
    public void eliminarPelicula(String id) {
        peliculaRepository.deleteById(id);
    }
    public List<Pelicula> obtenerTodasPeliculas() {
        return peliculaRepository.findAll();
    }
}
