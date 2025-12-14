/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.dtos;

import java.io.Serializable;

/**
 *
 * @author victoria
 */
public class AgregarProductoRequest implements Serializable {
    
    private Long idUsuario;
    private Long idProducto;
    private Integer cantidad;

    public AgregarProductoRequest() {
    }

    public AgregarProductoRequest(Long idUsuario, Long idProducto, Integer cantidad) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}