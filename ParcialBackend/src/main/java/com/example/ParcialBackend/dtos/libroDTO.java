package com.example.ParcialBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class libroDTO {
    
    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private Long bibliotecaId;
}
