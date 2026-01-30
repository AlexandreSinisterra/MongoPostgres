package org.example.repository;

import org.example.models.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeliculaRepository extends MongoRepository<Pelicula,String> {
}
