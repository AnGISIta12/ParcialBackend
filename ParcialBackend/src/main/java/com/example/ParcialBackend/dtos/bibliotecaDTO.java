package com.example.ParcialBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class bibliotecaDTO {
    
    private Long id;
    private String nombre;
    private List<libroDTO> libros;
}


