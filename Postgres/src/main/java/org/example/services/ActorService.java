package org.example.services;

import org.example.models.Actor;
import org.example.models.Pelicula;
import org.example.repository.ActorRepository;
import org.example.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final PeliculaRepository peliculaRepository;
    @Autowired
    public ActorService(ActorRepository actorRepository,PeliculaRepository peliculaRepository) {
        this.actorRepository = actorRepository;
        this.peliculaRepository = peliculaRepository;
    }
    public Optional<Actor> obtenerActorPorId(Long id) {
        return actorRepository.findById(id);
    }
    public Optional<Actor> guardarActor(Actor actor) {
        if (actor.getPelicula()!=null) {
            Optional<Pelicula> peliObtida = peliculaRepository.findById(actor.getPelicula().getId_pelicula());
            peliObtida.ifPresent(actor::setPelicula);
        }
        Actor actorGuardado = actorRepository.save(actor);
        return Optional.of(actorGuardado);
    }
    public Optional<Actor> eliminarActor(Long id) {
        Optional<Actor> actorEliminar = actorRepository.findById(id);
        actorEliminar.ifPresent(actorRepository::delete);
        return actorEliminar;
    }
    public Optional<Actor> eliminarActor(Actor actor) {
        Optional<Actor> actorEliminar = actorRepository.findById(actor.getId_actor());
        actorEliminar.ifPresent(actorRepository::delete);
        return actorEliminar;
    }
    public Optional<List<Actor>> obtenerTodosActores() {
        return Optional.of(actorRepository.findAll());
    }
}