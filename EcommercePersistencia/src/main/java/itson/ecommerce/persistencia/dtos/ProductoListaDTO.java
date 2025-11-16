/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public class ProductoListaDTO {
    private Long idProducto;
    private String formato;
    private Float precio;
    private Integer stock;
    private Boolean esDisponible;
    private String descripcion;
    
    // Datos del álbum
    private Long albumId;
    private String albumNombre;
    private String albumImagenUrl;
    
    // Datos del artista
    private String artistaNombre;
    
    // Géneros (solo los nombres)
    private List<String> generos;

    public ProductoListaDTO() {
    }

    public ProductoListaDTO(Long idProducto, String formato, Float precio, Integer stock, Boolean esDisponible, String descripcion, Long albumId, String albumNombre, String albumImagenUrl, String artistaNombre, List<String> generos) {
        this.idProducto = idProducto;
        this.formato = formato;
        this.precio = precio;
        this.stock = stock;
        this.esDisponible = esDisponible;
        this.descripcion = descripcion;
        this.albumId = albumId;
        this.albumNombre = albumNombre;
        this.albumImagenUrl = albumImagenUrl;
        this.artistaNombre = artistaNombre;
        this.generos = generos;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getEsDisponible() {
        return esDisponible;
    }

    public void setEsDisponible(Boolean esDisponible) {
        this.esDisponible = esDisponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumNombre() {
        return albumNombre;
    }

    public void setAlbumNombre(String albumNombre) {
        this.albumNombre = albumNombre;
    }

    public String getAlbumImagenUrl() {
        return albumImagenUrl;
    }

    public void setAlbumImagenUrl(String albumImagenUrl) {
        this.albumImagenUrl = albumImagenUrl;
    }

    public String getArtistaNombre() {
        return artistaNombre;
    }

    public void setArtistaNombre(String artistaNombre) {
        this.artistaNombre = artistaNombre;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    
}
