/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jrasc
 */
public class AlbumDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Calendar fechaLanzamiento;
    private String imagenUrl;
    private Long idArtista;
    private String nombreArtista;
    private List<String> canciones;
    private List<Long> idGeneros;
    private List<Long> idProductos;

    public AlbumDTO(Long id, String nombre, String descripcion, Calendar fechaLanzamiento, String imagenUrl, Long idArtista, String nombreArtista, List<String> canciones, List<Long> idGeneros, List<Long> idProductos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagenUrl = imagenUrl;
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.canciones = canciones;
        this.idGeneros = idGeneros;
        this.idProductos = idProductos;
    }

    public AlbumDTO(String nombre, String descripcion, Calendar fechaLanzamiento, String imagenUrl, Long idArtista, String nombreArtista, List<String> canciones, List<Long> idGeneros, List<Long> idProductos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagenUrl = imagenUrl;
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.canciones = canciones;
        this.idGeneros = idGeneros;
        this.idProductos = idProductos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calendar getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Calendar fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Long getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Long idArtista) {
        this.idArtista = idArtista;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    public List<Long> getIdGeneros() {
        return idGeneros;
    }

    public void setIdGeneros(List<Long> idGeneros) {
        this.idGeneros = idGeneros;
    }

    public List<Long> getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(List<Long> idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

}
