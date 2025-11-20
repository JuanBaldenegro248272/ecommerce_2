/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

/**
 *
 * @author Dana Chavez
 */
public class GeneroDTO {
    private Long id;
    private String nombre;
    private Integer cantidadAlbums; 

    public GeneroDTO() {
    }

    public GeneroDTO(Long id, String nombre, Integer cantidadAlbums) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadAlbums = cantidadAlbums;
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

    public Integer getCantidadAlbums() {
        return cantidadAlbums;
    }

    public void setCantidadAlbums(Integer cantidadAlbums) {
        this.cantidadAlbums = cantidadAlbums;
    }
    
}
