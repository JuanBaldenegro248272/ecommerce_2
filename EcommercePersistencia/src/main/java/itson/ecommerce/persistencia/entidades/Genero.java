/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dana Chavez
 */
@Entity
@Table (name = "generos")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    
    @Column (name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @OneToMany(mappedBy = "genero")
    private List<GeneroAlbum> generosAlbum;

    public Genero() {
    }

    public Genero(Long id, String nombre, List<GeneroAlbum> generosAlbum) {
        this.id = id;
        this.nombre = nombre;
        this.generosAlbum = generosAlbum;
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

    public List<GeneroAlbum> getGenerosAlbum() {
        return generosAlbum;
    }

    public void setGenerosAlbum(List<GeneroAlbum> generosAlbum) {
        this.generosAlbum = generosAlbum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.ecommerce.persistencia.entidades.Genero[ id=" + id + " ]";
    }
    
}
