package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "actores")
public class Actor {
    @Id
    private Long id_actor;
    private String nome;
    private String apelidos;
    private String Nacionalidade;
    private Pelicula pelicula;
}
