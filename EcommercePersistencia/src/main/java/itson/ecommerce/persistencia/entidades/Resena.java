/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 *
 * @author Dana Chavez
 */
@Entity
@Table (name = "resenas")
public class Resena implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "estado", nullable = false)
    private EstadoResena estado;
    
    @Column (name = "calificacion", nullable = false)
    private Integer calificacion;
    
    @Column (name = "comentario", length = 255, nullable = false)
    private String comentario;
    
    @ManyToOne
    @JoinColumn (name = "id_producto", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn (name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @PrePersist
    @PreUpdate
    private void validarCalificacion() {
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
    }

    public Resena() {
    }

    public Resena(Long id, EstadoResena estado, Integer calificacion, String comentario, Producto producto, Cliente cliente) {
        this.id = id;
        this.estado = estado;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.producto = producto;
        this.cliente = cliente;
    }

    public EstadoResena getEstado() {
        return estado;
    }

    public void setEstado(EstadoResena estado) {
        this.estado = estado;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Resena)) {
            return false;
        }
        Resena other = (Resena) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.ecommerce.persistencia.entidades.Resena[ id=" + id + " ]";
    }
    
}
