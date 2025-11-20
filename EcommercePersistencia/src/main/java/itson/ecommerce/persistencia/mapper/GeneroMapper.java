/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.entidades.Genero;

/**
 *
 * @author Dana Chavez
 */
public class GeneroMapper {
    
    public static GeneroDTO toDTO(Genero genero) {
        GeneroDTO dto = new GeneroDTO();
        
        dto.setId(genero.getId());
        dto.setNombre(genero.getNombre());
        
        if (genero.getGenerosAlbum() != null) {
            dto.setCantidadAlbums(genero.getGenerosAlbum().size());
        } else {
            dto.setCantidadAlbums(0);
        }
        
        return dto;
    }    
}
