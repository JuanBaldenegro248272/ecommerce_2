/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dana Chavez
 */
@Entity
@Table (name = "albums")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    
    @Column (name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Column (name = "descripcion", length = 255)
    private String descripcion;
    
    @Column (name = "fecha_lanzamiento", nullable = false)
    private Calendar fechaLanzamiento;
    
    @Column (name = "imagen_url", length = 255, nullable = false)
    private String imagenUrl;
    
    @ManyToOne
    @JoinColumn (name = "id_artista", nullable = false)
    private Artista artista;
    
    @ElementCollection
    @CollectionTable(name = "canciones", joinColumns = @JoinColumn(name = "id_album"))
    @Column(name = "cancion", length = 20, nullable = false)
    private List<String> canciones;
    
    @OneToMany(mappedBy = "album")
    private List<GeneroAlbum> generos;
    
    @OneToMany (mappedBy = "album")
    private List<Producto> productos;

    public Album() {
    }

    public Album(Long id, String nombre, String descripcion, Calendar fechaLanzamiento, Artista artista, List<String> canciones, List<GeneroAlbum> generos, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artista = artista;
        this.canciones = canciones;
        this.generos = generos;
        this.productos = productos;
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    public List<GeneroAlbum> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroAlbum> generos) {
        this.generos = generos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.ecommerce.persistencia.entidades.Album[ id=" + id + " ]";
    }
    
}
