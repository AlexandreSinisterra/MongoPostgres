package org.example.controllers;

import org.example.models.Pelicula;
import org.example.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/postgres/peli")
public class RestPelicula {
    private final PeliculaService peliculaService;
    @Autowired
    public RestPelicula(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPelibyId (@PathVariable Long id){
        return peliculaService.getPelibyId(id).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @GetMapping("/buscarTitulo/{titulo}")
    public ResponseEntity<Pelicula> getPelibyNome (@PathVariable String tiulo){
        return peliculaService.getPelibyName(tiulo).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/TodasPelis")
    public ResponseEntity<List<Pelicula>> getAllPelis (){
        return peliculaService.getAllPelis().map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PostMapping("/savePeli")
    public ResponseEntity<Pelicula> savePeli (@PathVariable Pelicula pelicula){
        return peliculaService.savePeli(pelicula).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pelicula> deletePelibyId (@PathVariable Long id){
        return peliculaService.deletePeli(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/deletePeli")
    public ResponseEntity<Pelicula> deletePeli (@PathVariable Pelicula pelicula){
        return peliculaService.deletePeli(pelicula).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}