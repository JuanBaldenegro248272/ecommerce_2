/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

public class ResenaListaDTO {
    private Long id;
    private String comentario;
    private Integer calificacion;
    private String estado; 

    private Long clienteId;
    private String clienteNombre;

    private Long productoId;
    private String albumNombre;
    private String artistaNombre;

    public ResenaListaDTO() {
    }

    public ResenaListaDTO(Long id, String comentario, Integer calificacion, String estado, 
                         Long clienteId, String clienteNombre, Long productoId, 
                         String albumNombre, String artistaNombre) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.estado = estado;
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.productoId = productoId;
        this.albumNombre = albumNombre;
        this.artistaNombre = artistaNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getAlbumNombre() {
        return albumNombre;
    }

    public void setAlbumNombre(String albumNombre) {
        this.albumNombre = albumNombre;
    }

    public String getArtistaNombre() {
        return artistaNombre;
    }

    public void setArtistaNombre(String artistaNombre) {
        this.artistaNombre = artistaNombre;
    }
}
