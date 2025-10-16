/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.List;

/**
 *
 * @author Gael
 */
public class Carrito{

    private Long id;
    private double total;
    private Usuario usuario;
    private List<ItemCarrito> itemsCarrito;

    public Carrito() {
    }

    public Carrito(Long id, double total, Usuario usuario, List<ItemCarrito> itemsCarrito) {
        this.id = id;
        this.total = total;
        this.usuario = usuario;
        this.itemsCarrito = itemsCarrito;
    }

    public Carrito(double total, Usuario usuario, List<ItemCarrito> itemsCarrito) {
        this.total = total;
        this.usuario = usuario;
        this.itemsCarrito = itemsCarrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemCarrito> getItemsCarrito() {
        return itemsCarrito;
    }

    public void setItemsCarrito(List<ItemCarrito> itemsCarrito) {
        this.itemsCarrito = itemsCarrito;
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
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.NewEntity[ id=" + id + " ]";
    }

}
