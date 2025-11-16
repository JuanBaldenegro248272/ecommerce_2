/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table (name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    
    @Column (name = "stock", nullable = false)
    private Integer stock;
    
    @Column (name = "es_disponible")
    private Boolean esDisponible;
    
    @Column (name = "precio", nullable = false)
    private Float precio;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "formato_producto", nullable = false)
    private FormatoProducto formato;
    
    @Column (name = "imagen_url", length = 255, nullable = false)
    private String imagenUrl;
    
    @Column (name = "descripcion", length = 255)
    private String descripcion;
    
    @ManyToOne
    @JoinColumn (name = "id_album", nullable = false)
    private Album album;
    
    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallesPedido;
    
    @OneToMany(mappedBy = "producto")
    private List<DetalleCarrito> detallesCarrito;
    
    @OneToMany(mappedBy = "producto")
    private List<Resena> resenas;

    public Producto() {
    }

    public Producto(Long id, Integer stock, Boolean esDisponible, Float precio, FormatoProducto formato, String imagenUrl, String descripcion, Album album, List<DetallePedido> detallesPedido, List<DetalleCarrito> detallesCarrito, List<Resena> resenas) {
        this.id = id;
        this.stock = stock;
        this.esDisponible = esDisponible;
        this.precio = precio;
        this.formato = formato;
        this.imagenUrl = imagenUrl;
        this.descripcion = descripcion;
        this.album = album;
        this.detallesPedido = detallesPedido;
        this.detallesCarrito = detallesCarrito;
        this.resenas = resenas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public FormatoProducto getFormato() {
        return formato;
    }

    public void setFormato(FormatoProducto formato) {
        this.formato = formato;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public List<DetalleCarrito> getDetallesCarrito() {
        return detallesCarrito;
    }

    public void setDetallesCarrito(List<DetalleCarrito> detallesCarrito) {
        this.detallesCarrito = detallesCarrito;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.ecommerce.persistencia.entidades.Producto[ id=" + id + " ]";
    }
    
}
