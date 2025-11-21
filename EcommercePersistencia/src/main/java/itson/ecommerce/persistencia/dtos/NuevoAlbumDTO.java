/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public class NuevoAlbumDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Calendar fechaLanzamiento;
    private String imagenUrl;
    private Long idArtista;
    private List<Long> idsGeneros; 
    private List<String> canciones; 

    public NuevoAlbumDTO() {
    }

    public NuevoAlbumDTO(Long id, String nombre, String descripcion, Calendar fechaLanzamiento, 
                        String imagenUrl, Long idArtista, List<Long> idsGeneros, List<String> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagenUrl = imagenUrl;
        this.idArtista = idArtista;
        this.idsGeneros = idsGeneros;
        this.canciones = canciones;
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

    public List<Long> getIdsGeneros() {
        return idsGeneros;
    }

    public void setIdsGeneros(List<Long> idsGeneros) {
        this.idsGeneros = idsGeneros;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }
}
