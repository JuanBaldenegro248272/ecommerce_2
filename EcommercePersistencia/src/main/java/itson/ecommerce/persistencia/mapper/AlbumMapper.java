/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.entidades.Genero;
import itson.ecommerce.persistencia.entidades.GeneroAlbum;
import itson.ecommerce.persistencia.entidades.Producto;
import java.util.ArrayList;
import java.util.List;

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
                album.getArtista().getNombreArtistico(),
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
        if (dto.getIdArtista() != null) {
            Artista artistaReferencia = new Artista();
            artistaReferencia.setId(dto.getIdArtista());
            album.setArtista(artistaReferencia);
        }
        if (dto.getIdGeneros() != null) {
            List<GeneroAlbum> generosAlbum = dto.getIdGeneros().stream()
                    .map(idGenero -> {
                        Genero generoRef = new Genero();
                        generoRef.setId(idGenero);
                        GeneroAlbum ga = new GeneroAlbum();
                        ga.setAlbum(album);
                        ga.setGenero(generoRef);
                        return ga;
                    }).toList();
            album.setGeneros(generosAlbum);
        } else {
            album.setGeneros(new ArrayList<>());
        }
        if (dto.getIdProductos() != null) {
            List<Producto> productosReferencia = dto.getIdProductos().stream()
                    .map(idProducto -> {
                        Producto productoRef = new Producto();
                        productoRef.setId(idProducto);
                        return productoRef;
                    }).toList();
            album.setProductos(productosReferencia);
        } else {
            album.setProductos(new ArrayList<>());
        }
        return album;
    }

}
