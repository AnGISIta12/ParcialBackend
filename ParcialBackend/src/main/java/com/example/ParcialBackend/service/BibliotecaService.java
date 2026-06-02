package com.example.ParcialBackend.service;

import com.example.ParcialBackend.dtos.bibliotecaDTO;
import com.example.ParcialBackend.dtos.libroDTO;
import com.example.ParcialBackend.models.Biblioteca;
import com.example.ParcialBackend.models.Libro;
import com.example.ParcialBackend.repository.BibliotecaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BibliotecaService {
    
    private final BibliotecaRepo bibliotecaRepo;
    
    public bibliotecaDTO crear(bibliotecaDTO bibliotecaDTO) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre(bibliotecaDTO.getNombre());
        Biblioteca guardada = bibliotecaRepo.save(biblioteca);
        return convertirADTO(guardada);
    }
    
    // listar bibliotecas
    public List<bibliotecaDTO> listarTodas() {
        return bibliotecaRepo.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    // Obtener biblioteca por ID
    public bibliotecaDTO obtenerPorId(Long id) {
        Biblioteca biblioteca = bibliotecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada con ID: " + id));
        return convertirADTO(biblioteca);
    }
    
    // actualizaaa
    public bibliotecaDTO actualizar(Long id, bibliotecaDTO bibliotecaDTO) {
        Biblioteca biblioteca = bibliotecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada con ID: " + id));
        biblioteca.setNombre(bibliotecaDTO.getNombre());
        Biblioteca actualizada = bibliotecaRepo.save(biblioteca);
        return convertirADTO(actualizada);
    }
    
    // eliminar
    public void eliminar(Long id) {
        if (!bibliotecaRepo.existsById(id)) {
            throw new RuntimeException("Biblioteca no encontrada con ID: " + id);
        }
        bibliotecaRepo.deleteById(id);
    }
    
    // Método auxiliar para convertir Biblioteca a bibliotecaDTO
    private bibliotecaDTO convertirADTO(Biblioteca biblioteca) {
        bibliotecaDTO dto = new bibliotecaDTO();
        dto.setId(biblioteca.getId());
        dto.setNombre(biblioteca.getNombre());
        
        if (biblioteca.getLibros() != null) {
            dto.setLibros(biblioteca.getLibros()
                    .stream()
                    .map(this::convertirLibroADTO)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    // Método auxiliar para convertir Libro a libroDTO
    private libroDTO convertirLibroADTO(Libro libro) {
        libroDTO dto = new libroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setCategoria(libro.getCategoria());
        dto.setBibliotecaId(libro.getBiblioteca().getId());
        return dto;
    }
}

