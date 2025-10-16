/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jrasc
 */
public class Producto {

    private Long id;
    private String formato;
    private double precio;
    private String especificaciones;
    private boolean esDisponible;
    private int stock;
    private Album album;
    private List<ItemPedido> itemsPedido = new ArrayList<>();

    public Producto() {
    }

    public Producto(Long id, String formato, double precio, String especificaciones, boolean esDisponible, int stock, Album album) {
        this.id = id;
        this.formato = formato;
        this.precio = precio;
        this.especificaciones = especificaciones;
        this.esDisponible = esDisponible;
        this.stock = stock;
        this.album = album;
    }

    public Producto(String formato, double precio, String especificaciones, boolean esDisponible, int stock, Album album) {
        this.formato = formato;
        this.precio = precio;
        this.especificaciones = especificaciones;
        this.esDisponible = esDisponible;
        this.stock = stock;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public boolean isEsDisponible() {
        return esDisponible;
    }

    public void setEsDisponible(boolean esDisponible) {
        this.esDisponible = esDisponible;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<ItemPedido> getItemsPedido() {
        return itemsPedido;
    }

    public void setItemsPedido(List<ItemPedido> itemsPedido) {
        this.itemsPedido = itemsPedido;
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
        return "Dominio.Producto[ id=" + id + " ]";
    }

}
