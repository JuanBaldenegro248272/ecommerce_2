/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

/**
 *
 * @author Dana Chavez
 */
public class NuevaResenaDTO {

    private Long idCliente;
    private Long idProducto; 
    private Integer calificacion;
    private String comentario;

    public NuevaResenaDTO() {
    }
    
    public NuevaResenaDTO(Long idCliente, Long idProducto, Integer calificacion, String comentario) {
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}