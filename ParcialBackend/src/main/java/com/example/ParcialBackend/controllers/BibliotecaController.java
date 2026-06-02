package com.example.ParcialBackend.controllers;

import com.example.ParcialBackend.models.Biblioteca;
import com.example.ParcialBackend.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
@CrossOrigin(origins = "http://localhost:4200")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    // Crear
    @PostMapping
    public ResponseEntity<Biblioteca> crear(@RequestBody Biblioteca biblioteca) {
        Biblioteca nueva = bibliotecaService.crear(biblioteca);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Biblioteca>> listarTodos() {
        return ResponseEntity.ok(bibliotecaService.listarTodas());
    }

    // Consultar por id
    //GetMapping("/{id}")
    //public ResponseEntity<Biblioteca> buscarPorId(@PathVariable Long id) {
      //  return bibliotecaService.obtenerPorId(id) 
                //.map(ResponseEntity::ok)
                //.orElse(ResponseEntity.notFound().build()); 
    //}

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizar(@PathVariable Long id, @RequestBody Biblioteca datos) {
        try {
            Biblioteca actualizada = bibliotecaService.actualizar(id, datos);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bibliotecaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
