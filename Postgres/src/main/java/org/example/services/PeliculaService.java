package org.example.services;

import org.example.models.Pelicula;
import org.example.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
    private final PeliculaRepository peliculaRepository;
    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository = peliculaRepository;
    }

    public Optional<Pelicula> getPelibyId(Long id){
        return peliculaRepository.findById(id);
    }

    public Optional<Pelicula> getPelibyName(String name){
        return peliculaRepository.findByTitulo(name);
    }

    public Optional<List<Pelicula>> getAllPelis(){
        return Optional.of(peliculaRepository.findAll());
    }

    public Optional<Pelicula> savePeli (Pelicula pelicula){
        return Optional.of(peliculaRepository.save(pelicula));
    }

    public  Optional<Pelicula> deletePeli(Long id){
        Optional<Pelicula> eliminada = peliculaRepository.findById(id);
        eliminada.ifPresent(peliculaRepository::delete);
        return eliminada;
    }

    public  Optional<Pelicula> deletePeli(Pelicula id){
        Optional<Pelicula> eliminada = peliculaRepository.findById(id.getId_pelicula());
        eliminada.ifPresent(peliculaRepository::delete);
        return eliminada;
    }

}
