/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

/**
 *
 * @author Dana Chavez
 */
public class NuevoProductoDTO {
    private Long albumId;
    private String formato;        
    private Float precio;
    private Integer stock;
    private Boolean esDisponible;
    private String descripcion;

    public NuevoProductoDTO() {
    }

    public NuevoProductoDTO(Long albumId, String formato, Float precio, Integer stock, Boolean esDisponible, String descripcion) {
        this.albumId = albumId;
        this.formato = formato;
        this.precio = precio;
        this.stock = stock;
        this.esDisponible = esDisponible;
        this.descripcion = descripcion;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
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



}
