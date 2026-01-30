package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Actor;
import org.example.models.Pelicula;
import org.example.service.ConexionService;
import org.example.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class Secuencia {
    private final ConexionService conexionService;
    private final PeliculaService peliculaService;

    @Autowired
    public Secuencia (ConexionService conexionService, PeliculaService peliculaService){
        this.conexionService = conexionService;
        this.peliculaService= peliculaService;

    }

    public void ejecutarSecuencia() {
        ObjectMapper mapper = new ObjectMapper();

        Actor actor = new Actor();
        actor.setNome("Actor 1");
        actor.setApelidos("Apellido 1");
        conexionService.crearActor(actor);

        Actor actor2 = new Actor();
        actor2.setNome("Actor 2");
        actor2.setApelidos("Apellido 2");
        conexionService.crearActor(actor2);

        List<Actor> actores = conexionService.obtenerTodosActores();

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Pelicula 1");
        pelicula1.setActores(actores);
        pelicula1.setAno(2029L);
        conexionService.crearPelicula(pelicula1);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Pelicula 2");
        pelicula2.setAno(201L);

        Actor actor3 = new Actor();
        actor3.setNome("Actor 3");
        actor3.setApelidos("Apellido 3");
        conexionService.crearActor(actor3);

        List<Actor> actores2 = new ArrayList<>();
        actores2.add(actores.getLast());

        pelicula2.setActores(actores2);
        conexionService.crearPelicula(pelicula1);

        Pelicula obtenidaTitulo = conexionService.obtenerPeliculaPorTitulo("Pelicula 2");
        Pelicula especifica = conexionService.obtenerPeliculaPorID(1L);

        peliculaService.crearPelicula(obtenidaTitulo);

        peliculaService.crearPelicula(especifica);

        List<Pelicula> peliculas = peliculaService.obtenerTodasPeliculas();

        try (PrintWriter writer = new PrintWriter("datos.json")) {
            writer.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(peliculas));
        } catch (FileNotFoundException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println("Fallo al procesar el JSON de las narices");
        }


    }


}
