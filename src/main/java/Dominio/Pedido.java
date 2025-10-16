/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author victoria
 */
public class Pedido {

    private Long id;
    private Estado estado;
    private Date fechaCompra;
    private double total;
    private String envioDireccion;
    private List<ItemPedido> productosComprados;
    private Usuario usuario;
    private Pago pago;

    public Pedido() {
    }

    public Pedido(Long id, Estado estado, Date fechaCompra, double total, List<ItemPedido> productosComprados, String envioDireccion, Usuario usuario) {
        this.id = id;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.productosComprados = productosComprados;
        this.envioDireccion = envioDireccion;
        this.usuario = usuario;
    }

    public Pedido(Estado estado, Date fechaCompra, double total, List<ItemPedido> productosComprados, String envioDireccion, Usuario usuario) {
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.productosComprados = productosComprados;
        this.envioDireccion = envioDireccion;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemPedido> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<ItemPedido> productosComprados) {
        this.productosComprados = productosComprados;
    }

    public String getEnvioDireccion() {
        return envioDireccion;
    }

    public void setEnvioDireccion(String envioDireccion) {
        this.envioDireccion = envioDireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Pedido[ id=" + id + " ]";
    }

}
