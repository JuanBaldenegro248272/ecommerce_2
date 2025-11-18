/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Producto;

/**
 *
 * @author jrasc
 */
public class AlbumMapper {

    public static AlbumDTO toDTO(Album album) {
        return new AlbumDTO(album.getId(),
                album.getNombre(),
                album.getDescripcion(),
                album.getFechaLanzamiento(),
                album.getImagenUrl(),
                album.getArtista().getId(),
                album.getCanciones(),
                album.getGeneros().stream().map(ga -> ga.getGenero().getId()).toList(),
                album.getProductos().stream().map(Producto::getId).toList());
    }

    public static Album toEntity(AlbumDTO dto) {
        Album album = new Album();
        album.setId(dto.getId());
        album.setNombre(dto.getNombre());
        album.setDescripcion(dto.getDescripcion());
        album.setFechaLanzamiento(dto.getFechaLanzamiento());
        album.setImagenUrl(dto.getImagenUrl());
        album.setCanciones(dto.getCanciones());
        return album;
    }

}
