/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

/**
 *
 * @author Gael
 */
public class UsuarioDTO {
    
    private Long id;
    private String nombre;
    private String correoElectronico;
    private Boolean esActiva;
    private String rol;

    public UsuarioDTO() {
    }

    
    public UsuarioDTO(Long id, String nombre, String correoElectronico, Boolean esActiva) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.esActiva = esActiva;
    }

    public Long getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Boolean getEsActiva() {
        return esActiva;
    }

    public void setEsActiva(Boolean esActiva) {
        this.esActiva = esActiva;
    }

    
}
