/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.FormatoProducto;
import itson.ecommerce.persistencia.entidades.Producto;

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
}
