package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "peliculas")
public class Pelicula {
    @Id
    private Long id_peli;
    private String titulo;
    private String xenero;
    private Long ano;
    private List<Actor> actores = new ArrayList<>();
}
