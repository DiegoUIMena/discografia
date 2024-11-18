package org.iplacex.proyectos.discografia.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo; 
    
    @Autowired
    private IArtistaRepository artistaRepo;



    // Método para insertar un nuevo disco
    @PostMapping(
        value = "/disco", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
        )

    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
        Disco tempDisco = discoRepo.insert(disco);
        return new ResponseEntity<>(tempDisco, null, HttpStatus.CREATED);
    }


    // Método para obtener todos los discos
    @GetMapping(
        value = "/discos", 
        produces = MediaType.APPLICATION_JSON_VALUE
        )

    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepo.findAll();
        return new ResponseEntity<>(discos,null, HttpStatus.OK);
    }


    // Método para obtener un disco por su ID
    @GetMapping(
        value = "/disco/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE
        )

    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> tempDisco = discoRepo.findById(id);
        if (!tempDisco.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tempDisco.get(), null, HttpStatus.OK);
    }


    // Método para obtener todos los discos de un artista por su ID
    @GetMapping(
        value = "/artista/{id}/discos", 
        produces = MediaType.APPLICATION_JSON_VALUE
        )

    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        List<Disco> discos = discoRepo.findDiscosByIdArtista(idArtista);
        if (!discos.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
