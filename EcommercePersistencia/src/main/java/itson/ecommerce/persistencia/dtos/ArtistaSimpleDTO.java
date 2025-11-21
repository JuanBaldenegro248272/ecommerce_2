/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

/**
 *
 * @author Dana Chavez
 */
public class ArtistaSimpleDTO {
    private Long id;
    private String nombreArtistico;

    public ArtistaSimpleDTO() {
    }

    public ArtistaSimpleDTO(Long id, String nombreArtistico) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }
}