package org.example.service;

import org.example.models.Actor;
import org.example.models.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionService {
    @Autowired
    private RestTemplate restTemplate;

    private final String POSTGRES_PELICULAS_URL = "http://localhost:8081/postgres/peli/";
    private final String POSTGRES_ACTORES_URL = "http://localhost:8081/postgres/actor/";

    // ---------------- ACTORES ----------------
    public List<Actor> obtenerTodosActores() {
        try {
            String url = POSTGRES_ACTORES_URL + "obtenerTodosActores";
            ResponseEntity<List<Actor>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Actor>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener actores: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return Collections.emptyList();
        }
    }

    public Actor obtenerActorPorID(Long id) {
        try {
            String url = POSTGRES_ACTORES_URL + "eliminarActor/" + id; // DELETE actor ID correcto
            ResponseEntity<Actor> response = restTemplate.exchange(
                    POSTGRES_ACTORES_URL + id,
                    HttpMethod.GET,
                    null,
                    Actor.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener actor por ID: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public Actor crearActor(Actor actor) {
        try {
            String url = POSTGRES_ACTORES_URL + "guardarActor";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Actor> request = new HttpEntity<>(actor, headers);
            ResponseEntity<Actor> response = restTemplate.exchange(url, HttpMethod.POST, request, Actor.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al crear actor: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    // ---------------- PELICULAS ----------------
    public Pelicula obtenerPeliculaPorID(Long id) {
        try {
            String url = POSTGRES_PELICULAS_URL + id;
            ResponseEntity<Pelicula> response = restTemplate.exchange(url, HttpMethod.GET, null, Pelicula.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener película por ID: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public Pelicula obtenerPeliculaPorTitulo(String titulo) {
        try {
            String url = POSTGRES_PELICULAS_URL + "buscarTitulo/" + titulo;
            ResponseEntity<Pelicula> response = restTemplate.exchange(url, HttpMethod.GET, null, Pelicula.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener película por título: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public List<Pelicula> obtenerTodasPeliculas() {
        try {
            String url = POSTGRES_PELICULAS_URL + "TodasPelis";
            ResponseEntity<List<Pelicula>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Pelicula>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener películas: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return Collections.emptyList();
        }
    }

    public Pelicula crearPelicula(Pelicula pelicula) {
        try {
            String url = POSTGRES_PELICULAS_URL + "savePeli"; // coincide con el controller
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Pelicula> request = new HttpEntity<>(pelicula, headers);
            ResponseEntity<Pelicula> response = restTemplate.exchange(url, HttpMethod.POST, request, Pelicula.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al crear película: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public boolean eliminarActorPorID(Long id) {
        try {
            String url = POSTGRES_ACTORES_URL + "eliminarActor/" + id;
            restTemplate.delete(url);
            return true;
        } catch (HttpClientErrorException e) {
            System.err.println("Error al eliminar actor: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return false;
        }
    }

    public boolean eliminarPeliculaPorID(Long id) {
        try {
            String url = POSTGRES_PELICULAS_URL + "delete/" + id;
            restTemplate.delete(url);
            return true;
        } catch (HttpClientErrorException e) {
            System.err.println("Error al eliminar película: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return false;
        }
    }
}
