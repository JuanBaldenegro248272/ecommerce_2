/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.entidades.Producto;

/**
 *
 * @author Dana Chavez
 */
public interface IProductosBO {
    public abstract NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws BusinessException;
}
