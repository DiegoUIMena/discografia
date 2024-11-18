package org.iplacex.proyectos.discografia.discografia.discos;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface IDiscoRepository extends MongoRepository<Disco, String> {
    
    // Firma del método personalizado para buscar discos por el ID del artista
    
    @Query("{ 'idArtista': ?0 }")
    List<Disco> findDiscosByIdArtista(String idArtista);
}