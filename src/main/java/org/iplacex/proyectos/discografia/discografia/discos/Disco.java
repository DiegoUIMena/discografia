package org.iplacex.proyectos.discografia.discografia.discos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("discos")
public class Disco {
    @Id
    public String _id;
    public String idArtita; 
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;

}