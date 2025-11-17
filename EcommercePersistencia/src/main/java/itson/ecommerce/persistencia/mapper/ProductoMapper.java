/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.EditarProductoDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.FormatoProducto;
import itson.ecommerce.persistencia.entidades.Producto;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dana Chavez
 */
public final class ProductoMapper {

    private ProductoMapper() {}
   
    public static Producto toEntity(NuevoProductoDTO dto, Album album) {
        if (dto == null) return null;

        Producto p = new Producto();
        p.setAlbum(album);

        // Formato: normalizar en mayúsculas y sin espacios
        if (dto.getFormato() != null) {
            String fmt = dto.getFormato().trim().toUpperCase();
            try {
                p.setFormato(FormatoProducto.valueOf(fmt));
            } catch (IllegalArgumentException ex) {
                // dejar null y que la llamada superior maneje invalid format
                p.setFormato(null);
            }
        } else {
            p.setFormato(null);
        }

        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setEsDisponible(dto.getEsDisponible());
        p.setDescripcion(dto.getDescripcion());

        return p;
    }

    public static NuevoProductoDTO toDto(Producto p) {
        if (p == null) return null;

        NuevoProductoDTO dto = new NuevoProductoDTO();
        if (p.getAlbum() != null) {
            dto.setAlbumId(p.getAlbum().getId());
        }
        dto.setFormato(p.getFormato() != null ? p.getFormato().name() : null);
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        dto.setEsDisponible(p.getEsDisponible());
        dto.setDescripcion(p.getDescripcion());

        return dto;
    }
    
    public static ProductoListaDTO toListaDTO(Producto producto) {
        ProductoListaDTO dto = new ProductoListaDTO();
        
        dto.setIdProducto(producto.getId());
        dto.setFormato(producto.getFormato() != null ? producto.getFormato().name() : null);
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setEsDisponible(producto.getEsDisponible());
        dto.setDescripcion(producto.getDescripcion());
        
        // Datos del álbum
        if (producto.getAlbum() != null) {
            Album album = producto.getAlbum();
            dto.setAlbumId(album.getId());
            dto.setAlbumNombre(album.getNombre());
            dto.setAlbumImagenUrl(album.getImagenUrl());
            
            // Datos del artista
            if (album.getArtista() != null) {
                dto.setArtistaNombre(album.getArtista().getNombreArtistico());
            }
            
            // Géneros (solo los nombres)
            if (album.getGeneros() != null && !album.getGeneros().isEmpty()) {
                List<String> generos = album.getGeneros().stream()
                    .map(g -> g.getGenero() != null ? g.getGenero().getNombre() : null)
                    .filter(nombre -> nombre != null)
                    .collect(Collectors.toList());
                dto.setGeneros(generos);
            }
        }
        
        return dto;
    }
    
    public static EditarProductoDTO toEditarDTO(Producto producto) {
        EditarProductoDTO dto = new EditarProductoDTO();
        
        dto.setId(producto.getId());
        dto.setFormato(producto.getFormato() != null ? producto.getFormato().name() : null);
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setEsDisponible(producto.getEsDisponible());
        dto.setDescripcion(producto.getDescripcion());

        if (producto.getAlbum() != null) {
            Album album = producto.getAlbum();
            dto.setAlbumId(album.getId());
            dto.setAlbumNombre(album.getNombre());
            dto.setAlbumImagenUrl(album.getImagenUrl());

            if (album.getArtista() != null) {
                dto.setArtistaNombre(album.getArtista().getNombreArtistico());
            }
        }
        
        return dto;
    }

    public static void updateEntity(Producto producto, EditarProductoDTO dto) {
        if (dto.getPrecio() != null) {
            producto.setPrecio(dto.getPrecio());
        }
        if (dto.getStock() != null) {
            producto.setStock(dto.getStock());
        }
        if (dto.getEsDisponible() != null) {
            producto.setEsDisponible(dto.getEsDisponible());
        }
        if (dto.getDescripcion() != null) {
            producto.setDescripcion(dto.getDescripcion());
        }
        if (dto.getFormato() != null) {
            try {
                producto.setFormato(FormatoProducto.valueOf(dto.getFormato()));
            } catch (IllegalArgumentException e) {

            }
        }
    }
}
