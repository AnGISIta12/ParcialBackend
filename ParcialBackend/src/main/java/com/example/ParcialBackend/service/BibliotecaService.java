package com.example.ParcialBackend.service;

import com.example.ParcialBackend.models.Biblioteca;
import com.example.ParcialBackend.repository.BibliotecaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BibliotecaService {
    
    private final BibliotecaRepository bibliotecaRepo;
    
    public Biblioteca crear(Biblioteca biblioteca) {
        return bibliotecaRepo.save(biblioteca);
    }
    
    // listar bibliotecas
    public List<Biblioteca> listarTodas() {
        return bibliotecaRepo.findAll();
    }
    
    // Obtener biblioteca por ID
    public Biblioteca obtenerPorId(Long id) {
        return bibliotecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada con ID: " + id));
    }
    
    // actualizaaa
    public Biblioteca actualizar(Long id, Biblioteca bibliotecaDTO) {
        Biblioteca biblioteca = bibliotecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada con ID: " + id));
        biblioteca.setNombre(bibliotecaDTO.getNombre());
        return bibliotecaRepo.save(biblioteca);
    }
    
    // eliminar
    public void eliminar(Long id) {
        if (!bibliotecaRepo.existsById(id)) {
            throw new RuntimeException("Biblioteca no encontrada con ID: " + id);
        }
        bibliotecaRepo.deleteById(id);
    }
}

