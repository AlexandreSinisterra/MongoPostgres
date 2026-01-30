package org.example.controllers;

import org.example.models.Actor;
import org.example.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postgres/actor")
public class RestActor {
    private final ActorService actorService;
    @Autowired
    public RestActor(ActorService actorService) {
        this.actorService = actorService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Actor> obtenerActorPorID(@PathVariable Long id) {
        return actorService.obtenerActorPorId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/guardarActor")
    public ResponseEntity<Actor> guardarActor(@RequestBody Actor actor) {
        return actorService.guardarActor(actor).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/eliminarActor/{id}")
    public ResponseEntity<Actor> eliminarActorPorID(@PathVariable Long id) {
        return actorService.eliminarActor(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/eliminarActor")
    public ResponseEntity<Actor> eliminarActor(@RequestBody Actor actor) {
        return actorService.eliminarActor(actor).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/obtenerTodosActores")
    public ResponseEntity<List<Actor>> obtenerTodosActores() {
        return actorService.obtenerTodosActores().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}