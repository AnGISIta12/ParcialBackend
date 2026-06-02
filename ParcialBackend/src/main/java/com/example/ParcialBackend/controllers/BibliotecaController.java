package com.example.ParcialBackend.controllers;

import com.example.ParcialBackend.dtos.bibliotecaDTO;
import com.example.ParcialBackend.service.BibliotecaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
@AllArgsConstructor
public class BibliotecaController {
    
    private final BibliotecaService bibliotecaService;
    
    // Crear biblioteca
    @PostMapping
    public ResponseEntity<bibliotecaDTO> crear(@RequestBody bibliotecaDTO bibliotecaDTO) {
        bibliotecaDTO creada = bibliotecaService.crear(bibliotecaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    
    // listo todas las bibliotecas
    @GetMapping
    public ResponseEntity<List<bibliotecaDTO>> listarTodas() {
        List<bibliotecaDTO> bibliotecas = bibliotecaService.listarTodas();
        return ResponseEntity.ok(bibliotecas);
    }
    
    // por ide
    @GetMapping("/{id}")
    public ResponseEntity<bibliotecaDTO> obtenerPorId(@PathVariable Long id) {
        bibliotecaDTO biblioteca = bibliotecaService.obtenerPorId(id);
        return ResponseEntity.ok(biblioteca);
    }
    
    // actualizaaa
    @PutMapping("/{id}")
    public ResponseEntity<bibliotecaDTO> actualizar(
            @PathVariable Long id,
            @RequestBody bibliotecaDTO bibliotecaDTO) {
        bibliotecaDTO actualizada = bibliotecaService.actualizar(id, bibliotecaDTO);
        return ResponseEntity.ok(actualizada);
    }
    
    // eliminaa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bibliotecaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

