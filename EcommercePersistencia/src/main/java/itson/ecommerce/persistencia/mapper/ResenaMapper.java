/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.ResenaListaDTO;
import itson.ecommerce.persistencia.entidades.Resena;

public class ResenaMapper {
    
    public static ResenaListaDTO toListaDTO(Resena resena) {
        ResenaListaDTO dto = new ResenaListaDTO();
        
        dto.setId(resena.getId());
        dto.setComentario(resena.getComentario());
        dto.setCalificacion(resena.getCalificacion());
        dto.setEstado(resena.getEstado() != null ? resena.getEstado().name() : null);

        if (resena.getCliente() != null) {
            dto.setClienteId(resena.getCliente().getId());
            dto.setClienteNombre(resena.getCliente().getNombre());
        }

        if (resena.getProducto() != null) {
            dto.setProductoId(resena.getProducto().getId());
            
            if (resena.getProducto().getAlbum() != null) {
                dto.setAlbumNombre(resena.getProducto().getAlbum().getNombre());
                
                if (resena.getProducto().getAlbum().getArtista() != null) {
                    dto.setArtistaNombre(resena.getProducto().getAlbum().getArtista().getNombreArtistico());
                }
            }
        }
        
        return dto;
    }
}