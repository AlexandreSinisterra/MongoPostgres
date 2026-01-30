package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actores")
public class Actor {
    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_actor;
    private String nome;
    private String apelidos;
    private String nacionalidade;
    @ManyToOne
    private Pelicula pelicula;
}
